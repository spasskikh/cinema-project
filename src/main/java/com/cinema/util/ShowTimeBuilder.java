package com.cinema.util;

import com.cinema.exception.NoSuchDAOExc;
import com.cinema.model.dao.DAOFactory;
import com.cinema.model.dao.impl.MoviesDAO;
import com.cinema.model.dao.impl.SeatsDAO;
import com.cinema.model.dao.impl.TimeSlotsDAO;
import com.cinema.model.dao.impl.UsersDAO;
import com.cinema.model.entity.ShowTime;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ShowTimeBuilder {

    private ShowTime showTime = new ShowTime();
//method should return builder class
    public ShowTime build() {
        return showTime;
    }

    public ShowTimeBuilder buildId(Integer id) {
        showTime.setId(id);
        return this;
    }

    public ShowTimeBuilder buildDate(String date) {
        showTime.setDate(LocalDate.parse(date, DateTimeFormatter.ISO_DATE));
        return this;
    }

    public ShowTimeBuilder buildMovie(Integer movieId) throws NoSuchDAOExc {
        MoviesDAO moviesDAO = (MoviesDAO) DAOFactory.getDAO("movies");
        showTime.setMovie(moviesDAO.read(movieId));
        return this;
    }

    public ShowTimeBuilder buildTimeSlot(Integer timeSlotId) throws NoSuchDAOExc {
        TimeSlotsDAO timeSlotsDAO = (TimeSlotsDAO) DAOFactory.getDAO("time_slots");
        showTime.setTimeSlot(timeSlotsDAO.read(timeSlotId));
        return this;
    }

    public ShowTimeBuilder buildSeat(Integer seatId) throws NoSuchDAOExc {
        SeatsDAO seatsDAO = (SeatsDAO) DAOFactory.getDAO("seats");
        showTime.setSeat(seatsDAO.read(seatId));
        return this;
    }

    public ShowTimeBuilder buildUser(Integer userId) throws NoSuchDAOExc {
        UsersDAO usersDAO = (UsersDAO) DAOFactory.getDAO("users");
        showTime.setUser(usersDAO.read(userId));
        return this;
    }
}
