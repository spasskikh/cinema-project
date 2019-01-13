package com.cinema.controller.command.impl;

import com.cinema.controller.command.Command;
import com.cinema.model.entity.Movie;
import com.cinema.model.entity.ShowTime;
import com.cinema.model.entity.User;
import com.cinema.service.ServiceFactory;
import com.cinema.service.impl.ShowTimeService;
import com.cinema.util.constants.PageKey;
import com.cinema.util.constants.ServiceKey;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Class implementing Command interface
 *
 * @author Anton Spasskikh
 */
public class ViewHomePage implements Command {

    /**
     * showtime service field
     */
    private ShowTimeService showTimeService = (ShowTimeService) ServiceFactory.getService(ServiceKey.SHOWTIME_SERVICE);

    /**
     * {@inheritDoc}
     */
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");

        if (user == null || !user.getRole().getRoleName().equalsIgnoreCase("ADMIN")) {
            LocalDateTime now = LocalDateTime.now();
            List<ShowTime> currentShowTimes = showTimeService.getActualShowTimes(now);
            List<Movie> movies = showTimeService.getUniqueMovies(currentShowTimes);

            req.setAttribute("date", now.format(DateTimeFormatter.ISO_DATE));
            req.setAttribute("movies", movies);

            req.getRequestDispatcher(PageKey.HOME_PAGE.toString()).forward(req, resp);
        } else {
            req.getRequestDispatcher(PageKey.ADMIN_PAGE.toString()).forward(req, resp);
        }
    }
}
