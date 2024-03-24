package com.openclassrooms.paymybuddy.controller;

import com.openclassrooms.paymybuddy.model.User;
import com.openclassrooms.paymybuddy.service.impl.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@Slf4j
public class SearchConnectionController {

    @Autowired
    private UserService userService;

    @GetMapping("/searchConnection")
    public String searchConnection(Model model, @RequestParam(value = "query", required = false) String query) {
        List<User> searchResults;
        if (query != null && !query.isEmpty()) {
            searchResults = userService.searchConnections(query);
        } else {
            searchResults = userService.findAllConnections();
        }
        model.addAttribute("listOfConnections", searchResults);
        return "searchConnection";
    }
}
