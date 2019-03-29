package com.cinema.controller;

import com.cinema.dto.UserDto;
import com.cinema.exception.IncorrectPasswordException;
import com.cinema.model.entity.User;
import com.cinema.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;

@RestController
public class UserRestController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestBody UserDto userDto, HttpServletRequest req) {
        try {
            User foundUser = userService.findByLogin(userDto.getLogin());
            userService.checkPassword(userDto, foundUser);
            req.getSession(true).setAttribute("user", foundUser);
            return "redirect: /cinema";
        } catch (EntityNotFoundException | IncorrectPasswordException exc) {
            return "login";
        }
    }
}
