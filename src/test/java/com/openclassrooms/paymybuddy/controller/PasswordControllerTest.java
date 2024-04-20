package com.openclassrooms.paymybuddy.controller;

import com.openclassrooms.paymybuddy.model.DTO.PasswordUpdateDTO;
import com.openclassrooms.paymybuddy.model.UserAccount;
import com.openclassrooms.paymybuddy.service.impl.UserAccountService;
import jakarta.servlet.http.HttpServletRequest;
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
    private UserAccountService userAccountService;

    @Mock
    private HttpServletRequest request;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private Model model;

    @Test
    public void showChangePasswordForm_shouldReturnPasswordUpdateForm() {
        // Given
        PasswordUpdateDTO passwordUpdateDTO = PasswordUpdateDTO.builder().build();

        // When
        String viewName = passwordController.showChangePasswordForm(model);

        // Then
        verify(model).addAttribute("passwordChange", passwordUpdateDTO);
        assertEquals("passwordUpdateForm", viewName);
    }

    @Test
    public void changePassword_WithValidPasswordUpdate_ReturnsRedirectToLogin() {
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

        when(userAccountService.findByEmail(anyString()))
                .thenReturn(Optional.of(UserAccount.builder()
                        .email("user@example.com")
                        .password("oldPassword")
                        .build()));

        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");

        when(passwordEncoder.matches(eq("oldPassword"), anyString())).thenReturn(true);

        // When
        String viewName = passwordController.changePassword(passwordUpdateDTO, bindingResult, request);

        // Then
        verify(userAccountService).findByEmail("user@example.com");
        verify(userAccountService).savePassword(anyString(), eq("user@example.com"));
        verify(request).getSession(false);
        verify(bindingResult, never()).rejectValue(anyString(), anyString(), anyString());
        verify(model, never()).addAttribute(anyString(), any());
        assertEquals("redirect:/login?logout", viewName);
    }

}