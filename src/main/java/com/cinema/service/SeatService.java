package com.cinema.service;

import com.cinema.model.entity.Seat;

import java.util.List;

public interface SeatService {

    Seat create(Seat seat);

    Seat read(long id);

    Seat update(Seat seat);

    void delete(long id);

    List<Seat> getAll();
}
