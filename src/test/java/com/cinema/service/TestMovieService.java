package com.cinema.service;

import com.cinema.config.WebApplicationContextConfig;
import com.cinema.model.entity.Movie;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;

@DirtiesContext
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = WebApplicationContextConfig.class)
@WebAppConfiguration
class TestMovieService {

    @Resource
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    @Resource
    private MovieService movieService;
    private Movie movie;

    @BeforeEach
    void setUp() {
        entityManager = entityManagerFactory.createEntityManager();
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
        Assertions.assertEquals(5,movieService.getAll().size());
    }
}
