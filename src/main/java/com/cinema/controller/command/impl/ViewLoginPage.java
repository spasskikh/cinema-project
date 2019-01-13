package com.cinema.controller.command.impl;

import com.cinema.controller.command.Command;
import com.cinema.model.entity.User;
import com.cinema.service.ServiceFactory;
import com.cinema.service.impl.UserService;
import com.cinema.util.CommandManager;
import com.cinema.util.ResourceManager;
import com.cinema.util.constants.CommandKey;
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
public class ViewLoginPage implements Command {

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
            login(req, resp);
        } else {
            req.getRequestDispatcher(PageKey.LOGIN_PAGE.toString()).forward(req, resp);
        }
    }

    /**
     * reads passed parameters and performs login procedures
     *
     * @param req  request from client to server
     * @param resp response from server to client
     * @throws ServletException if ServletException occurs
     * @throws IOException      if IOException occurs
     */
    private void login(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        User user = userService.getUser(req.getParameter("Login"));
        boolean isValid = user != null && userService.isValid(user, req.getParameter("Password"));

        if (isValid) {
            req.getSession().setAttribute("user", user);

            if (user.getRole().getRoleName().equalsIgnoreCase("ADMIN")) {
                resp.sendRedirect(CommandManager.getRedirect(CommandKey.COMM_VIEW_ADMIN_PAGE.toString()));
            } else {
                resp.sendRedirect(CommandManager.getRedirect(CommandKey.COMM_VIEW_HOME_PAGE.toString()));
            }
        } else {
            req.setAttribute("msg", ResourceManager.INSTANCE.getValue("incorrectCredentials"));
            req.getRequestDispatcher(PageKey.LOGIN_PAGE.toString()).forward(req, resp);
        }
    }
}
