
package sk.stuba.fei.uim.oop.zadanie3.insurancesystem.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import sk.stuba.fei.uim.oop.zadanie3.insurancesystem.domain.AccidentInsurance;
import sk.stuba.fei.uim.oop.zadanie3.insurancesystem.domain.Contract;
import sk.stuba.fei.uim.oop.zadanie3.insurancesystem.domain.HouseholdInsurance;
import sk.stuba.fei.uim.oop.zadanie3.insurancesystem.domain.User;
import sk.stuba.fei.uim.oop.zadanie3.insurancesystem.domain.enums.PropertyType;
import sk.stuba.fei.uim.oop.zadanie3.insurancesystem.domain.enums.Territory;
import sk.stuba.fei.uim.oop.zadanie3.insurancesystem.service.ContractService;
import sk.stuba.fei.uim.oop.zadanie3.insurancesystem.service.UserService;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@Slf4j
@RequestMapping("/user/id/{userId}/contract/household_insurance")
public class HouseholdInsuranceController {
    private final UserService userService;
    private final ContractService contractService;
    @Autowired
    public HouseholdInsuranceController(UserService userService, ContractService contractService) {
        this.userService = userService;
        this.contractService = contractService;
    }

    @GetMapping("/id/{id}")
    public String byId(@PathVariable long userId, @PathVariable long id, Model model) {
        Optional<Contract> optional=contractService.findContractById(id);
        if(optional.isPresent()){
            HouseholdInsurance householdInsurance=(HouseholdInsurance) optional.get();
            model.addAttribute("contract", householdInsurance);
        }
        return "contract/householdInsurance/one";
    }

    @GetMapping("/add")
    public String addForm(@PathVariable long userId, Model model) {
        HouseholdInsuranceResource householdInsuranceResource=new HouseholdInsuranceResource();
        model.addAttribute("contract", householdInsuranceResource);
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("PropertyType",PropertyType.values());
        return "contract/householdInsurance/add";
    }


@PostMapping("/add")
    public String addSubmit(@PathVariable long userId,@ModelAttribute("contract") @Valid HouseholdInsuranceResource householdInsuranceResource, BindingResult bindingResult, Model model) {
    if (householdInsuranceResource.getCommencementOfInsurance() != null &&
            householdInsuranceResource.getTerminationOfInsurance() != null &&
            householdInsuranceResource.getCommencementOfInsurance().isAfter(householdInsuranceResource.getTerminationOfInsurance())) {
        bindingResult.rejectValue("terminationOfInsurance", "terminationOfInsurance", "Must be after commencement date");
    }
        if (bindingResult.hasErrors()) {
            model.addAttribute("users", userService.getAllUsers());
            model.addAttribute("PropertyType",PropertyType.values());
            return "contract/householdInsurance/add";
        }
         User user= userService.findUserById(userId);
        contractService.createContract(householdInsuranceResource.toHouseholdInsurance(0,user));
        return  "redirect:/contract/";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable long userId,@PathVariable long id, Model model){
        Optional <Contract> optional=contractService.findContractById(id);
        if(optional.isPresent()){
            HouseholdInsurance householdInsurance=(HouseholdInsurance) optional.get();
            HouseholdInsuranceResource householdInsuranceResource=new HouseholdInsuranceResource(householdInsurance);
            model.addAttribute("contract", householdInsuranceResource);
            model.addAttribute("users", userService.getAllUsers());
            model.addAttribute("PropertyType", PropertyType.values());
            model.addAttribute("id",householdInsurance.getId());
        }
        return "contract/householdInsurance/update";
    }


    @PostMapping("/update/{id}")
    public String submit(@PathVariable long userId,@ModelAttribute("contract") @Valid HouseholdInsuranceResource householdInsuranceResource,BindingResult bindingResult, @PathVariable long id ) {
        if (householdInsuranceResource.getCommencementOfInsurance() != null &&
                householdInsuranceResource.getTerminationOfInsurance() != null &&
                householdInsuranceResource.getCommencementOfInsurance().isAfter(householdInsuranceResource.getTerminationOfInsurance())) {
            bindingResult.rejectValue("terminationOfInsurance", "terminationOfInsurance", "Must be after commencement date");
        }
        if (bindingResult.hasErrors()) {
            return "contract/householdInsurance/update";
        }User user= userService.findUserById(userId);
        HouseholdInsurance householdInsurance=householdInsuranceResource.toHouseholdInsurance(id,user);
        contractService.updateContract(householdInsurance);
        return "redirect:/contract/";
    }



}

