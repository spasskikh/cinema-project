package com.cinema.model.dao.impl;

import com.cinema.model.dao.UserDao;
import com.cinema.model.dao.connection.HibernateUtil;
import com.cinema.model.entity.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class UserDaoImp implements UserDao {

    private SessionFactory factory;

    public UserDaoImp() {
        factory = HibernateUtil.factory();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long create(User entity) {
        Session session = factory.openSession();
        try {
            session.beginTransaction();
            Long id = (Long) session.save(entity);
            session.getTransaction().commit();
            return id;
        } catch (HibernateException exc) {
            session.getTransaction().rollback();
            logger.error("Exception in User read method", exc);
            throw new RuntimeException(exc);
        } finally {
            session.close();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User read(Long id) {
        Session session = factory.openSession();
        session.beginTransaction();
        User user = session.get(User.class, id);
        session.close();
        return user;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean update(User entity) {
        Session session = factory.openSession();
        try {
            session.beginTransaction();
            session.update(entity);
            session.getTransaction().commit();
            return true;
        } catch (HibernateException exc) {
            session.getTransaction().rollback();
            logger.error("Exception in User update method", exc);
            throw new RuntimeException(exc);
        } finally {
            session.close();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean delete(User entity) {
        Session session = factory.openSession();
        try {
            session.beginTransaction();
            session.delete(entity);
            session.getTransaction().commit();
            return true;
        } catch (HibernateException exc) {
            session.getTransaction().rollback();
            logger.error("Exception in User delete method", exc);
            throw new RuntimeException(exc);
        } finally {
            session.close();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<User> getAll() {
        Session session = factory.openSession();
        CriteriaQuery<User> query = session.getCriteriaBuilder().createQuery(User.class);
        return session.createQuery(query).getResultList();
    }
}
