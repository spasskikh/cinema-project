package com.cinema.controller;

import com.cinema.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @Autowired
    private MovieService movieService;

    @RequestMapping("/")
    public String welcome(Model model) {
        return "home";
    }
}
