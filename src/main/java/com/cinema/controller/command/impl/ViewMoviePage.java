package com.cinema.controller.command.impl;

import com.cinema.controller.command.Command;
import com.cinema.model.entity.Movie;
import com.cinema.model.entity.ShowTime;
import com.cinema.service.ServiceFactory;
import com.cinema.service.impl.MovieService;
import com.cinema.service.impl.ShowTimeService;
import com.cinema.util.ShowTimeComparator;
import com.cinema.util.constants.PageKey;
import com.cinema.util.constants.ServiceKey;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Class implementing Command interface
 *
 * @author Anton Spasskikh
 */
public class ViewMoviePage implements Command {

    /**
     * movie service field
     */
    private MovieService movieService = (MovieService) ServiceFactory.getService(ServiceKey.MOVIE_SERVICE);

    /**
     * showtime service field
     */
    private ShowTimeService showTimeService = (ShowTimeService) ServiceFactory.getService(ServiceKey.SHOWTIME_SERVICE);

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Movie movie = movieService.getMovie(Integer.parseInt(req.getParameter("movieId")));
        List<ShowTime> showTimes = showTimeService.getActualShowTimesByMovie(LocalDateTime.now(), movie);

        req.setAttribute("movie", movie);
        req.setAttribute("showTimes", showTimes);

        req.getRequestDispatcher(PageKey.MOVIE_INFO_PAGE.toString()).forward(req, resp);
    }
}
