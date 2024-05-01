package com.openclassrooms.paymybuddy.controller;

import com.openclassrooms.paymybuddy.model.User;
import com.openclassrooms.paymybuddy.service.impl.ConnectionService;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ConnectionControllerTest {

    @InjectMocks
    ConnectionController controller;

    @Mock
    UserService userService;

    @Mock
    ConnectionService connectionService;

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
    public void testShowConnectionList() {
        // Given
        Model model = mock(Model.class);
        String email = "user@example.com";
        Long userId = 1L;
        User userConnected = new User();
        when(userService.getUserIdByEmail(email)).thenReturn(userId);
        when(userService.getUserById(userId)).thenReturn(Optional.of(userConnected));

        // When
        String viewName = controller.showConnectionList(model);

        // Then
        assertEquals("listConnections", viewName);
    }

    @Test
    public void testShowConnectionList_shouldRetrieveCurrentUserAndSetAttributeListOfConnections() {
        // Given
        Model model = mock(Model.class);
        String email = "user@example.com";
        Long userId = 1L;
        User userConnected = new User();
        when(userService.getUserIdByEmail(email)).thenReturn(userId);
        when(userService.getUserById(userId)).thenReturn(Optional.of(userConnected));

        // When
        String viewName = controller.showConnectionList(model);

        // Then
        assertEquals("listConnections", viewName);
        verify(userService).getUserIdByEmail("user@example.com");
        verify(userService, times(1)).getUserById(userId);
        verify(securityContext.getAuthentication()).getName();
    }

    @Test
    public void testSearchConnection_withQuery_shouldReturnSearchResults() {
        // Given
        Model model = mock(Model.class);
        String email = "user@example.com";
        Long userId = 1L;
        String query = "search query";
        List<User> searchResults = new ArrayList<>();
        searchResults.add(new User());
        searchResults.add(new User());

        when(userService.getUserIdByEmail(email)).thenReturn(userId);
        when(connectionService.searchConnections(query, userId)).thenReturn(searchResults);

        // When
        String viewName = controller.searchConnection(model, query);

        // Then
        assertEquals("searchConnection", viewName);
        verify(userService).getUserIdByEmail(email);
        verify(connectionService).searchConnections(query, userId);
        verify(model).addAttribute("listOfConnections", searchResults);
    }

    @Test
    public void testSearchConnection_withEmptyQuery_shouldReturnAllConnections() {
        // Given
        Model model = mock(Model.class);
        String email = "user@example.com";
        Long userId = 1L;
        String query = null;
        List<User> allConnections = new ArrayList<>();
        allConnections.add(new User());
        allConnections.add(new User());

        when(userService.getUserIdByEmail(email)).thenReturn(userId);
        when(connectionService.findAllConnections(userId)).thenReturn(allConnections);

        // When
        String viewName = controller.searchConnection(model, query);

        // Then
        assertEquals("searchConnection", viewName);
        verify(userService).getUserIdByEmail(email);
        verify(connectionService).findAllConnections(userId);
        verify(model).addAttribute("listOfConnections", allConnections);
    }

    @Test
    public void testAddConnection_shouldReturnSuccessMessageWhenConnectionAdded() {
        // Given
        RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();
        String email = "user@example.com";
        Long userId = 1L;
        Long friendId = 2L;
        when(userService.getUserIdByEmail(email)).thenReturn(userId);
        when(connectionService.addConnection(userId, friendId)).thenReturn(true);

        // When
        String viewName = controller.addConnection(friendId, redirectAttributes);

        // Then
        assertEquals("redirect:/listConnections", viewName);
        assertEquals("Successfully added the connection", redirectAttributes.getFlashAttributes().get("successAddMessage"));
    }

    @Test
    public void testAddConnection_shouldReturnErrorMessageWhenConnectionNotAdded() {
        // Given
        RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();
        String email = "user@example.com";
        Long userId = 1L;
        Long friendId = 2L;
        when(userService.getUserIdByEmail(email)).thenReturn(userId);
        when(connectionService.addConnection(userId, friendId)).thenReturn(false);

        // When
        String viewName = controller.addConnection(friendId, redirectAttributes);

        // Then
        assertEquals("redirect:/listConnections", viewName);
        assertEquals("Failed to add the connection", redirectAttributes.getFlashAttributes().get("errorAddMessage"));
    }

    @Test
    public void testRemoveConnection_shouldReturnErrorMessageWhenConnectionIsRemoved() {
        // Given
        Model model = mock(Model.class);
        String email = "user@example.com";
        Long userId = 1L;
        Long friendId = 2L;
        when(userService.getUserIdByEmail(email)).thenReturn(userId);
        User user = User.builder().build();
        when(userService.getUserById(userId)).thenReturn(Optional.of(user));
        when(connectionService.removeConnection(userId, friendId)).thenReturn(true);

        // When
        String viewName = controller.removeConnection(friendId, model);

        // Then
        assertEquals("listConnections", viewName);
        verify(model).addAttribute("successRemoveMessage", "Successful connection deletion");
    }

    @Test
    public void testRemoveConnection_shouldReturnErrorMessageWhenConnectionNotRemoved() {
        // Given
        Model model = mock(Model.class);
        String email = "user@example.com";
        Long userId = 1L;
        Long friendId = 2L;
        when(userService.getUserIdByEmail(email)).thenReturn(userId);
        User user = User.builder().build();
        when(userService.getUserById(userId)).thenReturn(Optional.of(user));
        when(connectionService.removeConnection(userId, friendId)).thenReturn(false);

        // When
        String viewName = controller.removeConnection(friendId, model);

        // Then
        assertEquals("listConnections", viewName);
        verify(model).addAttribute("errorRemoveMessage", "Connection deletion failed");
    }

    @Test
    public void testSetAttributeListOfConnections_NullList(){
        // Given
        Long userId = 1L;
        Model model = mock(Model.class);
        when(userService.getUserById(userId)).thenReturn(Optional.of(User.builder().build()));
        when(userService.getActiveFriends(any())).thenReturn(null);

        // When
        controller.setAttributeListOfConnections(model, userId);

        // Then
        verify(userService, times(1)).getUserById(userId);
        verify(userService, times(1)).getActiveFriends(any());
        verify(model, never()).addAttribute(eq("listOfConnections"), any());
    }

}
