package com.cinema.controller;

import com.cinema.dto.UserDto;
import com.cinema.model.entity.User;
import com.cinema.model.entity.UserRole;
import com.cinema.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;


@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String viewLoginPage() {
        return "login";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("user", new UserDto());
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("user") @Valid UserDto userDto, BindingResult result) {
        if (isValid(userDto, result)) {
            User newUser = new User();
            newUser.setLogin(userDto.getLogin());
            newUser.setPassword(userDto.getPassword());
            newUser.setRole(new UserRole(2L, "USER"));
            userService.create(newUser);
            return "login";
        }
        return "registration";
    }

    private boolean isValid(UserDto userDto, BindingResult result) {
        return !result.hasErrors() && userDto.getPassword().equals(userDto.getMatchingPassword());
    }
}
