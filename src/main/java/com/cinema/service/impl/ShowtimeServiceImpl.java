package com.cinema.service.impl;

import com.cinema.model.dao.ShowtimeDao;
import com.cinema.model.entity.Showtime;
import com.cinema.service.ShowtimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class ShowtimeServiceImpl implements ShowtimeService {

    @Autowired
    private ShowtimeDao showtimeDao;

    @Override
    public Showtime create(Showtime showtime) {
        return showtimeDao.saveAndFlush(showtime);
    }

    @Override
    public Showtime read(long id) {
        return showtimeDao.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Showtime update(Showtime showtime) {
        return showtimeDao.saveAndFlush(showtime);
    }

    @Override
    public void delete(long id) {
        showtimeDao.deleteById(id);
    }

    @Override
    public List<Showtime> getAll() {
        return showtimeDao.findAll();
    }
}
