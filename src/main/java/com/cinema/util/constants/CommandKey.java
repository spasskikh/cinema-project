package com.cinema.util.constants;

/**
 * Enum contains commands' keys
 *
 * @author Anton Spasskikh
 */
public enum CommandKey {

    COMM_VIEW_HOME_PAGE("view_home_page"),
    COMM_VIEW_LOGIN_PAGE("view_login_page"),
    COMM_VIEW_REGISTRATION_PAGE("view_registration_page"),
    COMM_VIEW_ERROR_PAGE("view_error_page"),
    COMM_VIEW_ADMIN_PAGE("view_admin_page"),
    COMM_LOGOUT("logout"),
    COMM_CHANGE_LOCALE("change_locale"),
    COMM_ATTENDANCE_REPORT("attendance_report"),
    COMM_ADD_NEW_MOVIE("add_new_movie"),
    COMM_ADD_NEW_SHOWTIME("add_new_showtime"),
    COMM_CANCEL_SHOWTIME("cancel_showtime"),
    COMM_VIEW_MOVIE_INFO("view_movie_info"),
    COMM_ORDER_TICKET("order_ticket");

    /**
     * command's key value field
     */
    private String key;

    /**
     * Constructor assigns key field with passed parameter
     */
    CommandKey(String key) {
        this.key = key;
    }

    /**
     * @return path field value
     */
    @Override
    public String toString() {
        return key;
    }

    /**
     * defines command's key
     *
     * @param id enum key
     * @return command's key enum
     * @throws IllegalArgumentException if no matches
     */
    public static CommandKey get(String id) {
        for (CommandKey c : CommandKey.values()) {
            if (c.key.equalsIgnoreCase(id)) {
                return c;
            }
        }
        throw new IllegalArgumentException("No such command \""+id+"\" defined.");
    }
}
