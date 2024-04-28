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

/**
 * The type Connection controller test.
 */
@ExtendWith(MockitoExtension.class)
public class ConnectionControllerTest {

    /**
     * The Controller.
     */
    @InjectMocks
    ConnectionController controller;

    /**
     * The User service.
     */
    @Mock
    UserService userService;

    /**
     * The Connection service.
     */
    @Mock
    ConnectionService connectionService;

    /**
     * The Security context.
     */
    @Mock
    SecurityContext securityContext;

    /**
     * Sets up.
     */
    @BeforeEach
    public void setUp() {
        Authentication authentication = mock(Authentication.class);
        when(authentication.getName()).thenReturn("user@example.com");
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
    }

    /**
     * Test show connection list.
     */
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

    /**
     * Show connection list should retrieve current user and set attribute list of connections.
     */
    @Test
    public void showConnectionList_shouldRetrieveCurrentUserAndSetAttributeListOfConnections() {
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

    /**
     * Search connection with query should return search results.
     */
    @Test
    public void searchConnection_withQuery_shouldReturnSearchResults() {
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

    /**
     * Search connection with empty query should return all connections.
     */
    @Test
    public void searchConnection_withEmptyQuery_shouldReturnAllConnections() {
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

    /**
     * Add connection should return success message when connection added.
     */
    @Test
    public void addConnection_shouldReturnSuccessMessageWhenConnectionAdded() {
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

    /**
     * Add connection should return error message when connection not added.
     */
    @Test
    public void addConnection_shouldReturnErrorMessageWhenConnectionNotAdded() {
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

    /**
     * Remove connection should return error message when connection not removed.
     */
    @Test
    public void removeConnection_shouldReturnErrorMessageWhenConnectionNotRemoved() {
        // Given
        Model model = mock(Model.class);
        String email = "user@example.com";
        Long userId = 1L;
        Long friendId = 2L;
        when(userService.getUserIdByEmail(email)).thenReturn(userId);
        User user = new User();
        when(userService.getUserById(userId)).thenReturn(Optional.of(user));
        when(connectionService.removeConnection(userId, friendId)).thenReturn(false);

        // When
        String viewName = controller.removeConnection(friendId, model);

        // Then
        assertEquals("listConnections", viewName);
        verify(model).addAttribute("errorRemoveMessage", "Connection deletion failed");
    }

}
