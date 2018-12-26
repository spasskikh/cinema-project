package com.cinema.service.impl;

import com.cinema.exception.NoSuchDAOExc;
import com.cinema.model.dao.DAOFactory;
import com.cinema.model.dao.impl.MoviesDAO;
import com.cinema.model.entity.Movie;
import com.cinema.service.Service;

import java.util.List;

public class MovieService implements Service {

    public List<Movie> getAll() {

        MoviesDAO moviesDAO = null;
        try {
            moviesDAO = (MoviesDAO) DAOFactory.getDAO("movies");
        } catch (NoSuchDAOExc exc) {
            exc.printStackTrace();
        }
        return moviesDAO.getAll();
    }
}
