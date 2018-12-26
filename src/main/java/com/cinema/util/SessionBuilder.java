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

public class SessionBuilder {

    private ShowTime showTime = new ShowTime();
//method should return builder class
    public ShowTime getShowTime() {
        return showTime;
    }

    public void buildId(Integer id) {
        showTime.setId(id);
    }

    public void buildDate(String date) {
        showTime.setDate(LocalDate.parse(date, DateTimeFormatter.ISO_DATE));
    }

    public void buildMovie(Integer movieId) throws NoSuchDAOExc {
        MoviesDAO moviesDAO = (MoviesDAO) DAOFactory.getDAO("movies");
        showTime.setMovie(moviesDAO.read(movieId));
    }

    public void buildTimeSlot(Integer timeSlotId) throws NoSuchDAOExc {
        TimeSlotsDAO timeSlotsDAO = (TimeSlotsDAO) DAOFactory.getDAO("time_slots");
        showTime.setTimeSlot(timeSlotsDAO.read(timeSlotId));
    }

    public void buildSeat(Integer seatId) throws NoSuchDAOExc {
        SeatsDAO seatsDAO = (SeatsDAO) DAOFactory.getDAO("seats");
        showTime.setSeat(seatsDAO.read(seatId));
    }

    public void buildUser(Integer userId) throws NoSuchDAOExc {
        UsersDAO usersDAO = (UsersDAO) DAOFactory.getDAO("users");
        showTime.setUser(usersDAO.read(userId));

    }
}
