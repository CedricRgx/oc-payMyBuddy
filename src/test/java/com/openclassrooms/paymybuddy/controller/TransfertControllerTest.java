package com.openclassrooms.paymybuddy.controller;

import com.openclassrooms.paymybuddy.controller.TransfertController;
import com.openclassrooms.paymybuddy.model.User;
import com.openclassrooms.paymybuddy.service.impl.TransfertService;
import com.openclassrooms.paymybuddy.service.impl.UserService;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TransfertControllerTest {

    @InjectMocks
    private TransfertController transfertController;

    @Mock
    private Model model;

    @Mock
    private HttpSession session;

    @Mock
    private UserService userService;

    @Mock
    private TransfertService transfertService;

    @Mock
    SecurityContext securityContext;

    @BeforeEach
    public void setUp() {
        Authentication authentication = mock(Authentication.class);
        when(authentication.getName()).thenReturn("user@example.com");
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
    }

    @Test
    public void viewTransfertPage_ShouldDisplayTransfertTemplate() throws Exception {
        // Given
        when(userService.getUserIdByEmail(anyString())).thenReturn(1L);
        User user = User.builder().friends(new ArrayList<>()).build();
        Optional<User> optionalUser = Optional.of(user);
        when(userService.getUserById(anyLong())).thenReturn(optionalUser);

        when(userService.getActiveFriends(any())).thenReturn(Collections.emptyList());
        when(transfertService.getListOfTransferts(anyLong(), anyInt(), anyInt())).thenReturn(Collections.emptyList());
        when(transfertService.countTransferts(anyLong())).thenReturn(0);

        // When
        String viewName = transfertController.viewTransfertPage(model, session, 0, 3);

        // Then
        verify(model).addAttribute(eq("listOfConnections"), eq(Collections.emptyList()));
        verify(model).addAttribute(eq("listTransfertsDTO"), eq(Collections.emptyList()));
        verify(model).addAttribute(eq("pages"), eq(0));
        verify(model).addAttribute(eq("currentPage"), eq(0));
        verify(model).addAttribute(eq("pageSize"), eq(3));
        assertEquals("transfert", viewName);
    }

    @Test
    public void viewTransfertPage_ShouldAddErrorMessageToModelWhenSessionContainsErrorMessage() throws Exception {
        // Given
        User user = User.builder().friends(new ArrayList<>()).build();
        Optional<User> optionalUser = Optional.of(user);
        when(userService.getUserById(anyLong())).thenReturn(optionalUser);
        when(session.getAttribute("errorMessage")).thenReturn("Error message from session");

        // When
        transfertController.viewTransfertPage(model, session, 0, 3);

        // Then
        verify(model).addAttribute(eq("errorMessage"), eq("Error message from session"));
        verify(session).removeAttribute("errorMessage");
    }

    @Test
    public void viewTransfertPage_ShouldAddSuccessMessageToModelWhenSessionContainsSuccessMessage() throws Exception {
        // Given
        User user = User.builder().friends(new ArrayList<>()).build();
        Optional<User> optionalUser = Optional.of(user);
        when(userService.getUserById(anyLong())).thenReturn(optionalUser);

        doReturn("Success message from session").when(session).getAttribute("successMessage");
        doReturn(null).when(session).getAttribute("errorMessage");

        // When
        transfertController.viewTransfertPage(model, session, 0, 3);

        // Then
        verify(model).addAttribute("successMessage", "Success message from session");
        verify(session).removeAttribute("successMessage");
    }
}
