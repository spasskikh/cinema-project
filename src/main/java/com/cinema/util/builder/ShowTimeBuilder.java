package com.cinema.util.builder;

import com.cinema.model.dao.DAOFactory;
import com.cinema.model.dao.impl.MovieDAO;
import com.cinema.model.dao.impl.TimeSlotDAO;
import com.cinema.model.entity.ShowTime;

import static com.cinema.util.constants.DAOKey.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * ShowTime builder class
 *
 * @author Anton Spasskikh
 */
public class ShowTimeBuilder {

    /**
     * showtime field
     */
    private ShowTime showTime;

    /**
     * constructor without parameters
     * instantiates {@link #showTime}
     */
    public ShowTimeBuilder() {
        this.showTime = new ShowTime();
    }

    /**
     * @return {@link #showTime}
     */
    public ShowTime build() {
        return showTime;
    }

    /**
     * sets id
     *
     * @return builder instance
     */
    public ShowTimeBuilder buildId(Integer id) {
        showTime.setId(id);
        return this;
    }

    /**
     * sets id
     *
     * @return builder instance
     */
    public ShowTimeBuilder buildDate(String date) {
        showTime.setDate(LocalDate.parse(date, DateTimeFormatter.ISO_DATE));
        return this;
    }

    /**
     * sets movie
     *
     * @return builder instance
     */
    public ShowTimeBuilder buildMovie(Integer movieId) throws SQLException {
        MovieDAO movieDAO = (MovieDAO) DAOFactory.getDAO(MOVIE_DAO);
        showTime.setMovie(movieDAO.read(movieId));
        return this;
    }

    /**
     * sets time slot
     *
     * @return builder instance
     */
    public ShowTimeBuilder buildTimeSlot(Integer timeSlotId) {
        TimeSlotDAO timeSlotDAO = (TimeSlotDAO) DAOFactory.getDAO(TIMESLOT_DAO);
        showTime.setTimeSlot(timeSlotDAO.read(timeSlotId));
        return this;
    }

}
