package com.openclassrooms.paymybuddy.controller;

import com.openclassrooms.paymybuddy.exceptions.EmailAlreadyUsedException;
import com.openclassrooms.paymybuddy.model.DTO.RegisterDTO;
import com.openclassrooms.paymybuddy.service.impl.RegisterService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RegistrationControllerTest {

    @Mock
    private RegisterService registerService;

    @Mock
    private Model model;

    @InjectMocks
    private RegistrationController registrationController;

    @Test
    public void viewRegistrationForm_ShouldReturnRegistrationFormTemplate() {
        // Given
        RegisterDTO registerDTO = RegisterDTO.builder().build();
        when(model.addAttribute(eq("registerDTO"), any(RegisterDTO.class))).thenReturn(model);

        // When
        String viewName = registrationController.viewRegistrationForm(model);

        // Then
        verify(model).addAttribute("registerDTO", registerDTO);
        assertEquals("registrationForm", viewName);
    }

    @Test
    public void addUser_ValidRegistration_RedirectToLoginPage() {
        // Given
        RegisterDTO registerDTO = RegisterDTO.builder().build();
        BindingResult bindingResult = mock(BindingResult.class);

        // When
        String viewName = registrationController.addUser(registerDTO, bindingResult, model);

        // Then
        verify(registerService).addUser(registerDTO);
        verifyNoInteractions(model);
        assertEquals("redirect:/login", viewName);
    }

    @Test
    public void addUser_InvalidRegistration_ReturnsRegistrationForm() {
        // Given
        RegisterDTO registerDTO = RegisterDTO.builder().build();
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(true);

        // When
        String viewName = registrationController.addUser(registerDTO, bindingResult, model);

        // Then
        verifyNoInteractions(registerService);
        assertEquals("registrationForm", viewName);
    }

    @Test
    public void addUser_EmailAlreadyUsedException_ReturnsRegistrationFormWithError() {
        // Given
        RegisterDTO registerDTO = RegisterDTO.builder().build();
        BindingResult bindingResult = mock(BindingResult.class);
        EmailAlreadyUsedException exception = new EmailAlreadyUsedException("Email already in use");
        when(registerService.addUser(registerDTO)).thenThrow(exception);

        // When
        String viewName = registrationController.addUser(registerDTO, bindingResult, model);

        // Then
        verify(model).addAttribute("errorEmail", "Email already in use");
        assertEquals("registrationForm", viewName);
    }

    @Test
    public void addUser_GenericException_ReturnsRegistrationFormWithError() {
        // Given
        RegisterDTO registerDTO = RegisterDTO.builder().build();
        BindingResult bindingResult = mock(BindingResult.class);
        RuntimeException exception = new RuntimeException("Generic exception");
        when(registerService.addUser(registerDTO)).thenThrow(exception);

        // When
        String viewName = registrationController.addUser(registerDTO, bindingResult, model);

        // Then
        verify(model).addAttribute("error", "An error occurs");
        assertEquals("registrationForm", viewName);
    }
}