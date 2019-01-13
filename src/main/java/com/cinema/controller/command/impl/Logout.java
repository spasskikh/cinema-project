package com.cinema.controller.command.impl;

import com.cinema.controller.command.Command;
import com.cinema.util.CommandManager;
import com.cinema.util.constants.CommandKey;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Class implementing Command interface
 *
 * @author Anton Spasskikh
 */
public class Logout implements Command {

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();

        session.removeAttribute("user");
        session.invalidate();

        resp.sendRedirect(CommandManager.getRedirect(CommandKey.COMM_VIEW_HOME_PAGE.toString()));
    }
}
