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

/**
 * Controller responsible for managing the user's profile in the PayMyBuddy application.
 */
@Controller
@Slf4j
public class ProfileController {

    @Autowired
    ProfileService profileService;

    @Autowired
    UserService userService;

    /**
     * Displays the current user's profile page. Retrieves profile data based on the authenticated user's email
     * and adds it to the model for view rendering.
     *
     * @param model The model to which profile data is added.
     * @return The name of the template for displaying the user's profile
     */
    @GetMapping("/profile")
    public String viewProfilePage(Model model){
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

    /**
     * Shows the form for editing the current user's profile. Pre-populates the form with existing user profile data.
     *
     * @param model The model to which the profile data for editing is added.
     * @return The name of the template for editing the user's profile.
     */
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

    /**
     * Processes the submission of the profile edit form. Validates the provided profile data and updates the user's
     * profile if validation passes. If validation fails, the form is re-displayed with error messages.
     *
     * @param profileDTO The ProfileDTO object containing the updated profile information.
     * @param result Contains binding result errors related to form validation.
     * @param model The model to which attributes can be added, including validation errors.
     * @return Redirects to the profile page if the update is successful, otherwise returns to the edit form.
     */
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
