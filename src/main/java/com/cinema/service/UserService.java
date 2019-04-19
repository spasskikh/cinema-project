package com.cinema.service;

import com.cinema.dto.UserDto;
import com.cinema.model.entity.User;

import java.util.List;

public interface UserService {

    User create(User user);

    User read(long id);

    User update(User user);

    void delete(long id);

    List<User> getAll();

    User findByLogin(String login);

    void registerNewUser(UserDto userDto);

    boolean isPasswordValid(String raw, String encoded);
}
