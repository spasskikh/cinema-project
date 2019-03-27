package com.cinema.service.impl;

import com.cinema.model.dao.SeatDao;
import com.cinema.model.entity.Seat;
import com.cinema.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class SeatServiceImpl implements SeatService {

    @Autowired
    private SeatDao seatDao;

    @Override
    public Seat create(Seat seat) {
        return seatDao.saveAndFlush(seat);
    }

    @Override
    public Seat read(long id) {
        return seatDao.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Seat update(Seat seat) {
        return seatDao.saveAndFlush(seat);
    }

    @Override
    public void delete(long id) {
        seatDao.deleteById(id);
    }

    @Override
    public List<Seat> getAll() {
        return seatDao.findAll();
    }
}
