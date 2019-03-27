package com.cinema.controller;

import com.cinema.model.dao.MovieDao;
import com.cinema.model.entity.Movie;
import com.cinema.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MovieController {

    @Autowired
    private MovieService movieService;

    @RequestMapping("/movie")
    public void create() {
        System.out.println("in create method");
        movieService.create(new Movie());

        return;
    }
}
