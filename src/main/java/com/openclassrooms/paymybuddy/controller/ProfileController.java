package com.openclassrooms.paymybuddy.controller;

import com.openclassrooms.paymybuddy.model.DTO.ProfileDTO;
import com.openclassrooms.paymybuddy.service.impl.ProfileService;
import com.openclassrooms.paymybuddy.service.impl.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class ProfileController {

    @Autowired
    ProfileService profileService;

    @Autowired
    UserService userService;

    @GetMapping("/profile")
    public String viewProfilePage(Model model) throws Exception {
        log.info("profile template");
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Long userId = userService.getUserIdByEmail(email);
        ProfileDTO profileDTO = profileService.getProfile(userId);
        if(profileDTO==null){
            log.error("Profile not found");
            return "profile";
        }
        model.addAttribute("profileDTO", profileDTO);
        return "profile";
    }

    @GetMapping("/editProfile")
    public String showEditProfileForm(Model model){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Long userId = userService.getUserIdByEmail(email);
        ProfileDTO profileDTO = profileService.getProfile(userId);
        if (profileDTO == null) {
            log.error("Profile not found for editing");
            return "redirect:/profile";
        }
        model.addAttribute("profileDTO", profileDTO);
        return "editProfile";
    }

    @PostMapping("/editProfile")
    public String updateProfile(@Valid @ModelAttribute("profileDTO") ProfileDTO profileDTO, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "editProfile";
        }
        try {
            profileService.saveProfile(profileDTO);
        } catch (Exception e) {
            model.addAttribute("error", "An error occurs during profile update");
            log.error("Error during profile update", e);
            return "editProfile";
        }
        return "redirect:/profile";
    }
}
