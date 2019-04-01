package com.cinema.service;

import com.cinema.model.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    User create(User user);

    User read(long id);

    User update(User user);

    void delete(long id);

    List<User> getAll();

    User findByLogin(String login);

}
