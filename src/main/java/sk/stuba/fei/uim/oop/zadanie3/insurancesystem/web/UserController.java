package sk.stuba.fei.uim.oop.zadanie3.insurancesystem.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import sk.stuba.fei.uim.oop.zadanie3.insurancesystem.domain.Contract;
import sk.stuba.fei.uim.oop.zadanie3.insurancesystem.domain.User;
import sk.stuba.fei.uim.oop.zadanie3.insurancesystem.service.ContractService;
import sk.stuba.fei.uim.oop.zadanie3.insurancesystem.service.UserService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Optional;

@Controller
@Slf4j
@RequestMapping("/user")

public class UserController {

    private final UserService userService;
    private final ContractService contractService;

    @Autowired
    public UserController(UserService userService,ContractService contractService) {
        this.userService=userService;
        this.contractService=contractService;
    }

    @GetMapping("/")
    public String all(Model model){
        model.addAttribute("contracts", contractService.getAllContracts());
        model.addAttribute("users", userService.getAllUsers());
        return "user/all";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        UserResource userResource =new UserResource();
        model.addAttribute("user", userResource);
        return "user/add";
    }

    @PostMapping("/add")
    public String addSubmit(@ModelAttribute("user") @Valid UserResource userResource, BindingResult bindingResult, Model model) {
        if(!userResource.getTemporaryPostcode().isEmpty() || !userResource.getTemporaryTown().isEmpty()
                || !userResource.getTemporaryStreet().isEmpty() || !userResource.getTemporaryNumber().isEmpty()){
            if(userResource.getTemporaryPostcode().isEmpty()){
                bindingResult.rejectValue("temporaryPostcode" , "temporaryPostcode" ,"must be filled in or the rest of the temporary address must be deleted");
                //return "user/update";
            }
            if(userResource.getTemporaryTown().isEmpty()){
                bindingResult.rejectValue("temporaryTown" , "temporaryTown" ,"must be filled in or the rest of the temporary address must be deleted");
                //return "user/update";
            }
            if(userResource.getTemporaryStreet().isEmpty()){
                bindingResult.rejectValue("temporaryStreet" , "temporaryStreet" ,"must be filled in or the rest of the temporary address must be deleted");
                //return "user/update";
            }
            if(userResource.getTemporaryNumber().isEmpty()){
                bindingResult.rejectValue("temporaryNumber" , "temporaryNumber" ,"must be filled in or the rest of the temporary address must be deleted");
                //return "user/update";
            }}
        if (bindingResult.hasErrors()) {
            model.addAttribute("user", userResource);
            return "user/add";
        }
        userService.newUser(userResource.toUser(0));
        return  "redirect:/user/";
    }

    @GetMapping("/id/{id}")
    public String byId(@PathVariable long id, Model model) {
        User userById = userService.findUserById(id);
        if (userById!=null) {
            User user = userById;
            model.addAttribute("user", user);
            model.addAttribute("contracts", contractService.getAllUsersContracts(user));
        }
        return "user/one";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable long id, Model model) {
        UserResource userResource=new UserResource(userService.findUserById(id));
        model.addAttribute("userResource", userResource);
        return "user/update";
    }

    @PostMapping("/update/{id}")
    public String submit(@ModelAttribute @Valid UserResource userResource,BindingResult bindingResult , @PathVariable long id) {

        if(!userResource.getTemporaryPostcode().isEmpty() || !userResource.getTemporaryTown().isEmpty()
                || !userResource.getTemporaryStreet().isEmpty() || !userResource.getTemporaryNumber().isEmpty()){
            if(userResource.getTemporaryPostcode().isEmpty()){
                bindingResult.rejectValue("temporaryPostcode" , "temporaryPostcode" ,"must be filled in or the rest of the temporary address must be deleted");
                //return "user/update";
            }
            if(userResource.getTemporaryTown().isEmpty()){
                bindingResult.rejectValue("temporaryTown" , "temporaryTown" ,"must be filled in or the rest of the temporary address must be deleted");
                //return "user/update";
            }
            if(userResource.getTemporaryStreet().isEmpty()){
                bindingResult.rejectValue("temporaryStreet" , "temporaryStreet" ,"must be filled in or the rest of the temporary address must be deleted");
                //return "user/update";
            }
            if(userResource.getTemporaryNumber().isEmpty()){
                bindingResult.rejectValue("temporaryNumber" , "temporaryNumber" ,"must be filled in or the rest of the temporary address must be deleted");
                //return "user/update";
            }}
        if (bindingResult.hasErrors()) {
            return "user/update";
        }
        User user=userResource.toUser(id);
        user.setContracts(userService.findUserById(id).getContracts());
        userService.updateUser(user);
        return "redirect:/user/";
    }

}
