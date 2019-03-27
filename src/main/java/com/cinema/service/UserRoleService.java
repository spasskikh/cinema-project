package com.cinema.service;

import com.cinema.model.entity.UserRole;

import java.util.List;

public interface UserRoleService {

    UserRole create(UserRole userRole);

    UserRole read(long id);

    UserRole update(UserRole userRole);

    void delete(long id);

    List<UserRole> getAll();
}
