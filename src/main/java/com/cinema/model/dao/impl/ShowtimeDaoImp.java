package com.cinema.model.dao.impl;

import com.cinema.model.dao.ShowtimeDao;
import com.cinema.model.dao.connection.HibernateUtil;
import com.cinema.model.entity.Showtime;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class ShowtimeDaoImp implements ShowtimeDao {

    private SessionFactory factory;

    public ShowtimeDaoImp() {
        factory = HibernateUtil.factory();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long create(Showtime entity) {
        Session session = factory.openSession();
        try {
            session.beginTransaction();
            Long id = (Long) session.save(entity);
            session.getTransaction().commit();
            return id;
        } catch (HibernateException exc) {
            session.getTransaction().rollback();
            logger.error("Exception in Showtime read method", exc);
            throw new RuntimeException(exc);
        } finally {
            session.close();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Showtime read(Long id) {
        Session session = factory.openSession();
        session.beginTransaction();
        Showtime showtime = session.get(Showtime.class, id);
        session.close();
        return showtime;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean update(Showtime entity) {
        Session session = factory.openSession();
        try {
            session.beginTransaction();
            session.update(entity);
            session.getTransaction().commit();
            return true;
        } catch (HibernateException exc) {
            session.getTransaction().rollback();
            logger.error("Exception in Showtime update method", exc);
            throw new RuntimeException(exc);
        } finally {
            session.close();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean delete(Showtime entity) {
        Session session = factory.openSession();
        try {
            session.beginTransaction();
            session.delete(entity);
            session.getTransaction().commit();
            return true;
        } catch (HibernateException exc) {
            session.getTransaction().rollback();
            logger.error("Exception in Showtime delete method", exc);
            throw new RuntimeException(exc);
        } finally {
            session.close();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Showtime> getAll() {
        Session session = factory.openSession();
        CriteriaQuery<Showtime> query = session.getCriteriaBuilder().createQuery(Showtime.class);
        return session.createQuery(query).getResultList();
    }

}
