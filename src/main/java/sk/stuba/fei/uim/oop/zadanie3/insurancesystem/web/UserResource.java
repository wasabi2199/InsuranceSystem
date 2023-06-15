package sk.stuba.fei.uim.oop.zadanie3.insurancesystem.web;


import lombok.Data;
import lombok.NoArgsConstructor;
import sk.stuba.fei.uim.oop.zadanie3.insurancesystem.domain.Contract;
import sk.stuba.fei.uim.oop.zadanie3.insurancesystem.domain.User;
import sk.stuba.fei.uim.oop.zadanie3.insurancesystem.domain.address.Address;
import sun.rmi.runtime.NewThreadAction;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashMap;
@NoArgsConstructor
@Data
public class UserResource {
    private long id;
    @NotNull
    @Size(min = 1)
    private String name;
    @Size(min = 1)
    @NotNull
    private String surname;
    @NotNull
    @Size(min = 10)
    @Size(min = 10)
    private String pid;
    @NotNull
    @Email
    @Size(min = 1)
    private String email;
    @NotNull
    @Size(min = 1)
    private String permanentPostcode;
    @NotNull
    @Size(min = 1)
    private String permanentTown;
    @NotNull
    @Size(min = 1)
    private String permanentStreet;
    @NotNull
    @Size(min = 1)
    private String permanentNumber;
    private String temporaryPostcode;
    private String temporaryTown;

    private String temporaryStreet;

    private String temporaryNumber;
    private HashMap<Long, Contract> contracts;

    public UserResource(User user) {
        this.id=user.getId();
        this.name = user.getName();
        this.surname = user.getSurname();
        this.pid = user.getPid();
        this.email = user.getEmail();
        this.permanentPostcode = user.getPermanentAdress().getPostcode();
        this.permanentTown = user.getPermanentAdress().getTown();
        this.permanentStreet = user.getPermanentAdress().getStreet();
        this.permanentNumber = user.getPermanentAdress().getNumber();
        this.temporaryPostcode = user.getTemporarytAdress().getPostcode();
        this.temporaryTown = user.getTemporarytAdress().getTown();
        this.temporaryStreet = user.getTemporarytAdress().getStreet();
        this.temporaryNumber = user.getTemporarytAdress().getNumber();
        contracts= new HashMap<>();
        this.contracts = user.getContracts();
    }

    User toUser(long id){

        Address permanentAdress= (new Address( permanentPostcode, permanentTown,permanentStreet,permanentNumber));
        Address temporaryAdress= (new Address( temporaryPostcode, temporaryTown,temporaryStreet,temporaryNumber));
        if(this.temporaryNumber.isEmpty() && this.temporaryStreet.isEmpty() &&
                this.temporaryTown.isEmpty() && this.temporaryPostcode.isEmpty()){
            temporaryAdress.setNumber(permanentNumber);
            temporaryAdress.setPostcode(permanentPostcode);
            temporaryAdress.setStreet(permanentStreet);
            temporaryAdress.setTown(permanentTown);
        }
        User user=new User( this.name,  this.surname,  this.pid,  this.email,
                permanentAdress, temporaryAdress );
        if(id!=0){
            user.setId(id);
        }
        return user;
    }


}
