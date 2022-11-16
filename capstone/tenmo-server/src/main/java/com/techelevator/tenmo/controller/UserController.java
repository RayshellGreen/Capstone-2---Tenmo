package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.UnsatisfiedServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tenmo_user")
public class UserController {

    private UserDao userDao;

    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }

    @GetMapping("")
    public List<User> findAllUsernames() {
        return userDao.findAllUsernames();
    }


 //TODO is this needed?
    @GetMapping("/{username}")
    public int findIdByUsername(String username) {
    return userDao.findIdByUsername(username);
}
    @GetMapping("/user/{username}")
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public  boolean created(String username, String password) {
        return userDao.create(username, password);
    }


}
