package com.openclassrooms.paymybuddy.controller;

import com.openclassrooms.paymybuddy.model.DTO.UserDTO;
import com.openclassrooms.paymybuddy.service.impl.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Controller responsible for managing the homepage of the PayMyBuddy application.
 */
@Controller
@Slf4j
public class HomeController {

    @Autowired
    private UserService userService;

    /**
     * Displays the homepage for the PayMyBuddy application. This method retrieves the currently authenticated
     * user's information.
     *
     * @param model The Model object used for adding attributes to be rendered on the view.
     * @return The name of the homepage view template to be rendered.
     */
    @GetMapping("/home")
    public String viewHomePage(Model model){
        log.info("home template");
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        setUserDTOHome(model, email);
        return "home";
    }

    /**
     * Handles the submission of the credit balance form.
     *
     * @param amount The amount to credit.
     * @param model The Model object used for adding attributes to be rendered on the view.
     * @param redirectAttributes Used to add attributes that will be available in the model after the redirect.
     * @return The redirect path after processing the credit balance.
     */
    @PostMapping("/creditBalance")
    public String creditBalance(@RequestParam("creditAmount") double amount, Model model, RedirectAttributes redirectAttributes) {
        log.info("Credit balance: {}", amount);
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        Long userId = userService.getUserIdByEmail(email);
        setUserDTOHome(model, email);

        if(amount<=0){
            redirectAttributes.addFlashAttribute("errorCreditMinusZeroMessage", "Balance credit unsuccessful. The amount must higher than zero.");
        }else{
            userService.creditUserBalance(userId, amount);
            redirectAttributes.addFlashAttribute("successCreditMessage", "Balance credited successfully.");
        }

        return "redirect:home";
    }

    /**
     * Handles the submission of the debit balance form.
     *
     * @param amount The amount to debit.
     * @param model The Model object used for adding attributes to be rendered on the view.
     * @param redirectAttributes Used to add attributes that will be available in the model after the redirect.
     * @return The redirect path after processing the debit balance.
     */
    @PostMapping("/debitBalance")
    public String debitBalance(@RequestParam("debitAmount") double amount, Model model, RedirectAttributes redirectAttributes) {
        log.info("Debit balance: {}", amount);
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        Long userId = userService.getUserIdByEmail(email);
        setUserDTOHome(model, email);
        double balanceAmount = userService.getUserBalance(userId);

        if (amount <= 0) {
            redirectAttributes.addFlashAttribute("errorDebitMinusZeroMessage", "Balance debit unsuccessful. The amount must be higher than zero.");
        } else if(amount > balanceAmount) {
            redirectAttributes.addFlashAttribute("errorDebitLowerThanBalanceMessage", "Balance debit unsuccessful. The amount must be lower than your current balance.");
        } else{
            userService.debitUserBalance(userId, amount);
            redirectAttributes.addFlashAttribute("successDebitMessage", "Balance debited successfully.");
        }

        return "redirect:home";
    }

    /**
     * Retrieves the UserDTO for the specified email and adds it to the Model.
     *
     * @param model The Model object to which the UserDTO will be added.
     * @param email The email of the user for whom the UserDTO needs to be retrieved.
     * @return The updated Model object with the UserDTO added, or the original Model object if the UserDTO retrieval fails.
     */
    public Model setUserDTOHome(Model model, String email){
        UserDTO userDTO = userService.getUserDTOFromUser(email);
        if(userDTO!=null){
            log.info("Success when displaying the userDTO's attributes");
            model.addAttribute("userDTO", userDTO);
        }else{
            log.error("Error when displaying the userDTO's attributes");
        }
        return model;
    }

}
