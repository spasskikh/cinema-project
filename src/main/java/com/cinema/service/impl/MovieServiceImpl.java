package com.cinema.service.impl;

import com.cinema.model.dao.MovieDao;
import com.cinema.model.entity.Movie;
import com.cinema.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieDao movieDao;

    @Override
    public Movie create(Movie movie) {
        return movieDao.saveAndFlush(movie);
    }

    @Override
    public Movie read(long id) {
        return movieDao.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Movie update(Movie movie) {
        return movieDao.saveAndFlush(movie);
    }

    @Override
    public void delete(long id) {
        movieDao.deleteById(id);
    }

    @Override
    public List<Movie> getAll() {
        return movieDao.findAll();
    }
}
