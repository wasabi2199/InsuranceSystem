package sk.stuba.fei.uim.oop.zadanie3.insurancesystem.domain;

import lombok.Data;

import javax.validation.constraints.*;

import lombok.NoArgsConstructor;
import sk.stuba.fei.uim.oop.zadanie3.insurancesystem.domain.address.Address;
import java.util.*;

public class User extends Entity {
    @NotNull
    @Size(min = 1)
    private String name;
    @NotNull
    @Size(min = 1)
    private String surname;
    @NotNull
    @Size(min = 1)
    private String pid;
    @NotNull
    @Email
    private String email;
    @NotNull
    private Address permanentAdress;
    private Address temporarytAdress;
    private HashMap<Long, Contract> contracts;

    public User( String name, String surname, String pid, String email,
                Address permanentAdress, Address temporarytAdress)
    {
        setName(name);
        setSurname(surname);
        setPid(pid);
        setEmail(email);
        setPermanentAdress(permanentAdress);
        setTemporarytAdress(temporarytAdress);
        contracts = new HashMap<Long, Contract>();
    }

    public User( String name, String surname, String pid, String email,
                Address permanentAdress)
    {
        setName(name);
        setSurname(surname);
        setPid(pid);
        setEmail(email);
        setPermanentAdress(permanentAdress);
        setTemporarytAdress(permanentAdress);
        contracts = new HashMap<Long, Contract>();
    }

    public void addContracts(Contract contract) {this.contracts.put(contract.getId(),contract);}

    public String getFullName(){
        return name+" "+surname;
    }

    public HashMap<Long, Contract> getContracts() {
        return contracts;
    }

    public String getFullPermanentAdress() {
        return permanentAdress.getFullAdress();
    }
    public String getFullTemporaryAdress() {
        return temporarytAdress.getFullAdress();
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPid() {
        return pid;
    }

    public String getEmail() {
        return email;
    }

    public Address getPermanentAdress() {
        return permanentAdress;
    }

    public Address getTemporarytAdress() {
        return temporarytAdress;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPermanentAdress(Address permanentAdress) {
        this.permanentAdress = permanentAdress;
    }

    public void setTemporarytAdress(Address temporarytAdress) {
        this.temporarytAdress = temporarytAdress;
    }

    public void setContracts(HashMap<Long, Contract> contracts) {
        this.contracts = contracts;
    }
}
