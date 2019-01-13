package com.cinema.util.constants;

/**
 * Enum contains pages' path
 *
 * @author Anton Spasskikh
 */
public enum PageKey {

    HOME_PAGE("WEB-INF/jsp/home.jsp"),
    ERROR_PAGE("WEB-INF/jsp/error.jsp"),
    LOGIN_PAGE("WEB-INF/jsp/login.jsp"),
    REGISTRATION_PAGE("WEB-INF/jsp/registration.jsp"),
    ADMIN_PAGE("WEB-INF/jsp/admin.jsp"),
    ATTEND_REP_PAGE("WEB-INF/jsp/attend_rep.jsp"),
    NEW_MOVIE_PAGE("WEB-INF/jsp/new_movie.jsp"),
    NEW_SHOWTIME_PAGE("WEB-INF/jsp/new_showtime.jsp"),
    CANCEL_SHOWTIME_PAGE("WEB-INF/jsp/cancel_showtime.jsp"),
    MOVIE_INFO_PAGE("WEB-INF/jsp/movie_info.jsp"),
    ORDER_TICKET_PAGE("WEB-INF/jsp/order_ticket.jsp");

    /**
     * page's path value field
     */
    private String path;

    /**
     * Constructor assigns path field with passed parameter
     */
    PageKey(String path) {
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
