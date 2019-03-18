package com.cinema.model.dao.impl;

import com.cinema.model.dao.UserRoleDao;
import com.cinema.model.dao.connection.HibernateUtil;
import com.cinema.model.entity.UserRole;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class UserRoleDaoImp implements UserRoleDao {

    private SessionFactory factory;

    public UserRoleDaoImp() {
        factory = HibernateUtil.factory();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long create(UserRole entity) {
        Session session = factory.openSession();
        try {
            session.beginTransaction();
            Long id = (Long) session.save(entity);
            session.getTransaction().commit();
            return id;
        } catch (HibernateException exc) {
            session.getTransaction().rollback();
            logger.error("Exception in UserRole read method", exc);
            throw new RuntimeException(exc);
        } finally {
            session.close();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserRole read(Long id) {
        Session session = factory.openSession();
        session.beginTransaction();
        UserRole userRole = session.get(UserRole.class, id);
        session.close();
        return userRole;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean update(UserRole entity) {
        Session session = factory.openSession();
        try {
            session.beginTransaction();
            session.update(entity);
            session.getTransaction().commit();
            return true;
        } catch (HibernateException exc) {
            session.getTransaction().rollback();
            logger.error("Exception in UserRole update method", exc);
            throw new RuntimeException(exc);
        } finally {
            session.close();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean delete(UserRole entity) {
        Session session = factory.openSession();
        try {
            session.beginTransaction();
            session.delete(entity);
            session.getTransaction().commit();
            return true;
        } catch (HibernateException exc) {
            session.getTransaction().rollback();
            logger.error("Exception in UserRole delete method", exc);
            throw new RuntimeException(exc);
        } finally {
            session.close();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UserRole> getAll() {
        Session session = factory.openSession();
        CriteriaQuery<UserRole> query = session.getCriteriaBuilder().createQuery(UserRole.class);
        Root<UserRole> userRoles = query.from(UserRole.class);
        query.select(userRoles);
        return session.createQuery(query).getResultList();
    }
}
