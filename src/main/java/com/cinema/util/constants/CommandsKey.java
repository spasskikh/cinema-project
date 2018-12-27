package com.cinema.util.constants;

/**
 * Enum contains commands' keys
 *
 * @author Anton Spasskikh
 */
public enum CommandsKey {

    COMM_VIEW_HOME_PAGE("view_home_page"),
    COMM_VIEW_LOGIN_PAGE("view_login_page"),
    COMM_VIEW_REGISTRATION_PAGE("view_registration_page"),
    COMM_VIEW_ERROR_PAGE("view_error_page");

    /**
     * command's key value field
     */
    private String key;

    /**
     * Constructor assigns key field with passed parameter
     */
    CommandsKey(String key) {
        this.key = key;
    }


    /**
     * defines command's key
     *
     * @param id enum key
     * @return command's key enum
     * @throws IllegalArgumentException if no matches
     */
    public static CommandsKey get(String id) {
        for (CommandsKey c : CommandsKey.values()) {
            if (c.key.equalsIgnoreCase(id)) {
                return c;
            }
        }
        throw new IllegalArgumentException("No such command \""+id+"\" defined.");
    }

    /**
     * @return path field value
     */
    @Override
    public String toString() {
        return key;
    }
}
