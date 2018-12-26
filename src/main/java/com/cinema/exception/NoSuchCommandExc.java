package com.cinema.exception;

public class NoSuchCommandExc extends RuntimeException {

    public NoSuchCommandExc(String message) {
        super(message);
    }
}
