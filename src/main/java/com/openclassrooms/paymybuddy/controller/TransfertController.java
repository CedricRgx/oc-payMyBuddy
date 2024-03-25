package com.openclassrooms.paymybuddy.controller;

import com.openclassrooms.paymybuddy.exceptions.UserNotFoundException;
import com.openclassrooms.paymybuddy.model.DTO.ConnectionDTO;
import com.openclassrooms.paymybuddy.model.DTO.TransfertDTO;
import com.openclassrooms.paymybuddy.model.DTO.NewTransfertDTO;
import com.openclassrooms.paymybuddy.model.User;
import com.openclassrooms.paymybuddy.service.impl.TransfertService;
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
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

/**
 * Controller responsible for handling transfer-related operations in the PayMyBuddy application.
 */
@Controller
@Slf4j
public class TransfertController {

    @Autowired
    private UserService userService;

    @Autowired
    private TransfertService transfertService;

    /**
     * Displays the transfer page, showing available connections for initiating transfers and
     * a history of past transfers.
     *
     * @param model The model for passing attributes to the view.
     * @param page The page number for pagination of the transfer history.
     * @param size The number of transfer records to display per page.
     * @return The name of the view template for the transfer page.
     */
    @GetMapping("/transfert")
    public String viewTransfertPage(Model model, @RequestParam(name="page", defaultValue = "0") int page, @RequestParam(name="size", defaultValue = "3") int size) throws Exception {
        log.info("transfert template");
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Long userId = userService.getUserIdByEmail(email);
        User userConnected = userService.getUserById(userId).get();
        List<ConnectionDTO> listOfConnections = userService.getActiveFriends(userConnected.getFriends());

        List<TransfertDTO> listTransfertsDTO = transfertService.getListOfTransferts(userId, page, size);
        int totalTransferts = transfertService.countTransferts(userId);
        int totalPages = (int) Math.ceil((double) totalTransferts / size);

        if(listOfConnections == null) {
            log.info("Error when displaying the list of connections on transfert template");
        } else if(listTransfertsDTO == null) {
            log.info("Error when displaying the list of transferts on transfert template");
        } else {
            log.info("Success in displaying on transfert template");
            model.addAttribute("listOfConnections", listOfConnections);
            model.addAttribute("listTransfertsDTO", listTransfertsDTO);
            model.addAttribute("pages", totalPages);
            model.addAttribute("currentPage", page);
            model.addAttribute("pageSize", size);
        }
        return "transfert";
    }

    /**
     * Processes the submission of a new transfer request.
     *
     * @param newTransfertDTO The DTO containing the data for the new transfer.
     * @param result The result of the validation of the newTransfertDTO.
     * @param model The model for passing attributes to the view, including potential error messages.
     * @return Redirects back to the transfer page upon successful completion, or displays the transfer page
     *         with error messages if validation fails or the transfer cannot be processed.
     */
    @PostMapping("/actionTransfert")
    public String addTransfertPage(@Valid @ModelAttribute("newTransfertDTO") NewTransfertDTO newTransfertDTO, BindingResult result, Model model) throws Exception {
        log.info("Attempting to add transfert on transfert template");
        if(result.hasErrors()){
            model.addAttribute("errorMessage", "Please correct any errors in the form.");
            return "transfert";
        }
        try{
            transfertService.addNewTransfert(newTransfertDTO);
            model.addAttribute("successMessage", "Transfer completed successfully!");
        }catch(UserNotFoundException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "transfert";
        }catch(Exception e) {
            model.addAttribute("errorMessage", "An unexpected error occurred.");
            return "transfert";
        }
        return "redirect:/transfert";
    }
    
}
