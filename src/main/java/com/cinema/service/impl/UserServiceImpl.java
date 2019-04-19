package com.cinema.service.impl;

import com.cinema.dto.UserDto;
import com.cinema.model.dao.UserDao;
import com.cinema.model.entity.User;
import com.cinema.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Override
    public User create(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return userDao.saveAndFlush(user);
    }

    @Override
    public User read(long id) {
        return userDao.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public User update(User user) {
        return userDao.saveAndFlush(user);
    }

    @Override
    public void delete(long id) {
        userDao.deleteById(id);
    }

    @Override
    public List<User> getAll() {
        return userDao.findAll();
    }

    @Override
    public User findByLogin(String login) {
        User byLogin = userDao.findByLogin(login);
        if (byLogin == null) {
            throw new EntityNotFoundException("User not found.");
        }
        return byLogin;
    }

    @Override
    public void registerNewUser(UserDto userDto) {
        User userByLogin = userDao.findByLogin(userDto.getLogin());
        if (userByLogin == null) {
            User newUser = User.newBuilder()
                    .setLogin(userDto.getLogin())
                    .setPassword(userDto.getPassword())
                    .build();
            create(newUser);
        } else {
            throw new IllegalArgumentException("User already exists");
        }
    }

    public boolean isPasswordValid(String raw, String encoded) {
        return encoder.matches(raw, encoded);
    }
}
