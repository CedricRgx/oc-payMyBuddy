package com.openclassrooms.paymybuddy.controller;

import com.openclassrooms.paymybuddy.model.DTO.NewTransfertDTO;
import com.openclassrooms.paymybuddy.model.User;
import com.openclassrooms.paymybuddy.repository.TransfertRepository;
import com.openclassrooms.paymybuddy.service.impl.TransfertService;
import com.openclassrooms.paymybuddy.service.impl.UserService;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

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
    private BindingResult bindingResult;

    @Mock
    private UserService userService;

    @Mock
    private TransfertService transfertService;

    @Mock
    private TransfertRepository transfertRepository;

    @Mock
    SecurityContext securityContext;

    @BeforeEach
    public void setUp() {
        Authentication authentication = mock(Authentication.class);
        lenient().when(authentication.getName()).thenReturn("user@example.com");
        lenient().when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
    }

    @Test
    public void testViewTransfertPage_ShouldDisplayTransfertTemplate() {
        // Given
        when(userService.getUserIdByEmail(anyString())).thenReturn(1L);
        User user = User.builder().friends(new ArrayList<>()).build();
        Optional<User> optionalUser = Optional.of(user);
        when(userService.getUserById(anyLong())).thenReturn(optionalUser);

        when(userService.getActiveFriends(any())).thenReturn(Collections.emptyList());
        when(transfertService.getListOfTransferts(anyLong(), anyInt(), anyInt())).thenReturn(Page.empty());
        when(transfertRepository.countByUser(anyLong())).thenReturn(0);

        // When
        String viewName = transfertController.viewTransfertPage(model, session, 0, 3);

        // Then
        verify(model).addAttribute(eq("listOfConnections"), eq(Collections.emptyList()));
        verify(model).addAttribute(eq("listTransfertsDTO"), eq(Page.empty()));
        verify(model).addAttribute(eq("pages"), eq(0));
        verify(model).addAttribute(eq("currentPage"), eq(0));
        verify(model).addAttribute(eq("pageSize"), eq(3));
        assertEquals("transfert", viewName);
    }

    @Test
    public void testViewTransfertPage_ShouldDisplayTransfertTemplateWithListConnectionsNull() {
        // Given
        when(userService.getUserIdByEmail(anyString())).thenReturn(1L);
        User user = User.builder().friends(new ArrayList<>()).build();
        Optional<User> optionalUser = Optional.of(user);
        when(userService.getUserById(anyLong())).thenReturn(optionalUser);

        when(userService.getActiveFriends(any())).thenReturn(null);
        when(transfertService.getListOfTransferts(anyLong(), anyInt(), anyInt())).thenReturn(Page.empty());
        when(transfertRepository.countByUser(anyLong())).thenReturn(0);

        // When
        String viewName = transfertController.viewTransfertPage(model, session, 0, 3);

        // Then
        assertEquals("transfert", viewName);
    }

    @Test
    public void testViewTransfertPage_ShouldAddErrorMessageToModelWhenSessionContainsErrorMessage() {
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
    public void testViewTransfertPage_ShouldAddSuccessMessageToModelWhenSessionContainsSuccessMessage() {
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

    @Test
    public void testViewTransfertPage_ShouldHandleNullSessionAttributes(){
        // Given
        when(userService.getUserIdByEmail(anyString())).thenReturn(1L);
        User user = User.builder().friends(new ArrayList<>()).build();
        when(userService.getUserById(anyLong())).thenReturn(Optional.of(user));
        when(session.getAttribute(anyString())).thenReturn(null);

        // When
        String viewName = transfertController.viewTransfertPage(model, session, 0, 3);

        // Then
        verify(model, never()).addAttribute(eq("errorMessage"), any());
        verify(model, never()).addAttribute(eq("successMessage"), any());
        assertEquals("transfert", viewName);
    }

    @Test
    public void testAddTransfertPage_ValidTransfer() throws Exception {
        // Given
        NewTransfertDTO dto = NewTransfertDTO.builder().build();
        when(bindingResult.hasErrors()).thenReturn(false);
        when(transfertService.addNewTransfert(dto)).thenReturn(true);

        // When
        String viewName = transfertController.addTransfertPage(dto, bindingResult, model, session);

        // Then
        verify(session).setAttribute("successMessage", "Transfer completed successfully!");
        assertEquals("redirect:/transfert", viewName);
    }

    @Test
    public void testAddTransfertPage_ValidationFailure() throws Exception {
        // Given
        when(bindingResult.hasErrors()).thenReturn(true);

        // When
        String viewName = transfertController.addTransfertPage(NewTransfertDTO.builder().build(), bindingResult, model, session);

        // Then
        verify(model).addAttribute(eq("errorMessage"), anyString());
        assertEquals("transfert", viewName);
    }

    @Test
    public void testAddTransfertPage_ExceptionDuringTransfer() throws Exception {
        // Given
        when(bindingResult.hasErrors()).thenReturn(false);
        doThrow(new RuntimeException("Service failure")).when(transfertService).addNewTransfert(any(NewTransfertDTO.class));

        // When
        String viewName = transfertController.addTransfertPage(NewTransfertDTO.builder().build(), bindingResult, model, session);

        // Then
        verify(model).addAttribute("errorMessage", "An unexpected error occurred.");
        assertEquals("transfert", viewName);
    }

    @Test
    public void testAddTransfertPage_InsufficientBalance() throws Exception {
        // Given
        NewTransfertDTO dto = NewTransfertDTO.builder().build();
        when(bindingResult.hasErrors()).thenReturn(false);
        when(transfertService.addNewTransfert(dto)).thenReturn(false);

        // When
        String viewName = transfertController.addTransfertPage(dto, bindingResult, model, session);

        // Then
        verify(session).setAttribute("errorMessage", "Insufficient balance to make the transfer");
        assertEquals("redirect:/transfert", viewName);
    }



}
