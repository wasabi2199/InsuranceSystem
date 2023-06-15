
package sk.stuba.fei.uim.oop.zadanie3.insurancesystem.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import sk.stuba.fei.uim.oop.zadanie3.insurancesystem.domain.AccidentInsurance;
import sk.stuba.fei.uim.oop.zadanie3.insurancesystem.domain.Contract;
import sk.stuba.fei.uim.oop.zadanie3.insurancesystem.domain.HomeInsurance;
import sk.stuba.fei.uim.oop.zadanie3.insurancesystem.domain.User;
import sk.stuba.fei.uim.oop.zadanie3.insurancesystem.domain.enums.PropertyType;
import sk.stuba.fei.uim.oop.zadanie3.insurancesystem.domain.enums.Territory;
import sk.stuba.fei.uim.oop.zadanie3.insurancesystem.service.ContractService;
import sk.stuba.fei.uim.oop.zadanie3.insurancesystem.service.UserService;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@Slf4j
@RequestMapping("/user/id/{userId}/contract/home_insurance")
public class HomeInsuranceController {
    private final UserService userService;
    private final ContractService contractService;

    @Autowired
    public HomeInsuranceController(UserService userService, ContractService contractService) {
        this.userService = userService;
        this.contractService = contractService;
    }

    @GetMapping("/id/{id}")
    public String byId(@PathVariable long userId,@PathVariable long id, Model model) {
        Optional<Contract> optional=contractService.findContractById(id);
        if(optional.isPresent()){
            HomeInsurance homeInsurance=(HomeInsurance) optional.get();
            model.addAttribute("contract", homeInsurance);
        }
        return "contract/homeInsurance/one";
    }


    @GetMapping("/add")
    public String addForm(Model model,@PathVariable long userId) {
        HomeInsuranceResource homeInsuranceResource=new HomeInsuranceResource();
        model.addAttribute("contract", homeInsuranceResource);
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("PropertyType",PropertyType.values());

        return "contract/homeInsurance/add";
    }


@PostMapping("/add")
    public String addSubmit(@PathVariable long userId,@ModelAttribute("contract") @Valid HomeInsuranceResource homeInsuranceResource, BindingResult bindingResult, Model model) {
    if (homeInsuranceResource.getCommencementOfInsurance() != null &&
            homeInsuranceResource.getTerminationOfInsurance() != null &&
            homeInsuranceResource.getCommencementOfInsurance().isAfter(homeInsuranceResource.getTerminationOfInsurance())) {
        bindingResult.rejectValue("terminationOfInsurance", "terminationOfInsurance", "Must be after commencement date");
    }
        if (bindingResult.hasErrors()) {
            model.addAttribute("users", userService.getAllUsers());
            model.addAttribute("PropertyType",PropertyType.values());
            return "contract/homeInsurance/add";
        }
    User user= userService.findUserById(userId);

        contractService.createContract(homeInsuranceResource.toHomeInsurance(0,user));
        return  "redirect:/contract/";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable long userId,@PathVariable long id, Model model){
        Optional <Contract> optional=contractService.findContractById(id);
        if(optional.isPresent()){
            HomeInsurance homeInsurance=(HomeInsurance) optional.get();
            HomeInsuranceResource homeInsuranceResource=new HomeInsuranceResource(homeInsurance);
            model.addAttribute("contract", homeInsuranceResource);
            model.addAttribute("users", userService.getAllUsers());
            model.addAttribute("PropertyType", PropertyType.values());
            model.addAttribute("id",homeInsurance.getId());
        }
        return "contract/homeInsurance/update";
    }


@PostMapping("/update/{id}")
    public String submit(@PathVariable long userId,@ModelAttribute("contract") @Valid HomeInsuranceResource homeInsuranceResource, BindingResult bindingResult, @PathVariable long id ) {
    if (homeInsuranceResource.getCommencementOfInsurance() != null &&
            homeInsuranceResource.getTerminationOfInsurance() != null &&
            homeInsuranceResource.getCommencementOfInsurance().isAfter(homeInsuranceResource.getTerminationOfInsurance())) {
        bindingResult.rejectValue("terminationOfInsurance", "terminationOfInsurance", "Must be after commencement date");
    }
        if (bindingResult.hasErrors()) {
            return "contract/homeInsurance/update";
        }User user= userService.findUserById(userId);
        HomeInsurance homeInsurance=homeInsuranceResource.toHomeInsurance(id,user);
        contractService.updateContract(homeInsurance);
        return "redirect:/contract/";
    }

}

