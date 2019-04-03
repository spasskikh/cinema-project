package com.cinema.controller;

import com.cinema.dto.UserDto;
import com.cinema.model.entity.User;
import com.cinema.model.entity.UserRole;
import com.cinema.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

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
        try {
            if (result.hasErrors()) throw new IllegalArgumentException("Incorrect input data");
            userService.registerNewUser(userDto);
            return "login";
        } catch (IllegalArgumentException exc) {
            LOGGER.error("Incorrect input data", exc);
            return "registration";
        }
    }
}
