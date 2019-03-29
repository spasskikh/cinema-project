package com.cinema.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String welcome() {
        return "home";
    }

    @RequestMapping(value = "movies/{movieId}", method = RequestMethod.GET)
    public String movieInfo(@PathVariable(value = "movieId") String movieId,  Model model) {
        model.addAttribute("movieId", movieId);
        return "movie";
    }
}
