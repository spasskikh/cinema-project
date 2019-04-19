package com.cinema.controller;

import com.cinema.model.entity.Movie;
import com.cinema.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("rest")
public class MovieRestController {

    @Autowired
    private MovieService movieService;

    @GetMapping(value = "/movies")
    public List<Movie> getAllMovies() {
        return movieService.getAll();
    }

    @PostMapping(value = "movies/{movieId}")
    public Movie read(@PathVariable(value = "movieId") Long movieId) {
        return movieService.read(movieId);
    }
}
