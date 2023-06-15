
package sk.stuba.fei.uim.oop.zadanie3.insurancesystem.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sk.stuba.fei.uim.oop.zadanie3.insurancesystem.domain.Contract;
import sk.stuba.fei.uim.oop.zadanie3.insurancesystem.service.ContractService;
import sk.stuba.fei.uim.oop.zadanie3.insurancesystem.service.UserService;


@Controller
@Slf4j
@RequestMapping("/")
public class ContractController {

    private final ContractService contractService;
    private final UserService userService;

    @Autowired
    public ContractController(ContractService contractService,UserService userService) {
        this.contractService=contractService;
        this.userService=userService;
    }

    @GetMapping("/contract/")
    public String all(Model model){

        model.addAttribute("contracts", contractService.getAllContracts());
        model.addAttribute("users", userService.getAllUsers());
        return "contract/all";
    }
    @GetMapping("/user/id/{userId}/contractSelection")
    public String selection(@PathVariable long userId, Model model){
        model.addAttribute("contracts", contractService.getAllContracts());
        model.addAttribute("users", userService.getAllUsers());
        return "contract/contractSelection";
    }



}

