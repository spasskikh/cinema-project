package com.cinema.util;

import java.util.regex.Pattern;

/**
 * Util class for input validation
 *
 * @author Anton Spasskikh
 */
public class Validator {

    /**
     * login regular expression field
     */
    private static final String LOGIN_VALIDATION_REGEX = "[A-Za-z0-9_]{5,20}";

    /**
     * password regular expression field
     */
    private static final String PASSWORD_VALIDATION_REGEX = "[A-Za-z0-9_]{5,20}";
//    private static final String PASSWORD_VALIDATION_REGEX = "^(?=.*\\d).{5,20}$";

    /**
     * validates login input
     *
     * @return true if input is correct
     */
    public static boolean validateLoginInput(String login) {
        return Pattern.matches(LOGIN_VALIDATION_REGEX, login);
    }

    /**
     * validates password input
     *
     * @return true if input is correct
     */
    public static boolean validatePasswordInput(String pass) {
        return Pattern.matches(PASSWORD_VALIDATION_REGEX, pass);
    }
}
