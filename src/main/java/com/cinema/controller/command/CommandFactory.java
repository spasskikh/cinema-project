package com.cinema.controller.command;

import com.cinema.controller.command.impl.*;
import com.cinema.util.constants.CommandsKey;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

import static com.cinema.util.constants.CommandsKey.*;

/**
 * class contains instances of command classes
 *
 * @author Anton Spasskikh
 */

public class CommandFactory {

    /**
     * Map for instances
     */
    private static Map<CommandsKey, Command> commands;

    static {
        commands = new HashMap<>();
        commands.put(COMM_VIEW_HOME_PAGE, new ViewHomePage());
        commands.put(COMM_VIEW_LOGIN_PAGE, new ViewLoginPage());
        commands.put(COMM_VIEW_REGISTRATION_PAGE, new ViewRegistrationPage());
        commands.put(COMM_VIEW_ERROR_PAGE, new ViewErrorPage());
    }

    /**
     * defines command
     *
     * @param req request from client
     * @return command entity
     */
    public static Command getCommand(HttpServletRequest req) {
        String id = req.getParameter("command");
        if (id == null) {
            return commands.get(COMM_VIEW_HOME_PAGE);
        }
        return commands.get(CommandsKey.get(id));
    }
}
