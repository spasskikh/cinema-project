package com.cinema.controller.command.impl;

import com.cinema.controller.command.Command;
import com.cinema.service.ServiceFactory;
import com.cinema.service.impl.MovieService;
import com.cinema.model.entity.Movie;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GetAllMovies implements Command {

    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        MovieService movie_service = (MovieService) ServiceFactory.getService("MOVIE_SERVICE");
        List<Movie> all = movie_service.getAll();
        req.setAttribute("movies", all);
        req.getRequestDispatcher("/jsp/home.jsp").forward(req, resp);

    }
}
