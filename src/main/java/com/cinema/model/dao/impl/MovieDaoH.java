package com.cinema.model.dao.impl;

import java.util.List;

public interface MovieDaoH<T> {

    Long create(T entity);

    T read(Long id);

    boolean update(T entity);

    boolean delete(T entity);

    List<T> getAll();
}
