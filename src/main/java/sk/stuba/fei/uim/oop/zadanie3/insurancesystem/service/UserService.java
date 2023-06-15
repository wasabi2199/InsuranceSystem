package sk.stuba.fei.uim.oop.zadanie3.insurancesystem.service;

import org.springframework.stereotype.Service;
import sk.stuba.fei.uim.oop.zadanie3.insurancesystem.domain.Contract;
import sk.stuba.fei.uim.oop.zadanie3.insurancesystem.domain.User;

import java.util.*;

import java.util.LinkedList;

@Service
public interface UserService{

    User newUser(User user);

    User findUserById(long id);

    void updateUser(User changedUser);

    Map<Long, User> getAllUsers();
}
