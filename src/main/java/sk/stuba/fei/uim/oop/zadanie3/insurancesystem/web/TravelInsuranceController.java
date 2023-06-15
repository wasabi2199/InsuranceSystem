package sk.stuba.fei.uim.oop.zadanie3.insurancesystem.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import sk.stuba.fei.uim.oop.zadanie3.insurancesystem.domain.AccidentInsurance;
import sk.stuba.fei.uim.oop.zadanie3.insurancesystem.domain.Contract;
import sk.stuba.fei.uim.oop.zadanie3.insurancesystem.domain.TravelInsurance;
import sk.stuba.fei.uim.oop.zadanie3.insurancesystem.domain.User;
import sk.stuba.fei.uim.oop.zadanie3.insurancesystem.domain.enums.Territory;
import sk.stuba.fei.uim.oop.zadanie3.insurancesystem.domain.enums.TravelPurpose;
import sk.stuba.fei.uim.oop.zadanie3.insurancesystem.service.ContractService;
import sk.stuba.fei.uim.oop.zadanie3.insurancesystem.service.UserService;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@Slf4j
@RequestMapping("/user/id/{userId}/contract/travel_insurance")
public class TravelInsuranceController {
    private final UserService userService;
    private final ContractService contractService;

    @Autowired
    public TravelInsuranceController(UserService userService, ContractService contractService) {
        this.userService = userService;
        this.contractService = contractService;
    }


    @GetMapping("/id/{id}")
    public String byId(@PathVariable long userId,@PathVariable long id, Model model) {
        Optional<Contract> optional=contractService.findContractById(id);
        if(optional.isPresent()){
            TravelInsurance travelInsurance =(TravelInsurance) optional.get();
           // TravelInsuranceResource travelInsuranceResource=new TravelInsuranceResource(travelInsurance);
            model.addAttribute("contract", travelInsurance);
         //   model.addAttribute("users", userService.getAllUsers());
          //  model.addAttribute("TravelPurpose", TravelPurpose.values());
         //   model.addAttribute("id",travelInsurance.getId());
        }
        return "contract/travelInsurance/one";
    }



    @GetMapping("/add")
    public String addForm(@PathVariable long userId,Model model) {
        TravelInsuranceResource travelInsuranceResource=new TravelInsuranceResource();
        model.addAttribute("contract", travelInsuranceResource);
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("TravelPurpose",TravelPurpose.values());
        return "contract/travelInsurance/add";
    }

    @PostMapping("/add")
    public String addSubmit(@PathVariable long userId,@ModelAttribute("contract") @Valid TravelInsuranceResource travelInsuranceResource, BindingResult bindingResult, Model model) {
        if (travelInsuranceResource.getCommencementOfInsurance() != null &&
                travelInsuranceResource.getTerminationOfInsurance() != null &&
                travelInsuranceResource.getCommencementOfInsurance().isAfter(travelInsuranceResource.getTerminationOfInsurance())) {
            bindingResult.rejectValue("terminationOfInsurance", "terminationOfInsurance", "Must be after commencement date");
        }
        if (bindingResult.hasErrors()) {
            model.addAttribute("users", userService.getAllUsers());
            model.addAttribute("TravelPurpose",TravelPurpose.values());
            return "contract/travelInsurance/add";
        }
        User user= userService.findUserById(userId);
        User insured= userService.findUserById(travelInsuranceResource.getInsuredId());
        contractService.createContract(travelInsuranceResource.toTravelInsurance(0,insured,user));
        return  "redirect:/contract/";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable long userId,@PathVariable long id, Model model){
        Optional <Contract> optional=contractService.findContractById(id);
        if(optional.isPresent()){
            TravelInsurance travelInsurance=(TravelInsurance) optional.get();
            TravelInsuranceResource travelInsuranceResource=new TravelInsuranceResource(travelInsurance);
            model.addAttribute("contract", travelInsuranceResource);
            model.addAttribute("users", userService.getAllUsers());
            model.addAttribute("TravelPurpose", TravelPurpose.values());
            model.addAttribute("id",travelInsurance.getId());
        }
        return "contract/travelInsurance/update";
    }

    @PostMapping("/update/{id}")
    public String submit(@PathVariable long userId,@ModelAttribute("contract") @Valid TravelInsuranceResource travelInsuranceResource, BindingResult bindingResult , @PathVariable long id) {
        if (travelInsuranceResource.getCommencementOfInsurance() != null &&
                travelInsuranceResource.getTerminationOfInsurance() != null &&
                travelInsuranceResource.getCommencementOfInsurance().isAfter(travelInsuranceResource.getTerminationOfInsurance())) {
            bindingResult.rejectValue("terminationOfInsurance", "terminationOfInsurance", "Must be after commencement date");
        }
        if (bindingResult.hasErrors()) {
            return "/contract/travelInsurance/update";
        }
        User user= userService.findUserById(userId);
        User insured= userService.findUserById(travelInsuranceResource.getInsuredId());
        TravelInsurance travelInsurance=travelInsuranceResource.toTravelInsurance(id,insured,user);
        contractService.updateContract(travelInsurance);
        return "redirect:/contract/";
    }
}
