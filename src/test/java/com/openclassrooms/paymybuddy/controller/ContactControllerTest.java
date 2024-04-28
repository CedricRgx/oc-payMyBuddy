package com.openclassrooms.paymybuddy.controller;

import com.openclassrooms.paymybuddy.model.DTO.UserDTO;
import com.openclassrooms.paymybuddy.service.impl.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * The type Contact controller test.
 */
@ExtendWith(MockitoExtension.class)
public class ContactControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private ContactController contactController;

    /**
     * Sets up.
     */
    @BeforeEach
    public void setUp() {
        Authentication authentication = new UsernamePasswordAuthenticationToken("user@example.com", "password");
        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
    }

    /**
     * View contact page user dto exists returns contact page.
     */
    @Test
    public void viewContactPage_UserDTOExists_ReturnsContactPage() {
        // Given
        Model model = mock(Model.class);
        String email = "user@example.com";
        UserDTO userDTO = UserDTO.builder().build();

        when(userService.getUserDTOFromUser(email)).thenReturn(userDTO);

        // When
        String result = contactController.viewContactPage(model);

        // Then
        verify(userService).getUserDTOFromUser(email);
        verify(model).addAttribute("userDTO", userDTO);
        assertEquals("contact", result);
    }

    /**
     * View contact page user dto not exists returns contact page.
     */
    @Test
    public void viewContactPage_UserDTONotExists_ReturnsContactPage() {
        // Given
        Model model = mock(Model.class);
        String email = "user@example.com";

        when(userService.getUserDTOFromUser(email)).thenReturn(null);

        // When
        String result = contactController.viewContactPage(model);

        // Then
        verify(userService).getUserDTOFromUser(email);
        verifyNoInteractions(model);
        assertEquals("contact", result);
    }
}