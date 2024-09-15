package com.openclassrooms.paymybuddy.controller;

import com.openclassrooms.paymybuddy.model.DTO.PasswordUpdateDTO;
import com.openclassrooms.paymybuddy.model.User;
import com.openclassrooms.paymybuddy.service.impl.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PasswordControllerTest {

    @InjectMocks
    private PasswordController passwordController;

    @Mock
    private UserService userService;

    @Mock
    private HttpServletRequest request;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private Model model;

    @Mock
    private HttpSession session;

    @Mock
    private BindingResult bindingResult;

    @Mock
    private SecurityContext securityContext;

    @Mock
    private Authentication authentication;

    @BeforeEach
    public void setUp() {
        lenient().when(request.getSession(false)).thenReturn(session);
        lenient().when(securityContext.getAuthentication()).thenReturn(authentication);
        lenient().when(authentication.getName()).thenReturn("user@example.com");
        SecurityContextHolder.setContext(securityContext);
    }

    @Test
    public void testShowChangePasswordForm_shouldReturnPasswordUpdateForm() {
        // Given
        PasswordUpdateDTO passwordUpdateDTO = PasswordUpdateDTO.builder().build();

        // When
        String viewName = passwordController.showChangePasswordForm(model);

        // Then
        verify(model).addAttribute("passwordChange", passwordUpdateDTO);
        assertEquals("passwordUpdateForm", viewName);
    }

    @Test
    public void testChangePassword_WithValidPasswordUpdate_ReturnsRedirectToLogin() {
        // Given
        PasswordUpdateDTO passwordUpdateDTO = PasswordUpdateDTO.builder()
                .currentPassword("oldPassword")
                .newPassword("newPassword")
                .confirmPassword("newPassword")
                .build();
        BindingResult bindingResult = mock(BindingResult.class);

        Authentication authentication = mock(Authentication.class);
        when(authentication.getName()).thenReturn("user@example.com");
        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        when(userService.findByEmail(anyString()))
                .thenReturn(Optional.of(User.builder()
                        .email("user@example.com")
                        .password("oldPassword")
                        .build()));

        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");

        when(passwordEncoder.matches(eq("oldPassword"), anyString())).thenReturn(true);

        // When
        String viewName = passwordController.changePassword(passwordUpdateDTO, bindingResult, request);

        // Then
        verify(userService).findByEmail("user@example.com");
        verify(userService).savePassword(anyString(), eq("user@example.com"));
        verify(request).getSession(false);
        verify(bindingResult, never()).rejectValue(anyString(), anyString(), anyString());
        verify(model, never()).addAttribute(anyString(), any());
        assertEquals("redirect:/login?logout", viewName);
    }

    @Test
    public void testChangePassword_UserNotFound() {
        // Given
        PasswordUpdateDTO dto = PasswordUpdateDTO.builder().build();
        when(userService.findByEmail("user@example.com")).thenReturn(Optional.empty()); // User not found scenario

        // When
        String viewName = passwordController.changePassword(dto, bindingResult, request);

        // Then
        verify(bindingResult).rejectValue("currentPassword", "error.userNotFound", "User not found.");
        assertEquals("passwordUpdateForm", viewName);
    }

    @Test
    public void testChangePassword_WhenCurrentPasswordIsIncorrect_ShouldRejectValue() {
        // Given
        PasswordUpdateDTO dto = PasswordUpdateDTO.builder()
                .currentPassword("current")
                .newPassword("newPass")
                .confirmPassword("newPass").build();
        when(userService.findByEmail("user@example.com")).thenReturn(Optional.of(User.builder().password("hashedPassword").build()));
        when(passwordEncoder.matches("current", "hashedPassword")).thenReturn(false);

        // When
        String result = passwordController.changePassword(dto, bindingResult, request);

        // Then
        verify(bindingResult).rejectValue("currentPassword", "error.currentPassword", "The current password is incorrect.");
        assertEquals("passwordUpdateForm", result);
    }

    @Test
    public void testChangePassword_WhenNewPasswordDoesNotMatchConfirmation_ShouldReturnToForm() {
        // Given
        PasswordUpdateDTO dto = PasswordUpdateDTO.builder()
                .currentPassword("current")
                .newPassword("newPass")
                .confirmPassword("newPassFalse").build();
        when(userService.findByEmail("user@example.com")).thenReturn(Optional.of(User.builder().password("hashedPassword").build()));
        when(passwordEncoder.matches("current", "hashedPassword")).thenReturn(true);

        // When
        String result = passwordController.changePassword(dto, bindingResult, request);

        // Then
        verify(bindingResult).rejectValue("confirmPassword", "error.passwordChange", "The password confirmation does not match.");
        assertEquals("passwordUpdateForm", result);
    }

    @Test
    public void testChangePassword_WhenNewPasswordDoesMatchConfirmation() {
        // Given
        PasswordUpdateDTO dto = PasswordUpdateDTO.builder()
                .currentPassword("current")
                .newPassword("newPass")
                .confirmPassword("newPass").build();when(userService.findByEmail("user@example.com")).thenReturn(Optional.of(User.builder().password("hashedPassword").build()));
        when(passwordEncoder.matches("current", "hashedPassword")).thenReturn(true);

        // When
        String result = passwordController.changePassword(dto, bindingResult, request);

        // Then
        assertEquals("redirect:/login?logout", result);
    }

    @Test
    public void testChangePassword_WhenPasswordUpdateIsSuccessful_ShouldInvalidateSessionAndRedirect() {
        // Given
        PasswordUpdateDTO dto = PasswordUpdateDTO.builder()
                .currentPassword("current")
                .newPassword("newPass")
                .confirmPassword("newPass").build();
        when(userService.findByEmail("user@example.com")).thenReturn(Optional.of(User.builder().password("hashedPassword").build()));
        when(passwordEncoder.matches("current", "hashedPassword")).thenReturn(true);
        when(passwordEncoder.encode("newPass")).thenReturn("encodedNewPass");

        // When
        String result = passwordController.changePassword(dto, bindingResult, request);

        // Then
        verify(userService).savePassword("encodedNewPass", "user@example.com");
        verify(session).invalidate();
        assertEquals("redirect:/login?logout", result);
    }

}