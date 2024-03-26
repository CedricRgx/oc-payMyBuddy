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
import org.springframework.web.bind.annotation.GetMapping;
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
        List<ConnectionDTO> listOfConnections = userService.getActiveFriends(userConnected.getFriends());
        if(listOfConnections == null) {
            log.error("Error when displaying the list of connections on listConnections template");
        } else {
            log.info("Success when displaying the list of connections on listConnections template");
            model.addAttribute("listOfConnections", listOfConnections);
        }
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
     * @return Redirects to the listConnections page with a success or error parameter.
     */
    @GetMapping("/addConnection")
    public String addConnection(@RequestParam("friendId") Long friendId) {
        log.info("Get on addConnection");
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Long userId = userService.getUserIdByEmail(email);
        boolean resultAddConnection = connectionService.addConnection(userId, friendId);

        if(resultAddConnection){
            log.info("Success to add connection");
            return "redirect:/listConnections?success=true&action=add";
        } else {
            log.error("Error to add connection");
            return "redirect:/listConnections?error=true&action=add";
        }
    }

    /**
     * Removes a connection for the currently authenticated user based on the friendId provided.
     *
     * @param friendId The ID of the connection to be removed.
     * @return Redirects to the listConnections page with a success or error parameter.
     */
    @GetMapping("/removeConnection")
    public String removeConnection(@RequestParam("friendId") Long friendId){
        log.info("Get on removeConnection");
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Long userId = userService.getUserIdByEmail(email);
        boolean resultRemoveConnection = connectionService.removeConnection(userId, friendId);

        if(resultRemoveConnection) {
            log.info("Success to remove connection");
            return "redirect:/listConnections?success=true&action=remove";
        } else {
            log.error("Error to remove connection");
            return "redirect:/listConnections?error=true&action=remove";
        }
    }
}
