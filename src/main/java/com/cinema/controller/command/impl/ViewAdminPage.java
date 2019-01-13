package com.cinema.controller.command.impl;

import com.cinema.controller.command.Command;
import com.cinema.util.constants.PageKey;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Class implementing Command interface
 *
 * @author Anton Spasskikh
 */
public class ViewAdminPage implements Command {

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(PageKey.ADMIN_PAGE.toString()).forward(req, resp);
    }
}
