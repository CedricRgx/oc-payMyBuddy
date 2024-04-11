package com.openclassrooms.paymybuddy.controller;

import com.openclassrooms.paymybuddy.model.DTO.ConnectionDTO;
import com.openclassrooms.paymybuddy.model.User;
import com.openclassrooms.paymybuddy.service.impl.ConnectionService;
import com.openclassrooms.paymybuddy.service.impl.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

/**
 * Controller for handling user connections in the PayMyBuddy application.
 */
@Controller
@Slf4j
public class ConnectionController {

    @Autowired
    private UserService userService;

    @Autowired
    private ConnectionService connectionService;

    /**
     * Displays the list of connections for the currently authenticated user.
     *
     * @param model The model for the view to add attributes to be rendered on the page.
     * @return The name of the template to render the list of connections.
     */
    @GetMapping("/listConnections")
    public String showConnectionList(Model model) {
        log.info("Get on listConnections");
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Long userId = userService.getUserIdByEmail(email);
        User userConnected = userService.getUserById(userId).get();
        setAttributeListOfConnections(model, userId);
        return "listConnections";
    }

    /**
     * Handles the search for connections based on a query string. Can return a filtered list of connections
     * or all connections if the query is empty or null.
     *
     * @param model The model for the view where search results will be added.
     * @param query The search query used to filter connections.
     * @return The name of the template to render the search results.
     */
    @GetMapping("/searchConnection")
    public String searchConnection(Model model, @RequestParam(value = "query", required = false) String query) {
        log.info("Get on searchConnection");
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Long userId = userService.getUserIdByEmail(email);

        List<User> searchResults;
        if (query != null && !query.isEmpty()) {
            log.info("Return the result of search");
            searchResults = connectionService.searchConnections(query, userId);
        } else {
            log.info("Query null or empty, return all connections");
            searchResults = connectionService.findAllConnections(userId);
        }
        model.addAttribute("listOfConnections", searchResults);
        return "searchConnection";
    }

    /**
     * Adds a new connection for the currently authenticated user based on the friendId provided.
     *
     * @param friendId The ID of the user to be added as a connection.
     * @param model    The Model object used for adding attributes to be rendered on the view.
     * @return Redirects to the listConnections page with a success or error parameter.
     */
    @GetMapping("/addConnection")
    public String addConnection(@RequestParam("friendId") Long friendId, Model model) {
        log.info("Get on addConnection");
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Long userId = userService.getUserIdByEmail(email);
        boolean resultAddConnection = connectionService.addConnection(userId, friendId);

        if(resultAddConnection) {
            log.info("Success to add connection");
            model.addAttribute("successAddMessage", "Successfully added the connection");
            return "listConnections";
        } else {
            log.error("Error to add connection");
            model.addAttribute("errorAddMessage", "Failed to delete the connection");
            return "listConnections";
        }
    }

    /**
     * Handles the removal of a connection between the current user and another user.
     *
     * @param friendId The ID of the friend (connection) to be removed.
     * @param model    The Model object used for adding attributes to be rendered on the view.
     * @return The view name to be rendered after processing the removal of the connection.
     */
    @PostMapping("/removeConnection")
    public String removeConnection(@RequestParam("friendId") Long friendId, Model model) {
        log.info("DELETE on removeConnection");
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Long userId = userService.getUserIdByEmail(email);

        boolean resultRemoveConnection = connectionService.removeConnection(userId, friendId);

        setAttributeListOfConnections(model, userId);

        if (resultRemoveConnection) {
            log.info("Success to remove connection");
            model.addAttribute("successRemoveMessage", "Successful connection deletion");
            return "listConnections";
        } else {
            log.error("Error to remove connection");
            model.addAttribute("errorRemoveMessage", "Connection deletion failed");
            return "listConnections";
        }
    }

    /**
     * Retrieves the list of active connections for the specified user and adds it to the Model.
     *
     * @param model   The Model object to which the list of connections will be added.
     * @param userId  The ID of the user for whom the list of connections needs to be retrieved.
     * @return The updated Model object with the list of connections added, or the original Model object if the retrieval fails.
     */
    public Model setAttributeListOfConnections(Model model, Long userId){
        User userConnected = userService.getUserById(userId).get();
        List<ConnectionDTO> listOfConnections = userService.getActiveFriends(userConnected.getFriends());
        if(listOfConnections == null) {
            log.error("Error when displaying the list of connections on listConnections template");
        } else {
            log.info("Success when displaying the list of connections on listConnections template");
             model.addAttribute("listOfConnections", listOfConnections);
        }
        return model;
    }

}
