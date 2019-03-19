package com.cinema.service.impl;

import com.cinema.model.dao.DAOFactory;
import com.cinema.model.dao.impl.MovieDAO;
import com.cinema.model.entity.Movie;
import com.cinema.service.Service;
import com.cinema.util.constants.DAOKey;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * Class provides methods for working with Movie entity
 *
 * @author Anton Spasskikh
 */
public class MovieService implements Service {

    /**
     * logger field
     */
    protected static Logger logger = LogManager.getLogger(MovieService.class);

    /**
     * movieDAO field
     */
    MovieDAO movieDAO;

    /**
     * constructor without parameters, initializes {@link #movieDAO}
     */
    public MovieService() {
        this.movieDAO = (MovieDAO) DAOFactory.getDAO(DAOKey.MOVIE_DAO);
    }

    /**
     * returns movie instance
     *
     * @param id movie id
     * @return movie instance
     */
    public Movie getMovie(Integer id) {
        return movieDAO.read(id);
    }

    /**
     * returns all existing movies
     *
     * @return list of movies
     */
    public List<Movie> getAll() {
        return movieDAO.getAll();
    }

    /**
     * creates movie instance
     *
     * @param name        movie name
     * @param description movie description
     * @param year        movie year
     * @param duration    movie duration
     */
    public void createMovie(String name, String description, int year, int duration) {
        Movie movie = new Movie();
        movie.setName(name);
        movie.setDescription(description);
        movie.setYear(year);
        movie.setDuration(duration);

        movieDAO.create(movie);
    }
}
