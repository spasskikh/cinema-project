package com.cinema.model.dao.impl;

import com.cinema.model.dao.OrderDao;
import com.cinema.model.dao.connection.HibernateUtil;
import com.cinema.model.entity.Order;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class OrderDaoImp implements OrderDao {

    private SessionFactory factory;

    public OrderDaoImp() {
        factory = HibernateUtil.factory();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long create(Order entity) {
        Session session = factory.openSession();
        try {
            session.beginTransaction();
            Long id = (Long) session.save(entity);
            session.getTransaction().commit();
            return id;
        } catch (HibernateException exc) {
            session.getTransaction().rollback();
            logger.error("Exception in Order read method", exc);
            throw new RuntimeException(exc);
        } finally {
            session.close();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Order read(Long id) {
        Session session = factory.openSession();
        session.beginTransaction();
        Order order = session.get(Order.class, id);
        session.close();
        return order;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean update(Order entity) {
        Session session = factory.openSession();
        try {
            session.beginTransaction();
            session.update(entity);
            session.getTransaction().commit();
            return true;
        } catch (HibernateException exc) {
            session.getTransaction().rollback();
            logger.error("Exception in Order update method", exc);
            throw new RuntimeException(exc);
        } finally {
            session.close();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean delete(Order entity) {
        Session session = factory.openSession();
        try {
            session.beginTransaction();
            session.delete(entity);
            session.getTransaction().commit();
            return true;
        } catch (HibernateException exc) {
            session.getTransaction().rollback();
            logger.error("Exception in Order delete method", exc);
            throw new RuntimeException(exc);
        } finally {
            session.close();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Order> getAll() {
        Session session = factory.openSession();
        CriteriaQuery<Order> query = session.getCriteriaBuilder().createQuery(Order.class);
        return session.createQuery(query).getResultList();
    }
}
