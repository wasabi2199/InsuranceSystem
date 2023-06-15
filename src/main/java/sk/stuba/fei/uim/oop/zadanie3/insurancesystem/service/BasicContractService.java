package sk.stuba.fei.uim.oop.zadanie3.insurancesystem.service;

import org.springframework.stereotype.Service;
import sk.stuba.fei.uim.oop.zadanie3.insurancesystem.domain.Contract;
import sk.stuba.fei.uim.oop.zadanie3.insurancesystem.domain.User;

import java.util.*;


@Service
public class BasicContractService implements ContractService, UserService {
    private HashMap<Long, User> users;
    private HashMap<Long, Contract>  contracts;

    public BasicContractService() {
        this.users = new HashMap<>();
        this.contracts =new HashMap<>();
    }

    public Map<Long, User> getUsers() {
        return users;
    }

    public Map<Long, Contract> getContracts() {
        return contracts;
    }

    @Override
    public User newUser(User user)
    {
        users.put(user.getId(), user);
        return user;
    }

    @Override
    public void updateUser(User user) {
        this.users.put(user.getId(),user);
    }

    @Override
    public User findUserById(long id){
        return users.get(id);
    }

    @Override
    public Contract createContract(Contract contract){
        this.contracts.put(contract.getId(),contract);
       contract.getInsurer().addContracts(contract);
        return contract;
    }

    @Override
    public void updateContract(Contract contract) {
        this.contracts.put(contract.getId(),contract);
        contract.getInsurer().addContracts(contract);
    }

    @Override
    public HashMap<Long,Contract> getAllUsersContracts(User user){
       return user.getContracts();
    }

    @Override
    public HashMap<Long, User> getAllUsers(){
        return  users;
    }

    @Override
    public HashMap<Long,Contract> getAllContracts(){
        return contracts;
    }

    @Override
    public Optional<Contract> findContractById(long id) {
        return Optional.ofNullable(contracts.get(id));
    }



}
