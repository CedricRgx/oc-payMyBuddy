package com.openclassrooms.paymybuddy.controller;

import com.openclassrooms.paymybuddy.model.DTO.DisplayTransfertsDTO;
import com.openclassrooms.paymybuddy.service.impl.TransfertService;
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
public class TransfertController {

    @Autowired
    private UserService userService;

    @Autowired
    private TransfertService transfertService;

    /* viewTransfertPage method with pagination ##DON'T WORK
    @GetMapping("/transfert")
    public String viewTransfertPage(Model model, @RequestParam(name="page", defaultValue = "1") int p, @RequestParam(name="size", defaultValue = "3") int s) throws Exception {
        log.info("transfert template");
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Long userId = userService.getUserIdByEmail(email);
        Pageable pageable = PageRequest.of(p, s);
        Page<DisplayTransfertsDTO> pageTransfertsDTO = transfertService.getListOfTransfertsPageable(userId, pageable);
        List<String> listOfConnections = transfertService.getListOfConnections(userId);
        if(listOfConnections == null) {
            log.info("Error when displaying the list of connections on transfert template");
        } else if(pageTransfertsDTO == null) {
            log.info("Error when displaying the list of transferts on transfert template");
        } else {
            log.info("Success in displaying on transfert template");
            model.addAttribute("listOfConnections", listOfConnections);
            //model.addAttribute("listTransfertsDTO", listTransfertsDTO);
            model.addAttribute("pages", new int[pageTransfertsDTO.getTotalPages()]);
            model.addAttribute("currentPage", p);
        }
        return "transfert";
    }
    */

    @GetMapping("/transfert")
    public String viewTransfertPage(Model model, @RequestParam(name="page", defaultValue = "0") int page, @RequestParam(name="size", defaultValue = "3") int size) throws Exception {
        log.info("transfert template");
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Long userId = userService.getUserIdByEmail(email);

        List<String> listOfConnections = transfertService.getListOfConnections(userId);
        List<DisplayTransfertsDTO> listTransfertsDTO = transfertService.getListOfTransferts(userId, page, size);
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

}
