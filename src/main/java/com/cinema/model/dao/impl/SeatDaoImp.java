package com.cinema.model.dao.impl;

import com.cinema.model.dao.SeatDao;
import com.cinema.model.dao.connection.HibernateUtil;
import com.cinema.model.entity.Seat;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class SeatDaoImp implements SeatDao {

    private SessionFactory factory;

    public SeatDaoImp() {
        factory = HibernateUtil.factory();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long create(Seat entity) {
        Session session = factory.openSession();
        try {
            session.beginTransaction();
            Long id = (Long) session.save(entity);
            session.getTransaction().commit();
            return id;
        } catch (HibernateException exc) {
            session.getTransaction().rollback();
            logger.error("Exception in Seat read method", exc);
            throw new RuntimeException(exc);
        } finally {
            session.close();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Seat read(Long id) {
        Session session = factory.openSession();
        session.beginTransaction();
        Seat seat = session.get(Seat.class, id);
        session.close();
        return seat;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean update(Seat entity) {
        Session session = factory.openSession();
        try {
            session.beginTransaction();
            session.update(entity);
            session.getTransaction().commit();
            return true;
        } catch (HibernateException exc) {
            session.getTransaction().rollback();
            logger.error("Exception in Seat update method", exc);
            throw new RuntimeException(exc);
        } finally {
            session.close();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean delete(Seat entity) {
        Session session = factory.openSession();
        try {
            session.beginTransaction();
            session.delete(entity);
            session.getTransaction().commit();
            return true;
        } catch (HibernateException exc) {
            session.getTransaction().rollback();
            logger.error("Exception in Seat delete method", exc);
            throw new RuntimeException(exc);
        } finally {
            session.close();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Seat> getAll() {
        Session session = factory.openSession();
        CriteriaQuery<Seat> query = session.getCriteriaBuilder().createQuery(Seat.class);
        return session.createQuery(query).getResultList();
    }
}
