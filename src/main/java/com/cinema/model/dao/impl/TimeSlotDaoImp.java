package com.cinema.model.dao.impl;

import com.cinema.model.dao.TimeSlotDao;
import com.cinema.model.dao.connection.HibernateUtil;
import com.cinema.model.entity.TimeSlot;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class TimeSlotDaoImp implements TimeSlotDao {

    private SessionFactory factory;

    public TimeSlotDaoImp() {
        factory = HibernateUtil.factory();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long create(TimeSlot entity) {
        Session session = factory.openSession();
        try {
            session.beginTransaction();
            Long id = (Long) session.save(entity);
            session.getTransaction().commit();
            return id;
        } catch (HibernateException exc) {
            session.getTransaction().rollback();
            logger.error("Exception in TimeSlot read method", exc);
            throw new RuntimeException(exc);
        } finally {
            session.close();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TimeSlot read(Long id) {
        Session session = factory.openSession();
        session.beginTransaction();
        TimeSlot timeSlot = session.get(TimeSlot.class, id);
        session.close();
        return timeSlot;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean update(TimeSlot entity) {
        Session session = factory.openSession();
        try {
            session.beginTransaction();
            session.update(entity);
            session.getTransaction().commit();
            return true;
        } catch (HibernateException exc) {
            session.getTransaction().rollback();
            logger.error("Exception in TimeSlot update method", exc);
            throw new RuntimeException(exc);
        } finally {
            session.close();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean delete(TimeSlot entity) {
        Session session = factory.openSession();
        try {
            session.beginTransaction();
            session.delete(entity);
            session.getTransaction().commit();
            return true;
        } catch (HibernateException exc) {
            session.getTransaction().rollback();
            logger.error("Exception in TimeSlot delete method", exc);
            throw new RuntimeException(exc);
        } finally {
            session.close();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<TimeSlot> getAll() {
        Session session = factory.openSession();
        CriteriaQuery<TimeSlot> query = session.getCriteriaBuilder().createQuery(TimeSlot.class);
        Root<TimeSlot> timeSlots = query.from(TimeSlot.class);
        query.select(timeSlots);
        return session.createQuery(query).getResultList();
    }
}
