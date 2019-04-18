package com.cinema.model.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * Class describing user entity
 *
 * @author Anton Spasskikh
 */
@Entity
@Table(name = "user")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @OneToOne
    @JoinColumn(name = "user_role_id")
    private UserRole role;

    public User() {
    }

    public User(Long id, String login, String password, UserRole role) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.role = role;
    }

    /**
     * @return {@link #id}
     */
    public Long getId() {
        return id;
    }

    /**
     * sets user id
     *
     * @param id {@link #id}
     */
    public void setId(Long id) {
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

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format("User{id=%d, login=%s, password=%s, role=%s", id, login, password, role);
    }

    public static Builder newBuilder() {
        return new User().new Builder();
    }

    public class Builder {

        private Builder() {
        }

        public Builder setLogin(String login) {
            User.this.setLogin(login);
            return this;
        }

        public Builder setPassword(String password) {
            User.this.setPassword(password);
            return this;
        }

        public User build() {
            User.this.setRole(new UserRole(2L, "USER"));
            return User.this;
        }
    }
}
