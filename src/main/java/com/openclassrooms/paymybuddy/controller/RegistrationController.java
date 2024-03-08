package com.openclassrooms.paymybuddy.controller;

import com.openclassrooms.paymybuddy.exceptions.EmailAlreadyUsedException;
import com.openclassrooms.paymybuddy.model.AppAccount;
import com.openclassrooms.paymybuddy.model.DTO.RegisterDTO;
import com.openclassrooms.paymybuddy.model.User;
import com.openclassrooms.paymybuddy.model.UserAccount;
import com.openclassrooms.paymybuddy.repository.UserAccountRepository;
import com.openclassrooms.paymybuddy.service.impl.AppAccountService;
import com.openclassrooms.paymybuddy.service.impl.RegisterService;
import com.openclassrooms.paymybuddy.service.impl.UserAccountService;
import com.openclassrooms.paymybuddy.service.impl.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import java.time.LocalDateTime;

@Controller
@Slf4j
public class RegistrationController {

    @Autowired
    private RegisterService registerService;

    @GetMapping("/registration")
    public String viewRegistrationForm(Model model){
        log.info("registrationForm template");
        RegisterDTO registerDTO = RegisterDTO.builder().build();
        model.addAttribute("registerDTO", registerDTO);
        return "registrationForm";
    }

    @PostMapping("/registration")
    public String addUser(@Valid @ModelAttribute("registerDTO") RegisterDTO registerDTO, BindingResult result, Model model){
        log.info("inscription d'un utilisateur");
        if(result.hasErrors()){
            return "registrationForm";
        }
        try{
            registerService.addUser(registerDTO);
        }catch(EmailAlreadyUsedException e) {
            model.addAttribute("errorEmail", e.getMessage());
            log.error("This email is already in use: {}", e.getMessage());
            return "registrationForm";
        }catch(Exception e){
            model.addAttribute("error", "An error occurs");
            log.error("error", e);
            return "registrationForm";
        }
        return "redirect:/login";
    }
}
