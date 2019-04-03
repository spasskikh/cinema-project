package com.cinema.service.impl;

import com.cinema.dto.UserDto;
import com.cinema.model.dao.UserDao;
import com.cinema.model.entity.User;
import com.cinema.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User create(User user) {
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
    public UserDetails loadUserByUsername(String username) {
        User user = userDao.findByLogin(username);
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getLogin())
                .password(new BCryptPasswordEncoder().encode(user.getPassword()))
                .roles(user.getRole().getRoleName())
                .build();
    }

    @Override
    public void registerNewUser(UserDto userDto) {
        User userByLogin = userDao.findByLogin(userDto.getLogin());
        if (userByLogin == null) {
            User newUser = User.newBuilder()
                    .setLogin(userDto.getLogin())
                    .setPassword(userDto.getPassword())
                    .build();
            userDao.saveAndFlush(newUser);
        } else {
            throw new IllegalArgumentException("User already exists");
        }
    }
}
