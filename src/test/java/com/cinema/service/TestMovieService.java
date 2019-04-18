package com.cinema.service;

import com.cinema.model.entity.Movie;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import javax.persistence.EntityNotFoundException;

@DirtiesContext
@SpringBootTest
class TestMovieService {

    @Autowired
    private MovieService movieService;
    private Movie movie;

    @BeforeEach
    void setUp() {
        movie = new Movie(6L, "Name", "Description", 2019, 120);
    }

    @Test
    void test_create() {
        Movie createdMovie = movieService.create(movie);

        Assertions.assertEquals(createdMovie, movie);
    }

    @Test
    void test_read() {
        Movie createdMovie = movieService.create(movie);
        Movie readMovie = movieService.read(createdMovie.getId());

        Assertions.assertEquals(createdMovie, readMovie);
    }

    @Test
    void test_update() {
        Movie createdMovie = movieService.create(movie);
        createdMovie.setDescription("New Description");
        Movie updatedMovie = movieService.update(createdMovie);

        Assertions.assertEquals(createdMovie, updatedMovie);
    }

    @Test
    void test_delete() {
        Movie createdMovie = movieService.create(movie);
        movieService.delete(createdMovie.getId());

        Assertions.assertThrows(EntityNotFoundException.class, () -> movieService.read(createdMovie.getId()));
    }

    @Test
    void test_getAll() {
        /*5 - initial number of movies*/
        Assertions.assertEquals(5, movieService.getAll().size());
    }
}
