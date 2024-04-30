package com.openclassrooms.paymybuddy.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * The type Login controller test.
 */
@ExtendWith(MockitoExtension.class)
public class LoginControllerTest {

    @InjectMocks
    private LoginController loginController;

    @Mock
    private Model model;

    @Test
    public void login_withError_shouldAddErrorMessageToModel() {
        // Given
        String error = "error";

        // When
        String viewName = loginController.login(model, error, null, null);

        // Then
        verify(model).addAttribute("errorMessage", "Your username or password is incorrect.");
        assertEquals("login", viewName);
    }

    @Test
    public void login_withLogout_shouldAddLogoutMessageToModel() {
        // Given
        String logout = "logout";

        // When
        String viewName = loginController.login(model, null, logout, null);

        // Then
        verify(model).addAttribute("logoutMessage", "You have been successfully logged out.");
        assertEquals("login", viewName);
    }

    @Test
    public void login_withDisabled_shouldAddDisabledMessageToModel() {
        // Given
        String disabled = "disabled";

        // When
        String viewName = loginController.login(model, null, null, disabled);

        // Then
        verify(model).addAttribute("disabledMessage", "Your account has been disabled.");
        assertEquals("login", viewName);
    }

    @Test
    public void login_withoutMessages_shouldReturnLoginView() {
        // When
        String viewName = loginController.login(model, null, null, null);

        // Then
        assertEquals("login", viewName);
    }
}
