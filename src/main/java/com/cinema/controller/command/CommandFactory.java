package com.cinema.controller.command;

import com.cinema.controller.command.impl.*;
import com.cinema.exception.NoSuchCommandExc;

import javax.servlet.http.HttpServletRequest;

public class CommandFactory {

    private enum CommandList {
        GetAllMovies, Login, Movie, Register, Home
    }

    public static Command getCommand(HttpServletRequest req) throws NoSuchCommandExc {
//create map

//        group constants
        String id = req.getParameter("command");
        CommandList commandList = CommandList.valueOf(id);

        switch (commandList) {
            case GetAllMovies:
                return new GetAllMovies();
            case Login:
                return new Login();
            case Movie:
                return new Movie();
            case Register:
                return new Register();
            case Home:
                return new Home();
            default:
                return null;
        }
    }
}
