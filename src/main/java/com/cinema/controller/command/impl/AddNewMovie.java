package com.cinema.controller.command.impl;

import com.cinema.controller.command.Command;
import com.cinema.service.ServiceFactory;
import com.cinema.service.impl.MovieService;
import com.cinema.util.ResourceManager;
import com.cinema.util.constants.PageKey;
import com.cinema.util.constants.ServiceKey;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Class implementing Command interface
 *
 * @author Anton Spasskikh
 *
 */
public class AddNewMovie implements Command {

    /**
     * movie service field
     */
    private MovieService movieService = (MovieService) ServiceFactory.getService(ServiceKey.MOVIE_SERVICE);

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getMethod().equalsIgnoreCase("POST")) {
        createMovie(req, resp);
        } else {
            req.getRequestDispatcher(PageKey.NEW_MOVIE_PAGE.toString()).forward(req,resp);
        }
    }

    /**
     * reads passed parameters and creates movie instance
     *
     * @param req
     *            request from client to server
     * @param resp
     *            response from server to client
     * @throws ServletException
     *             if ServletException occurs
     * @throws IOException
     *             if IOException occurs
     */
    private void createMovie(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String name = req.getParameter("Name");
        String description = req.getParameter("Description");
        int year = Integer.parseInt(req.getParameter("Year"));
        int duration = Integer.parseInt(req.getParameter("Duration"));

        movieService.createMovie(name, description, year, duration);

        req.setAttribute("msg", ResourceManager.INSTANCE.getValue("movieSuccess"));
        req.getRequestDispatcher(PageKey.ADMIN_PAGE.toString()).forward(req, resp);
    }
}
