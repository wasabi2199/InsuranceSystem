
package sk.stuba.fei.uim.oop.zadanie3.insurancesystem.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import sk.stuba.fei.uim.oop.zadanie3.insurancesystem.domain.AccidentInsurance;
import sk.stuba.fei.uim.oop.zadanie3.insurancesystem.domain.Contract;
import sk.stuba.fei.uim.oop.zadanie3.insurancesystem.domain.User;
import sk.stuba.fei.uim.oop.zadanie3.insurancesystem.domain.enums.Territory;
import sk.stuba.fei.uim.oop.zadanie3.insurancesystem.service.ContractService;
import sk.stuba.fei.uim.oop.zadanie3.insurancesystem.service.UserService;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@Slf4j
@RequestMapping("/user/id/{userId}/contract/accident_insurance")

public class AccidentInsuranceController {

    private final UserService userService;
    private final ContractService contractService;

    @Autowired
    public AccidentInsuranceController(UserService userService,ContractService contractService) {
        this.userService=userService;
        this.contractService=contractService;
    }


    @GetMapping("/id/{id}")
    public String byId(@PathVariable long userId,@PathVariable long id, Model model) {
        Optional <Contract> optional=contractService.findContractById(id);
        if(optional.isPresent()){
            AccidentInsurance accidentInsurance=(AccidentInsurance) optional.get();
            model.addAttribute("contract", accidentInsurance);
        }
        return "contract/accidentInsurance/one";
    }

    @GetMapping("/add")
    public String addForm(@PathVariable long userId, Model model) {
        AccidentInsuranceResource accidentInsuranceResource=new AccidentInsuranceResource();
        model.addAttribute("contract", accidentInsuranceResource);
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("Territory",Territory.values());
        return "contract/accidentInsurance/add";
    }

 @PostMapping("/add")
    public String addSubmit(@PathVariable long userId,@ModelAttribute("contract") @Valid AccidentInsuranceResource accidentInsuranceResource, BindingResult bindingResult, Model model) {
     if (accidentInsuranceResource.getCommencementOfInsurance() != null &&
             accidentInsuranceResource.getTerminationOfInsurance() != null &&
             accidentInsuranceResource.getCommencementOfInsurance().isAfter(accidentInsuranceResource.getTerminationOfInsurance())) {
         bindingResult.rejectValue("terminationOfInsurance", "terminationOfInsurance", "Must be after commencement date");
     }
        if (bindingResult.hasErrors()) {
            model.addAttribute("users", userService.getAllUsers());
            model.addAttribute("Territory",Territory.values());
            return "contract/accidentInsurance/add";
        }
        User user= userService.findUserById(userId);
        User insured= userService.findUserById(accidentInsuranceResource.getInsuredId());
        contractService.createContract(accidentInsuranceResource.toAccidentInsurance(0, insured,user));
        return  "redirect:/contract/";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable long userId,@PathVariable long id, Model model){
        Optional <Contract> optional=contractService.findContractById(id);
        if(optional.isPresent()){
            AccidentInsurance accidentInsurance=(AccidentInsurance) optional.get();
            AccidentInsuranceResource accidentInsuranceResource=new AccidentInsuranceResource(accidentInsurance);
            model.addAttribute("contract", accidentInsuranceResource);
           model.addAttribute("users", userService.getAllUsers());
            model.addAttribute("Territory", Territory.values());
            model.addAttribute("id",accidentInsurance.getId());
        }
        return "contract/accidentInsurance/update";
    }


@PostMapping("/update/{id}")
    public String submit(@PathVariable long userId,@ModelAttribute("contract") @Valid AccidentInsuranceResource accidentInsuranceResource, BindingResult bindingResult, @PathVariable long id ) {
    if (accidentInsuranceResource.getCommencementOfInsurance() != null &&
            accidentInsuranceResource.getTerminationOfInsurance() != null &&
            accidentInsuranceResource.getCommencementOfInsurance().isAfter(accidentInsuranceResource.getTerminationOfInsurance())) {
        bindingResult.rejectValue("terminationOfInsurance", "terminationOfInsurance", "Must be after commencement date");
    }
        if (bindingResult.hasErrors()) {
            return "contract/accidentInsurance/update";
        }  User user= userService.findUserById(userId);
         User insured= userService.findUserById(accidentInsuranceResource.getInsuredId());
        contractService.updateContract(accidentInsuranceResource.toAccidentInsurance(id,  insured,user));
        return "redirect:/contract/";
    }

}

