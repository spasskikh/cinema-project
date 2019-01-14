package com.cinema.controller.command.impl;

import com.cinema.service.impl.MovieService;
import com.cinema.util.ResourceManager;
import com.cinema.util.constants.PageKey;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

class TestAddNewMovie {

    private AddNewMovie newMoviePage;
    private HttpServletRequest req;
    private HttpServletResponse resp;
    private RequestDispatcher dispatcher;
    private MovieService movieService;

    private static final String NAME = "Name";
    private static final String DESCRIPTION = "Description";
    private static final String YEAR = "Year";
    private static final String DURATION = "Duration";

    @BeforeEach
    void init() {
        newMoviePage = new AddNewMovie();
        req = Mockito.mock(HttpServletRequest.class);
        resp = Mockito.mock(HttpServletResponse.class);
        dispatcher = Mockito.mock(RequestDispatcher.class);
        movieService = Mockito.mock(MovieService.class);
        newMoviePage.movieService = movieService;
    }

    @Test
    void test_GET() throws ServletException, IOException {
        Mockito.when(req.getMethod()).thenReturn("GET");
        Mockito.when(req.getRequestDispatcher(PageKey.NEW_MOVIE_PAGE.toString())).thenReturn(dispatcher);

        newMoviePage.execute(req, resp);

        Mockito.verify(req).getMethod();
        Mockito.verify(req).getRequestDispatcher(PageKey.NEW_MOVIE_PAGE.toString());
        Mockito.verify(dispatcher).forward(req, resp);
        Mockito.verifyNoMoreInteractions(req, resp, dispatcher);
    }

    @Test
    void test_POST() throws ServletException, IOException {
        Mockito.when(req.getMethod()).thenReturn("POST");
        Mockito.when(req.getParameter(NAME)).thenReturn(NAME);
        Mockito.when(req.getParameter(DESCRIPTION)).thenReturn(DESCRIPTION);
        Mockito.when(req.getParameter(YEAR)).thenReturn("2020");
        Mockito.when(req.getParameter(DURATION)).thenReturn("120");
        Mockito.when(req.getRequestDispatcher(PageKey.ADMIN_PAGE.toString())).thenReturn(dispatcher);

        newMoviePage.execute(req, resp);

        Mockito.verify(req).getMethod();
        Mockito.verify(req).getParameter(NAME);
        Mockito.verify(req).getParameter(DESCRIPTION);
        Mockito.verify(req).getParameter(YEAR);
        Mockito.verify(req).getParameter(DURATION);
        Mockito.verify(movieService).createMovie(NAME, DESCRIPTION, 2020, 120);
        Mockito.verify(req).setAttribute("msg", ResourceManager.INSTANCE.getValue("movieSuccess"));
        Mockito.verify(req).getRequestDispatcher(PageKey.ADMIN_PAGE.toString());
        Mockito.verify(dispatcher).forward(req, resp);
        Mockito.verifyNoMoreInteractions(req, resp, dispatcher, movieService);
    }
}
