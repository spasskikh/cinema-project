package com.cinema.model.dao.connection;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConnDB {

    private static volatile DataSource dataSource;
    private static Logger logger =  LogManager.getLogger(ConnDB.class);

    private ConnDB() {
    }

    private static DataSource getDataSource() {
        if (dataSource == null) {
            synchronized (ConnDB.class) {
                if (dataSource == null) {

                    BasicDataSource ds = new BasicDataSource();

                    ResourceBundle resource = ResourceBundle.getBundle("db");

                    ds.setDriverClassName(resource.getString("driver"));
                    ds.setUrl(resource.getString("url"));
                    ds.setUsername(resource.getString("user"));
                    ds.setPassword(resource.getString("pass"));
                    ds.setMinIdle(Integer.valueOf(resource.getString("min")));
                    ds.setMaxIdle(Integer.valueOf(resource.getString("max")));
                    ds.setMaxOpenPreparedStatements(Integer.valueOf(resource.getString("statements")));

                    dataSource = ds;
                }
            }
        }
        return dataSource;
    }

    public static Connection getConnection() {
        try {
            return getDataSource().getConnection();
        } catch (SQLException e) {
           logger.error(e);
           throw new RuntimeException(e);
        }
    }
}
