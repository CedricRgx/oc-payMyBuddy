package com.openclassrooms.paymybuddy.controller;

import com.openclassrooms.paymybuddy.model.DTO.UserDTO;
import com.openclassrooms.paymybuddy.service.impl.AppAccountService;
import com.openclassrooms.paymybuddy.service.impl.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class HomeControllerTest {

    @InjectMocks
    private HomeController homeController;

    @Mock
    private Model model;

    @Mock
    private UserService userService;

    @BeforeEach
    public void setUp() {
        String email = "user@example.com";
        Authentication authentication = mock(Authentication.class);
        when(authentication.getName()).thenReturn(email);
        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
    }

    @Test
    public void testViewHomePage_AuthenticatedUser_ReturnsHomePage() {
        // Given
        String email = "user@example.com";
        when(userService.getUserDTOFromUser(email)).thenReturn(UserDTO.builder().build());

        // When
        String result = homeController.viewHomePage(model);

        // Then
        verify(userService).getUserDTOFromUser(email);
        verify(model).addAttribute("userDTO", UserDTO.builder().build());
        assertEquals("home", result);
    }

    @Test
    public void testCreditBalance_shouldCreditUserBalanceAndReturnHomePage() {
        // Given
        double amount = 100.0;
        String email = "user@example.com";
        Long userId = 1L;
        Model model = mock(Model.class);
        RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);

        when(userService.findByEmail(email).get().getUserId()).thenReturn(userId);

        // When
        String result = homeController.creditBalance(amount, model, redirectAttributes);

        // Then
        verify(userService).getUserIdByEmail(email);
        verify(userService).creditUserBalance(userId, amount);
        verify(redirectAttributes).addFlashAttribute("successMessage", "Balance credited successfully.");
        assertEquals("redirect:home", result);
    }

}