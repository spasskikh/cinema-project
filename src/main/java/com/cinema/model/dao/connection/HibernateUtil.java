package com.cinema.model.dao.connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {

    /**
     * logger field
     */
    private static Logger logger = LogManager.getLogger(ConnDB.class);

    /**
     * private constructor without parameters
     */
    private HibernateUtil() {
    }

    private static volatile SessionFactory factory;

    /**
     * @return {@link #factory}
     */
    private static SessionFactory getFactory() {
        if (factory == null) {
            synchronized (HibernateUtil.class) {
                if (factory == null) {
                    StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
                    try {
                        factory = new MetadataSources(registry)
                                .buildMetadata()
                                .buildSessionFactory();
                    } catch (Exception exc) {
                        logger.error("Exception in HibernateUtil class while building factory", exc);
                        StandardServiceRegistryBuilder.destroy(registry);
                    }
                }
            }
        }
        return factory;
    }

    /**
     * returns built factory instance
     */
    public static SessionFactory factory() {
        return getFactory();
    }
}

