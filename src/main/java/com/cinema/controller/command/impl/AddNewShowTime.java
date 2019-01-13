package com.cinema.controller.command.impl;

import com.cinema.controller.command.Command;
import com.cinema.model.entity.Movie;
import com.cinema.model.entity.TimeSlot;
import com.cinema.service.ServiceFactory;
import com.cinema.service.impl.MovieService;
import com.cinema.service.impl.ShowTimeService;
import com.cinema.service.impl.TimeSlotService;
import com.cinema.util.ResourceManager;
import com.cinema.util.constants.PageKey;
import com.cinema.util.constants.ServiceKey;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Class implementing Command interface
 *
 * @author Anton Spasskikh
 */
public class AddNewShowTime implements Command {

    /**
     * movie service field
     */
    private MovieService movieService = (MovieService) ServiceFactory.getService(ServiceKey.MOVIE_SERVICE);

    /**
     * time slot service field
     */
    private TimeSlotService timeSlotService = (TimeSlotService) ServiceFactory.getService(ServiceKey.TIMESLOT_SERVICE);

    /**
     * showtime service field
     */
    private ShowTimeService showTimeService = (ShowTimeService) ServiceFactory.getService(ServiceKey.SHOWTIME_SERVICE);

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getMethod().equalsIgnoreCase("POST")) {
            createShowTime(req, resp);
        } else {
            req.setAttribute("movies", movieService.getAll());
            req.setAttribute("timeSlots", timeSlotService.getAll());
            req.getRequestDispatcher(PageKey.NEW_SHOWTIME_PAGE.toString()).forward(req, resp);
        }
    }

    /**
     * reads passed parameters and creates showtime instance
     *
     * @param req  request from client to server
     * @param resp response from server to client
     * @throws ServletException if ServletException occurs
     * @throws IOException      if IOException occurs
     */
    private void createShowTime(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        LocalDate showTimeDate;

        try {
            showTimeDate = LocalDate.parse(req.getParameter("Date"), DateTimeFormatter.ISO_DATE);
        } catch (NumberFormatException | DateTimeParseException exc) {
            req.setAttribute("msg", ResourceManager.INSTANCE.getValue("incorrectDate"));
            req.getRequestDispatcher(PageKey.ADMIN_PAGE.toString()).forward(req, resp);
            return;
        }

        int timeSlotId = Integer.parseInt(req.getParameter("timeSlot"));

        if (showTimeService.exists(showTimeDate, timeSlotId)) {
            req.setAttribute("msg", ResourceManager.INSTANCE.getValue("unavailableShowTime"));
            req.getRequestDispatcher(PageKey.ADMIN_PAGE.toString()).forward(req, resp);
        } else {
            Movie movie = movieService.getMovie(Integer.parseInt(req.getParameter("movie")));
            TimeSlot timeSlot = timeSlotService.getTimeSlot(timeSlotId);
            showTimeService.create(movie, timeSlot, showTimeDate);

            req.setAttribute("msg", ResourceManager.INSTANCE.getValue("showTimeSuccessAdd"));
            req.getRequestDispatcher(PageKey.ADMIN_PAGE.toString()).forward(req, resp);
        }
    }
}
