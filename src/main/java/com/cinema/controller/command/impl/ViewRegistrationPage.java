package com.cinema.controller.command.impl;

import com.cinema.controller.command.Command;
import com.cinema.service.ServiceFactory;
import com.cinema.service.impl.UserService;
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
 */
public class ViewRegistrationPage implements Command {

    /**
     * user service field
     */
    private UserService userService = (UserService) ServiceFactory.getService(ServiceKey.USER_SERVICE);

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getMethod().equalsIgnoreCase("POST")) {
            register(req, resp);
        } else {
            req.getRequestDispatcher(PageKey.REGISTRATION_PAGE.toString()).forward(req, resp);
        }
    }

    /**
     * reads passed parameters and performs registration procedures
     *
     * @param req  request from client to server
     * @param resp response from server to client
     * @throws ServletException if ServletException occurs
     * @throws IOException      if IOException occurs
     */
    private void register(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("Login");
        String pass = req.getParameter("Password");
        String passRep = req.getParameter("Re-Password");

        boolean isValid = userService.validateLoginInput(login) &&
                userService.validatePasswordInput(pass, passRep) &&
                userService.getUser(login) == null;

        if (isValid) {
            userService.createUser(login, pass);
            req.setAttribute("msg", ResourceManager.INSTANCE.getValue("registrationSuccess"));
            req.getRequestDispatcher(PageKey.LOGIN_PAGE.toString()).forward(req, resp);
        } else {
            req.setAttribute("msg", ResourceManager.INSTANCE.getValue("incorrectCredentials"));
            req.getRequestDispatcher(PageKey.REGISTRATION_PAGE.toString()).forward(req, resp);
        }
    }
}
