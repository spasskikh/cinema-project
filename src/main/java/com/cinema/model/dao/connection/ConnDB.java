package com.cinema.model.dao.connection;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Class provides connections to database
 *
 * @author Anton Spasskikh
 */
public class ConnDB {

    /**
     * datasource field
     */
    private static volatile DataSource dataSource;

    /**
     * logger field
     */
    private static Logger logger = LogManager.getLogger(ConnDB.class);

    /**
     * private constructor without parameters
     */
    private ConnDB() {
    }

    /**
     * @return {@link #dataSource}
     */
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

    /**
     * returns connection instance
     *
     * @return connection
     * @throws NullPointerException if get connection is impossible
     */
    public static Connection getConnection() {
        try {
            return getDataSource().getConnection();
        } catch (SQLException exc) {
            logger.error(exc.getMessage(),exc);
            throw new NullPointerException();
        }
    }
}
