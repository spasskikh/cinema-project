package com.cinema.service.impl;

import com.cinema.model.dao.DAOFactory;
import com.cinema.model.dao.impl.UserDAO;
import com.cinema.model.entity.User;
import com.cinema.model.entity.UserRole;
import com.cinema.util.Validator;
import com.cinema.util.constants.DAOKey;
import org.jasypt.util.password.BasicPasswordEncryptor;
import com.cinema.service.Service;
import org.jasypt.util.password.PasswordEncryptor;

import java.util.List;

/**
 * Class provides methods for working with User entity
 *
 * @author Anton Spasskikh
 */
public class UserService implements Service {

    /**
     * userDAO field
     */
    UserDAO userDAO;

    /**
     * basic password encryptor field
     */
    PasswordEncryptor bpe;

    /**
     * constructor without parameters, initializes {@link #userDAO} and {@link #bpe}
     */
    public UserService() {
        this.userDAO = (UserDAO) DAOFactory.getDAO(DAOKey.USER_DAO);
        this.bpe = new BasicPasswordEncryptor();
    }

    /**
     * returns user by login
     *
     * @param login user login
     * @return user instance
     */
    public User getUser(String login) {
        for (User user : getAll()) {
            if (user.getLogin().equals(login)) {
                return user;
            }
        }
        return null;
    }

    /**
     * returns all existing users
     *
     * @return list of users
     */
    public List<User> getAll() {
        return userDAO.getAll();
    }

    /**
     * creates user instance
     *
     * @param login user login
     * @param pass  user password
     */
    public void createUser(String login, String pass) {
        User user = new User();
        user.setLogin(login);
        user.setPassword(bpe.encryptPassword(pass));
        user.setRole(new UserRole(2, "USER"));
        userDAO.create(user);
    }

    /**
     * validates user
     *
     * @param user     user instance
     * @param password user password
     */
    public boolean isValid(User user, String password) {
        return bpe.checkPassword(password, user.getPassword());
    }

    /**
     * validates login on correctness
     *
     * @param login user login
     * @return true if correct
     */
    public boolean validateLoginInput(String login) {
        return Validator.validateLoginInput(login);
    }

    /**
     * validates passwords on correctness
     *
     * @param pass   user password
     * @param rePass user password
     * @return true if correct
     */
    public boolean validatePasswordInput(String pass, String rePass) {
        return pass.equals(rePass) && Validator.validatePasswordInput(pass);
    }
}
