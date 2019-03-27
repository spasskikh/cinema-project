package com.cinema.service.impl;

import com.cinema.model.dao.UserRoleDao;
import com.cinema.model.entity.UserRole;
import com.cinema.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleDao userRoleDao;

    @Override
    public UserRole create(UserRole userRole) {
        return userRoleDao.saveAndFlush(userRole);
    }

    @Override
    public UserRole read(long id) {
        return userRoleDao.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public UserRole update(UserRole userRole) {
        return userRoleDao.saveAndFlush(userRole);
    }

    @Override
    public void delete(long id) {
        userRoleDao.deleteById(id);
    }

    @Override
    public List<UserRole> getAll() {
        return userRoleDao.findAll();
    }
}
