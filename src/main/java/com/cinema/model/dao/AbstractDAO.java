package com.cinema.model.dao;

import com.cinema.model.dao.connection.ConnDB;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.util.List;

public abstract class AbstractDAO <T> {

    protected Connection conn;
    protected static Logger logger =  LogManager.getLogger(AbstractDAO.class);

    protected AbstractDAO() {
        this.conn = ConnDB.getConnection();
    }

    public abstract void create(T t);
    public abstract T read(Integer id);
    public abstract void update(T t);
    public abstract void delete(T t);
    public abstract List<T> getAll();

}
