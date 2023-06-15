package sk.stuba.fei.uim.oop.zadanie3;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import sk.stuba.fei.uim.oop.zadanie3.insurancesystem.domain.*;
import sk.stuba.fei.uim.oop.zadanie3.insurancesystem.domain.enums.PropertyType;
import sk.stuba.fei.uim.oop.zadanie3.insurancesystem.domain.enums.Territory;
import sk.stuba.fei.uim.oop.zadanie3.insurancesystem.domain.enums.TravelPurpose;
import sk.stuba.fei.uim.oop.zadanie3.insurancesystem.domain.address.Address;
import sk.stuba.fei.uim.oop.zadanie3.insurancesystem.service.ContractService;
import sk.stuba.fei.uim.oop.zadanie3.insurancesystem.service.UserService;

import java.time.LocalDate;

@Controller
@Slf4j
@SpringBootApplication
public class Zadanie3Application implements CommandLineRunner {

    private final UserService userService;
    private final ContractService contractService;
    @Value("classpath:users.json")
    private Resource usersJson;
    @Value("classpath:contracts.json")
    private Resource contractsJson;

    public Zadanie3Application(UserService userService, ContractService contractService) {
        this.userService = userService;
        this.contractService = contractService;
    }

    public static void main(String[] args) {
        SpringApplication.run(Zadanie3Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        User user=new User("SabinaDaniela","Pekarekova", "1234/567890","mymail@mail.com",
                new Address("7111","Parndorf", "Joseph-Haydn-Gasse", "3/4"));
        userService.newUser(user);
        userService.newUser(new User("Winnie","the Pooh",
                "1111/999999","winnie@mail.com", new Address("123","Aker Wood West",
                "toys corner", "100")));
        userService.newUser(new User("Mug","Coffee",
                "1111/222222","coffeemug@mail.com", new Address("333","Cupboard ", "Upper-shelf",
                "13")));

        Contract travelInsurance=new TravelInsurance(LocalDate.of(2000 ,2 ,2) ,
                user,LocalDate.of(2000 ,2 ,2),LocalDate.of(2000 ,2 ,2),
                200.50,3.2,user,true, TravelPurpose.Recreation);
        contractService.createContract(travelInsurance);

        Contract accidentInsurance= new AccidentInsurance(LocalDate.of(2000 ,2 ,2) ,
                user,LocalDate.of(2000 ,2 ,2),LocalDate.of(2000 ,3,3), 50.5,1.5,
                user,500,200,20.5, Territory.Slovakia);
        contractService.createContract(accidentInsurance);

        Contract homeInsurance= new HomeInsurance(LocalDate.of(2000 ,2 ,2) ,
                userService.findUserById(2),LocalDate.of(2000 ,2 ,2),LocalDate.of(2000 ,3,3), 670.5,1.2,
                PropertyType.Flat, new Address("10","Bratislava ", "Ruzova dolina", "82005"), 500
        ) ;
        contractService.createContract(homeInsurance);
        Contract householdInsurance=new HouseholdInsurance(LocalDate.of(2000 ,2 ,2) ,
                userService.findUserById(2),LocalDate.of(2000 ,2 ,2),LocalDate.of(2000 ,3,3), 670.5,1.2,
                PropertyType.Flat, new Address("10","Bratislava ", "Ruzova dolina", "82005"), 500, 200
        );
        contractService.createContract(householdInsurance);
    }

}
