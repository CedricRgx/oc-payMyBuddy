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
        User mockUserConnected = User.builder().build();
        mockUserConnected.setUserId(userId);
        when(userService.findByEmail(email)).thenReturn(Optional.of(mockUserConnected));
        when(userService.getUserById(userId)).thenReturn(Optional.of(mockUserConnected));

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
        User mockUserConnected = User.builder().build();
        mockUserConnected.setUserId(userId);
        when(userService.findByEmail(email)).thenReturn(Optional.of(mockUserConnected));
        when(userService.getUserById(userId)).thenReturn(Optional.of(mockUserConnected));

        // When
        String viewName = controller.showConnectionList(model);

        // Then
        assertEquals("listConnections", viewName);
        verify(userService).findByEmail("user@example.com");
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
        searchResults.add(User.builder().build());
        searchResults.add(User.builder().build());
        User mockUser = User.builder().build();
        mockUser.setUserId(userId);

        when(userService.findByEmail(email)).thenReturn(Optional.of(mockUser));
        when(connectionService.searchConnections(query, userId)).thenReturn(searchResults);

        // When
        String viewName = controller.searchConnection(model, query);

        // Then
        assertEquals("searchConnection", viewName);
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
        allConnections.add(User.builder().build());
        allConnections.add(User.builder().build());
        User mockUser = User.builder().build();
        mockUser.setUserId(userId);

        when(userService.findByEmail(email)).thenReturn(Optional.of(mockUser));
        when(connectionService.findAllConnections(userId)).thenReturn(allConnections);

        // When
        String viewName = controller.searchConnection(model, query);

        // Then
        assertEquals("searchConnection", viewName);
        verify(userService).findByEmail(email);
        verify(connectionService).findAllConnections(userId);
        verify(model).addAttribute("listOfConnections", allConnections);
    }

    @Test
    public void testAddConnection_shouldReturnSuccessMessageWhenConnectionAdded() {
        // Given
        RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();
        String email = "user@example.com";
        Long friendId = 2L;
        User mockUser = User.builder().build();
        mockUser.setUserId(1L);
        when(userService.findByEmail(email)).thenReturn(Optional.of(mockUser));
        when(connectionService.addConnection(mockUser.getUserId(), friendId)).thenReturn(true);

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
        Long friendId = 2L;
        User mockUser = User.builder().build();
        mockUser.setUserId(1L);
        when(userService.findByEmail(email)).thenReturn(Optional.of(mockUser));
        when(connectionService.addConnection(mockUser.getUserId(), friendId)).thenReturn(false);

        // When
        String viewName = controller.addConnection(friendId, redirectAttributes);

        // Then
        assertEquals("redirect:/listConnections", viewName);
        assertEquals("Failed to add the connection", redirectAttributes.getFlashAttributes().get("errorAddMessage"));
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
