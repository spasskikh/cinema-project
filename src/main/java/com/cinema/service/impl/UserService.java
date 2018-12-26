package com.cinema.service.impl;

import com.cinema.model.dao.DAOFactory;
import com.cinema.model.dao.impl.UsersDAO;
import com.cinema.model.entity.User;
import com.cinema.model.entity.UserRole;
import org.jasypt.util.password.BasicPasswordEncryptor;
import com.cinema.service.Service;

public class UserService implements Service {

    private UsersDAO usersDAO;
    private BasicPasswordEncryptor bpe;

    public UserService() {
        this.usersDAO = (UsersDAO) DAOFactory.getDAO("users");
        this.bpe = new BasicPasswordEncryptor();
    }

    public User getUser(String login, String pass) {
        User user = usersDAO.getByLogin(login);
        if (user != null && bpe.checkPassword(pass, user.getPassword()))
            return user;
        else {
            return null;
        }
    }

    public void createUser(String login, String pass) {
        String encrPass = bpe.encryptPassword(pass);
        User user = new User(1, login, encrPass, UserRole.USER);
        usersDAO.create(user);
    }
}
