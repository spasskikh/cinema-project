package com.cinema.model.entity;

import java.util.Objects;

/**
 * Class describing user entity
 *
 * @author Anton Spasskikh
 */
public class User {

    /**
     * user id field
     */
    private Integer id;

    /**
     * user login field
     */
    private String login;

    /**
     * user password field
     */
    private String password;

    /**
     * user role field
     */
    private UserRole role;

    /**
     * constructor without parameters
     */
    public User() {
    }

    /**
     * constructor with parameters, sets all fields
     */
    public User(Integer id, String login, String password, UserRole role) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.role = role;
    }

    /**
     * @return {@link #id}
     */
    public Integer getId() {
        return id;
    }

    /**
     * sets user id
     *
     * @param id {@link #id}
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return {@link #login}
     */
    public String getLogin() {
        return login;
    }

    /**
     * sets user login
     *
     * @param login {@link #login}
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * @return {@link #password}
     */
    public String getPassword() {
        return password;
    }

    /**
     * sets user password
     *
     * @param password {@link #password}
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return {@link #role}
     */
    public UserRole getRole() {
        return role;
    }

    /**
     * sets user role
     *
     * @param role {@link #role}
     */
    public void setRole(UserRole role) {
        this.role = role;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(login, user.login) &&
                Objects.equals(password, user.password) &&
                Objects.equals(role, user.role);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, role);
    }
}
