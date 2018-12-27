package com.cinema.util.constants;

/**
 * Enum contains pages' path
 *
 * @author Anton Spasskikh
 */
public enum PagesKey {

    HOME_PAGE("/jsp/home.jsp"),
    LOGIN_PAGE("/jsp/login.jsp"),
    REGISTRATION_PAGE("/jsp/register.jsp"),
    ERROR_PAGE("/jsp/error.jsp");

    /**
     * page's path value field
     */
    private String path;

    /**
     * Constructor assigns path field with passed parameter
     */
    PagesKey(String path) {
        this.path = path;
    }

    /**
     * @return path field value
     */
    @Override
    public String toString() {
        return path;
    }
}
