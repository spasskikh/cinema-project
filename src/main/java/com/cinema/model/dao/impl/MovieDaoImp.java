package com.cinema.model.dao.impl;

import com.cinema.model.dao.MovieDaoH;
import com.cinema.model.dao.connection.HibernateUtil;
import com.cinema.model.entity.Movie;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class MovieDaoImp implements MovieDaoH {

    private SessionFactory factory;

    public MovieDaoImp() {
        factory = HibernateUtil.factory();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long create(Movie entity) {
        Session session = factory.openSession();
        try {
            session.beginTransaction();
            Long id = (Long) session.save(entity);
            session.getTransaction().commit();
            return id;
        } catch (HibernateException exc) {
            session.getTransaction().rollback();
            logger.error("Exception in Movie read method", exc);
            throw new RuntimeException(exc);
        } finally {
            session.close();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Movie read(Long id) {
        Session session = factory.openSession();
        session.beginTransaction();
        Movie movie = session.get(Movie.class, id);
        session.close();
        return movie;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean update(Movie entity) {
        Session session = factory.openSession();
        try {
            session.beginTransaction();
            session.update(entity);
            session.getTransaction().commit();
            return true;
        } catch (HibernateException exc) {
            session.getTransaction().rollback();
            logger.error("Exception in Movie update method", exc);
            throw new RuntimeException(exc);
        } finally {
            session.close();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean delete(Movie entity) {
        Session session = factory.openSession();
        try {
            session.beginTransaction();
            session.delete(entity);
            session.getTransaction().commit();
            return true;
        } catch (HibernateException exc) {
            session.getTransaction().rollback();
            logger.error("Exception in Movie delete method", exc);
            throw new RuntimeException(exc);
        } finally {
            session.close();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Movie> getAll() {
        Session session = factory.openSession();
        CriteriaQuery<Movie> query = session.getCriteriaBuilder().createQuery(Movie.class);
        return session.createQuery(query).getResultList();
    }
}
