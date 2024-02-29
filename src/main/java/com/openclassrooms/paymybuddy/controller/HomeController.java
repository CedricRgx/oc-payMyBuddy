package com.openclassrooms.paymybuddy.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class HomeController {

    @GetMapping("/home")
    public String viewHomePage(Model model){
        log.info("home template");
        return "home";
    }

    /*public String default() {
        log.info("Redirection to home template");
        return "redirect:/home";
    }*/
}
