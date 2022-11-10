package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.User;

import java.util.List;

public interface UserDao {

    List<User> findAllUsernames();

    List<String> listAllUsernames();

//    List<User> getAllUsernames();

    User findByUsername(String username);

    int findIdByUsername(String username);

    boolean create(String username, String password);
}
