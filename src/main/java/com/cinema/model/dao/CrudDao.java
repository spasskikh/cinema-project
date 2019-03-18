package com.cinema.model.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public interface CrudDao <T> {

    /**
     * logger field
     */
    Logger logger = LogManager.getLogger(CrudDao.class);

    /**
     * adds entity to database
     *
     * @param entity creating entity
     */
    Long create(T entity);

    /**
     * gets entity from database
     *
     * @param id id of entity
     * @return found entity
     */
    T read(Long id);

    /**
     * updates entity in database
     *
     * @param entity entity for update
     */
    boolean update(T entity);

    /**
     * deletes entity from database
     *
     * @param entity entity to delete
     */
    boolean delete(T entity);

    /**
     * gets all entities from database
     *
     * @return List of all entities
     */
    List<T> getAll();

}
