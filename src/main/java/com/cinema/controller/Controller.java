package com.cinema.controller;

import com.cinema.controller.command.Command;
import com.cinema.controller.command.CommandFactory;
import com.cinema.util.CommandManager;
import com.cinema.util.constants.CommandKey;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Controller class
 *
 * @author Anton Spasskikh
 */

@WebServlet(name = "Controller", urlPatterns = "/home")
public class Controller extends HttpServlet {

    private static Logger logger = LogManager.getLogger(Controller.class);

    /**
     * {@inheritDoc}
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        process(req, resp);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    /**
     * defines command and executes it
     *
     * @param req  request from client to server
     * @param resp response from server to client
     * @throws ServletException     if ServletException occurs
     * @throws IOException          if IOException occurs
     * @throws NullPointerException if NullPointer occurs
     */
    private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Command command = CommandFactory.getCommand(req);
            command.execute(req, resp);
        } catch (NullPointerException | ServletException | IOException exc) {
            logger.error(exc.getMessage(), exc);
            resp.sendRedirect(CommandManager.getRedirect(CommandKey.COMM_VIEW_ERROR_PAGE.toString()));
//            TODO: delete printStackTrace
            exc.printStackTrace();
        }
    }
}

