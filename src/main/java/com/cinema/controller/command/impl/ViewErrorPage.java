package com.cinema.controller.command.impl;

import com.cinema.controller.command.Command;
import com.cinema.util.constants.PagesKey;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ViewErrorPage implements Command {

    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher(PagesKey.ERROR_PAGE.toString()).forward(req, resp);

    }
}
