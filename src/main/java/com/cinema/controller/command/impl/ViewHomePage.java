package com.cinema.controller.command.impl;

import com.cinema.controller.command.Command;
import com.cinema.service.ServiceFactory;
import com.cinema.service.impl.MovieService;
import com.cinema.util.constants.PagesKey;
import com.cinema.util.constants.ServicesKey;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ViewHomePage implements Command {

    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        MovieService movie_service = (MovieService) ServiceFactory.getService(ServicesKey.MOVIE_SERVICE);
        req.setAttribute("movies", movie_service.getAll());
        req.getRequestDispatcher(PagesKey.HOME_PAGE.toString()).forward(req, resp);

    }
}
