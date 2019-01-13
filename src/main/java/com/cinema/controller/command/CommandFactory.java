package com.cinema.controller.command;

import com.cinema.controller.command.impl.*;
import com.cinema.util.constants.CommandKey;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

import static com.cinema.util.constants.CommandKey.*;

/**
 * Class contains instances of command classes
 *
 * @author Anton Spasskikh
 */

public class CommandFactory {

    /**
     * map for instances
     */
    private static Map<CommandKey, Command> commands;

    static {
        commands = new HashMap<>();
        commands.put(COMM_VIEW_HOME_PAGE, new ViewHomePage());
        commands.put(COMM_VIEW_LOGIN_PAGE, new ViewLoginPage());
        commands.put(COMM_VIEW_REGISTRATION_PAGE, new ViewRegistrationPage());
        commands.put(COMM_VIEW_ERROR_PAGE, new ViewErrorPage());
        commands.put(COMM_VIEW_ADMIN_PAGE, new ViewAdminPage());
        commands.put(COMM_VIEW_MOVIE_INFO, new ViewMoviePage());
        commands.put(COMM_LOGOUT, new Logout());
        commands.put(COMM_CHANGE_LOCALE, new ChangeLocale());
        commands.put(COMM_ATTENDANCE_REPORT, new AttendanceReport());
        commands.put(COMM_ADD_NEW_MOVIE, new AddNewMovie());
        commands.put(COMM_ADD_NEW_SHOWTIME, new AddNewShowTime());
        commands.put(COMM_CANCEL_SHOWTIME, new CancelShowTime());
        commands.put(COMM_ORDER_TICKET, new OrderTicket());
    }

    /**
     * defines command
     *
     * @param req request from client
     * @return command instance
     */
    public static Command getCommand(HttpServletRequest req) {
        String id = req.getParameter("command");
        if (id == null) {
            return commands.get(COMM_VIEW_HOME_PAGE);
        }
        return commands.get(CommandKey.get(id));
    }
}
