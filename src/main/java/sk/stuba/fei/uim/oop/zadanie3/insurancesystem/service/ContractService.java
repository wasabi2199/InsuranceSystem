package sk.stuba.fei.uim.oop.zadanie3.insurancesystem.service;

import org.springframework.stereotype.Service;
import sk.stuba.fei.uim.oop.zadanie3.insurancesystem.domain.Contract;
import sk.stuba.fei.uim.oop.zadanie3.insurancesystem.domain.User;

import java.util.*;

@Service
public interface ContractService {
   void updateContract(Contract contract);
   Contract createContract(Contract contract);
   HashMap<Long,Contract> getAllUsersContracts(User user);
   HashMap<Long,Contract> getAllContracts();
   Optional<Contract> findContractById(long id);

}
