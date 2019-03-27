package com.cinema.service;

import com.cinema.model.entity.Movie;

import java.util.List;

public interface MovieService {

    Movie create(Movie movie);

    Movie read(long id);

    Movie update(Movie movie);

    void delete(long id);

    List<Movie> getAll();


}
