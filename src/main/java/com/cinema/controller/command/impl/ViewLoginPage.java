package com.cinema.controller.command.impl;

import com.cinema.controller.command.Command;
import com.cinema.controller.command.CommandFactory;
import com.cinema.model.entity.User;
import com.cinema.service.ServiceFactory;
import com.cinema.service.impl.UserService;
import com.cinema.util.CommandManager;
import com.cinema.util.constants.CommandsKey;
import com.cinema.util.constants.PagesKey;
import com.cinema.util.constants.ServicesKey;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ViewLoginPage implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (!req.getMethod().equalsIgnoreCase("POST")) {
            req.getRequestDispatcher(PagesKey.LOGIN_PAGE.toString()).forward(req, resp);
        } else {

            String login = req.getParameter("Login");
            String pass = req.getParameter("Password");

            User user = ((UserService) ServiceFactory.getService(ServicesKey.USER_SERVICE)).getUser(login, pass);

            if (user != null) {
                req.getSession().setAttribute("user", user);
                resp.sendRedirect(CommandManager.getLocationForRedirect(CommandsKey.COMM_VIEW_HOME_PAGE.toString()));
            } else {
                req.getRequestDispatcher(PagesKey.LOGIN_PAGE.toString()).forward(req, resp);
            }
        }
    }
}
