package com.cinema.service.impl;

import com.cinema.model.dao.DAOFactory;
import com.cinema.model.dao.impl.ShowTimeDAO;
import com.cinema.model.entity.Movie;
import com.cinema.model.entity.ShowTime;
import com.cinema.model.entity.TimeSlot;
import com.cinema.service.Service;
import com.cinema.util.ShowTimeComparator;
import com.cinema.util.constants.DAOKey;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class provides methods for working with ShowTime entity
 *
 * @author Anton Spasskikh
 */
public class ShowTimeService implements Service {

    /**
     * showTimeDAO field
     */
    private ShowTimeDAO showTimeDAO;

    /**
     * constructor without parameters, initializes {@link #showTimeDAO}
     */
    public ShowTimeService() {
        showTimeDAO = (ShowTimeDAO) DAOFactory.getDAO(DAOKey.SHOWTIME_DAO);
    }

    /**
     * returns showtime by id
     *
     * @param id showtime id
     * @return showtime instance
     */
    public ShowTime getShowTime(int id) {
        return showTimeDAO.read(id);
    }

    /**
     * returns all existing showTimes
     *
     * @return list of showTimes
     */
    public List<ShowTime> getAll() {
        return showTimeDAO.getAll();
    }

    /**
     * creates showtime instance
     *
     * @param movie    showtime movie
     * @param timeSlot showtime timeSlot
     * @param date     showtime date
     */
    public void create(Movie movie, TimeSlot timeSlot, LocalDate date) {
        ShowTime showTime = new ShowTime();
        showTime.setDate(date);
        showTime.setTimeSlot(timeSlot);
        showTime.setMovie(movie);

        showTimeDAO.create(showTime);
    }

    /**
     * checks if showtime already exists
     *
     * @param date       showtime date
     * @param timeSlotId time slot id
     * @return true if showtime exists
     */
    public boolean exists(LocalDate date, int timeSlotId) {
        for (ShowTime s : getAll()) {
            if (s.getDate().equals(date) && s.getTimeSlot().getId().equals(timeSlotId)) {
                return true;
            }
        }
        return false;
    }

    /**
     * deletes showtime
     *
     * @param showTimeId showtime id
     */
    public void delete(int showTimeId) {
        ShowTime showTime = showTimeDAO.read(showTimeId);
        if (showTime != null) {
            showTimeDAO.delete(showTime);
        }
    }

    /**
     * returns showTimes on or after the date
     *
     * @param now date
     * @return list of showTimes
     */
    public List<ShowTime> getActualShowTimes(LocalDateTime now) {
        return getAll().stream()
                .filter(st -> st.getDate().isEqual(now.toLocalDate()) || st.getDate().isAfter(now.toLocalDate()))
                .collect(Collectors.toList());
    }

    /**
     * returns showTimes on or after the date by movie
     *
     * @param now   date
     * @param movie movie
     * @return list of showTimes
     */
    public List<ShowTime> getActualShowTimesByMovie(LocalDateTime now, Movie movie) {
        return getActualShowTimes(now).stream()
                .filter(st -> st.getMovie().getId().equals(movie.getId()))
                .sorted(new ShowTimeComparator())
                .collect(Collectors.toList());
    }

    /**
     * returns unique movies
     *
     * @param showTimes list of showTimes
     * @return list of movies
     */
    public List<Movie> getUniqueMovies(List<ShowTime> showTimes) {
        HashSet<Movie> movies = new HashSet<>();
        for (ShowTime st : showTimes) {
            movies.add(st.getMovie());
        }
        List<Movie> movieList = new ArrayList<>(movies);
        movieList.sort(Comparator.comparing(Movie::getName));
        return movieList;
    }
}
