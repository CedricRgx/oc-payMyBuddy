package com.openclassrooms.paymybuddy.controller;

import com.openclassrooms.paymybuddy.model.DTO.ConnectionDTO;
import com.openclassrooms.paymybuddy.model.DTO.TransfertDTO;
import com.openclassrooms.paymybuddy.model.DTO.NewTransfertDTO;
import com.openclassrooms.paymybuddy.model.User;
import com.openclassrooms.paymybuddy.repository.TransfertRepository;
import com.openclassrooms.paymybuddy.service.impl.TransfertService;
import com.openclassrooms.paymybuddy.service.impl.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

    @Autowired
    private TransfertRepository transfertRepository;

    /**
     * Displays the transfer page, showing available connections for initiating transfers and
     * a history of past transfers.
     *
     * @param model The model for passing attributes to the view
     * @param session Session of the user
     * @param page The page number for pagination of the transfer history.
     * @param size The number of transfer records to display per page.
     * @return The name of the view template for the transfer page.
     */
    @GetMapping("/transfert")
    public String viewTransfertPage(Model model, HttpSession session, @RequestParam(name="page", defaultValue = "0") int page, @RequestParam(name="size", defaultValue = "3") int size) {
        log.info("transfert template");
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Long userId = userService.findByEmail(email).get().getUserId();
        User userConnected = userService.getUserById(userId).get();
        List<ConnectionDTO> listOfConnections = userService.getActiveFriends(userConnected.getFriends());

        Page<TransfertDTO> listTransfertsDTO = transfertService.getListOfTransferts(userId, page, size);
        int totalTransferts = transfertRepository.countByUser(userId);
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

        String errorMessage = (String) session.getAttribute("errorMessage");
        String successMessage = (String) session.getAttribute("successMessage");

        if (errorMessage != null) {
            model.addAttribute("errorMessage", errorMessage);
            session.removeAttribute("errorMessage");
        }
        if (successMessage != null) {
            model.addAttribute("successMessage", successMessage);
            session.removeAttribute("successMessage");
        }

        return "transfert";
    }

    /**
     * Adds a new transfer based on the provided transfer DTO, after validating the transfer details.
     *
     * @param newTransfertDTO The DTO containing the details of the new transfer.
     * @param result          The BindingResult object that holds the result of the validation.
     * @param model           The Model object for adding attributes to the view.
     * @param session         The HttpSession object for managing session attributes.
     * @return The name of the view template to redirect to (in this case, "transfert").
     * @throws Exception if an unexpected error occurs during the transfer addition process.
     */
    @PostMapping("/actionTransfert")
    public String addTransfertPage(@Valid @ModelAttribute("newTransfertDTO") NewTransfertDTO newTransfertDTO, BindingResult result, Model model, HttpSession session) throws Exception {
        log.info("Attempting to add transfert on transfert template");
        if(result.hasErrors()){
            model.addAttribute("errorMessage", "Please correct any errors in the form.");
            return "transfert";
        }
        boolean transferSuccess = false;
        try {
            transferSuccess = transfertService.addNewTransfert(newTransfertDTO);
        } catch (Exception e) {
            log.info("An unexpected error occurred");
            model.addAttribute("errorMessage", "An unexpected error occurred.");
            return "transfert";
        }

        if (transferSuccess) {
            log.info("Transfer completed successfully!");
            session.setAttribute("successMessage", "Transfer completed successfully!");
        } else {
            log.info("Insufficient balance to make the transfer");
            session.setAttribute("errorMessage", "Insufficient balance to make the transfer");
        }
        return "redirect:/transfert";
    }
    
}
