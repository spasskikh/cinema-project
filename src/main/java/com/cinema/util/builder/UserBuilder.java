package com.cinema.util.builder;

import com.cinema.model.dao.DAOFactory;
import com.cinema.model.dao.impl.UserRoleDAO;
import com.cinema.model.entity.User;
import com.cinema.util.constants.DAOKey;

/**
 * User builder class
 *
 * @author Anton Spasskikh
 */
public class UserBuilder {

    /**
     * user field
     */
    private User user;

    /**
     * constructor without parameters
     * instantiates {@link #user}
     */
    public UserBuilder() {
        this.user = new User();
    }

    /**
     * @return {@link #user}
     */
    public User build() {
        return user;
    }

    /**
     * sets id
     *
     * @return builder instance
     */
    public UserBuilder buildId(Integer id) {
        user.setId(id);
        return this;
    }

    /**
     * sets login
     *
     * @return builder instance
     */
    public UserBuilder buildLogin(String login) {
        user.setLogin(login);
        return this;
    }

    /**
     * sets password
     *
     * @return builder instance
     */
    public UserBuilder buildPassword(String password) {
        user.setPassword(password);
        return this;
    }

    /**
     * sets user role
     *
     * @return builder instance
     */
    public UserBuilder buildUserRole(Integer roleId) {
        UserRoleDAO dao = (UserRoleDAO) DAOFactory.getDAO(DAOKey.USER_ROLE_DAO);
        user.setRole(dao.read(roleId));
        return this;
    }
}
