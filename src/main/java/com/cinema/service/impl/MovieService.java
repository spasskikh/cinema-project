package com.cinema.service.impl;

import com.cinema.model.dao.DAOFactory;
import com.cinema.model.dao.impl.MovieDAO;
import com.cinema.model.entity.Movie;
import com.cinema.service.Service;
import com.cinema.util.constants.DAOKey;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
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
    private MovieDAO movieDAO;

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
        try {
            return movieDAO.read(id);
        } catch (SQLException exc) {
            logger.error(exc.getMessage(), exc);
            return null;
        }
    }

    /**
     * returns all existing movies
     *
     * @return list of movies
     */
    public List<Movie> getAll() {
        try {
            return movieDAO.getAll();
        } catch (SQLException exc) {
            logger.error(exc);
            return null;
        }
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

        try {
            movieDAO.create(movie);
        } catch (SQLException exc) {
            logger.error(exc.getMessage(), exc);
        }
    }
}
