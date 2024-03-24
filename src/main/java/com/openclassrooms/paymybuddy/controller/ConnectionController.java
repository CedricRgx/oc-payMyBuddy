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

@Controller
@Slf4j
public class ConnectionController {

    @Autowired
    private UserService userService;

    @Autowired
    private ConnectionService connectionService;

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

    @GetMapping("/searchConnection")
    public String searchConnection(Model model, @RequestParam(value = "query", required = false) String query) {
        log.info("Get on searchConnection");
        List<User> searchResults;
        if (query != null && !query.isEmpty()) {
            log.info("Return the result of search");
            searchResults = connectionService.searchConnections(query);
        } else {
            log.info("Query null or empty, return all connections");
            searchResults = connectionService.findAllConnections();
        }
        model.addAttribute("listOfConnections", searchResults);
        return "searchConnection";
    }

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
