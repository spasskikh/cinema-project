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

}
