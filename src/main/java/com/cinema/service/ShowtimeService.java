package com.cinema.service;

import com.cinema.model.entity.Showtime;

import java.util.List;

public interface ShowtimeService {

    Showtime create(Showtime showtime);

    Showtime read(long id);

    Showtime update(Showtime showtime);

    void delete(long id);

    List<Showtime> getAll();
}
