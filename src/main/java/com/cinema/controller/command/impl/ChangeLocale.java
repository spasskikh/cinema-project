package com.cinema.controller.command.impl;

import com.cinema.controller.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.jstl.core.Config;
import java.io.IOException;
import java.util.Locale;

/**
 * Class implementing Command interface
 *
 * @author Anton Spasskikh
 */
public class ChangeLocale implements Command {

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String locale = req.getParameter("locale");
        String[] localArr = locale.split("_");
        Config.set(req.getSession(), Config.FMT_LOCALE, new Locale(localArr[0], localArr[1]));
        resp.sendRedirect(req.getHeader("Referer"));
    }

}
