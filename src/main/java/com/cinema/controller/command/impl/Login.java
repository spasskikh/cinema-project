package com.cinema.controller.command.impl;

import com.cinema.controller.command.Command;
import com.cinema.model.entity.User;
import com.cinema.service.ServiceFactory;
import com.cinema.service.impl.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Login implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (!req.getMethod().equalsIgnoreCase("POST")) {
            req.getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
        } else {

            String login = req.getParameter("Login");
            String pass = req.getParameter("Password");

            User user = ((UserService) ServiceFactory.getService("USER_SERVICE")).getUser(login, pass);

            if (user != null) {
                req.getSession().setAttribute("user", user);
                req.getRequestDispatcher("/jsp/home.jsp").forward(req, resp);
            } else {
                req.getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
            }
        }
    }
}
