package com.cinema.controller;

import com.cinema.model.entity.Movie;
import com.cinema.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("rest")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @RequestMapping(value = "/movies", method = RequestMethod.GET)
    public List<Movie> getAllMovies() {
        return movieService.getAll();
    }

    @RequestMapping(value = "movies/{movieId}", method = RequestMethod.GET)
    public Movie read(@PathVariable(value = "movieId") Long movieId) {
        System.out.println("in method");
        return movieService.read(movieId);
    }
}
