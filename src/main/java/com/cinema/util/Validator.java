package com.cinema.util;

import javax.servlet.http.HttpServletRequest;

public class Validator {

    public static boolean isPost(HttpServletRequest reg) {
        return reg.getMethod().toUpperCase().equals("POST");
    }
}
