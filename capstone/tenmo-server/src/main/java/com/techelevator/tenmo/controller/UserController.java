package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("")
    public List<User> findAllUsers() {
        return userDao.findAllUsers();
    }


    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/{username}")
    public int findIdByUsername(@PathVariable String username) {
    return userDao.findIdByUsername(username);
}

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/user/{username}")
    public User findByUsername(@PathVariable String username) {
        return userDao.findByUsername(username);
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public boolean created(String username, String password) {
        return userDao.create(username, password);
    }


}
