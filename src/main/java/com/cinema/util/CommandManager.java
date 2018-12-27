package com.cinema.util;

/**
 * Util class CommandManager
 *
 * @author Anton Spasskikh
 */
public class CommandManager {

    /**
     * gets location for redirect
     *
     * @param commandName command name for redirect
     * @return direction for redirect
     */
    public static String getLocationForRedirect(String commandName) {
        return "home?command=" + commandName;
    }
}
