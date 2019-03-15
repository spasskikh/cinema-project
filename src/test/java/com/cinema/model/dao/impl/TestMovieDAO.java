package com.cinema.model.dao.impl;

import com.cinema.model.dao.DAOFactory;
import com.cinema.model.entity.Movie;
import com.cinema.util.constants.DAOKey;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

class TestMovieDAO {

    private Movie testMovie;
    private MovieDAO dao;

    @BeforeEach
    void init() {
        testMovie = new Movie();
        testMovie.setName("NAME");
        testMovie.setDescription("DESCRIPTION");
        testMovie.setYear(LocalDate.now().getYear());
        testMovie.setDuration((int) Byte.MAX_VALUE);
        dao = (MovieDAO) DAOFactory.getDAO(DAOKey.MOVIE_DAO);
    }

    @Test()
    void test_create() {
        boolean exists = false;
        dao.create(testMovie);
        for (Movie movie : dao.getAll()) {
            if (movie.getName().equals(testMovie.getName())) {
                exists = true;
                testMovie = movie;
                break;
            }
        }
        Assertions.assertTrue(exists);

        dao.delete(testMovie);
    }

    @Test
    void test_read() {
        dao.create(testMovie);
        for (Movie movie : dao.getAll()) {
            if (movie.getName().equals(testMovie.getName())) {
                System.out.println(movie.getId());
                testMovie = movie;
                break;
            }
        }
        System.out.println(testMovie.getId());
        Movie movie = dao.read(testMovie.getId());
        Assertions.assertEquals(testMovie, movie);

        dao.delete(testMovie);
    }

    @Test
    void test_update() {
        dao.create(testMovie);
        for (Movie movie : dao.getAll()) {
            if (movie.getName().equals(testMovie.getName())) {
                testMovie = movie;
                break;
            }
        }
        testMovie.setDescription("NEW_DESCRIPTION");
        dao.update(testMovie);
        Assertions.assertEquals(testMovie, dao.read(testMovie.getId()));

        dao.delete(testMovie);
    }

    @Test
    void test_delete() {

        dao.create(testMovie);
        for (Movie movie : dao.getAll()) {
            if (movie.getName().equals(testMovie.getName())) {
                testMovie = movie;
                break;
            }
        }
        dao.delete(testMovie);
        Assertions.assertNull(dao.read(testMovie.getId()));

        dao.delete(testMovie);
    }

    @Test
    void test_getAll() {
        List<Movie> movies = dao.getAll();
        Assertions.assertFalse(movies.size() <= 1);
    }
}
