package com.cinema.service.impl;

import com.cinema.model.dao.impl.MovieDAO;
import com.cinema.model.entity.Movie;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

class TestMovieService {

    private MovieService movieService;
    private MovieDAO movieDAO;

    @BeforeEach
    void init() {
        movieService = new MovieService();
        movieDAO = Mockito.mock(MovieDAO.class);
        movieService.movieDAO = movieDAO;
    }

    @Test
    void test_getMovie() throws SQLException {
        int id = 1;
        Movie movie = new Movie();
        movie.setId(id);
        Mockito.when(movieDAO.read(id)).thenReturn(movie);

        Movie testMovie = movieService.getMovie(id);

        Mockito.verify(movieDAO).read(id);
        Mockito.verifyNoMoreInteractions(movieDAO);
        Assertions.assertEquals(movie, testMovie);
    }

    @Test
    void test_getAll() throws SQLException {
        Movie movie1 = new Movie();
        Movie movie2 = new Movie();
        movie1.setId(1);
        movie2.setId(2);
        List<Movie> movies = Arrays.asList(movie1, movie2);
        Mockito.when(movieDAO.getAll()).thenReturn(movies);

        List<Movie> all = movieService.getAll();

        Mockito.verify(movieDAO).getAll();
        Mockito.verifyNoMoreInteractions(movieDAO);
        Assertions.assertEquals(movies, all);
    }

    @Test
    void test_createMovie() throws SQLException {
        Movie movie = new Movie();
        movie.setName("name");
        movie.setDescription("description");
        movie.setYear(LocalDate.now().getYear());
        movie.setDuration((int) Byte.MAX_VALUE);

        movieService.createMovie(movie.getName(), movie.getDescription(), movie.getYear(), movie.getDuration());

        Mockito.verify(movieDAO).create(movie);
        Mockito.verifyNoMoreInteractions(movieDAO);
    }
}
