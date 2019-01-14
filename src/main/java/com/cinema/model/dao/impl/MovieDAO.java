package com.cinema.model.dao.impl;

import com.cinema.model.dao.AbstractDAO;
import com.cinema.model.entity.Movie;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Class provides operations for working with MOVIE table in database
 *
 * @author Anton Spasskikh
 */
public class MovieDAO extends AbstractDAO<Movie> {

    /**
     * {@inheritDoc}
     */
    @Override
    public void create(Movie movie) throws SQLException {
        conn.setAutoCommit(false);
        try (PreparedStatement st = conn.prepareStatement(
                "INSERT INTO cinema.MOVIE (NAME, DESCRIPTION, YEAR, DURATION) VALUES (?, ?, ?, ?)")) {
            st.setString(1, movie.getName());
            st.setString(2, movie.getDescription());
            st.setInt(3, movie.getYear());
            st.setInt(4, movie.getDuration());

            st.execute();
            conn.commit();
        } catch (SQLException exc) {
            logger.error(exc.getMessage(), exc);
            conn.rollback();
        } finally {
            conn.setAutoCommit(true);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Movie read(Integer id) throws SQLException {
        conn.setAutoCommit(false);
        try (PreparedStatement st = conn.prepareStatement(
                "SELECT * FROM cinema.MOVIE WHERE ID = ?")) {
            st.setInt(1, id);
            ResultSet resultSet = st.executeQuery();
            if (resultSet.next()) {
                return createMovie(resultSet);
            }
            conn.commit();
        } catch (SQLException exc) {
            logger.error(exc.getMessage(), exc);
            conn.rollback();
        } finally {
            conn.setAutoCommit(true);
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(Movie movie) throws SQLException {
        conn.setAutoCommit(false);
        try (PreparedStatement st = conn.prepareStatement(
                "UPDATE cinema.MOVIE" +
                        " SET NAME = ?, DESCRIPTION = ?, YEAR = ?, DURATION = ?" +
                        " WHERE ID = ?")) {
            st.setString(1, movie.getName());
            st.setString(2, movie.getDescription());
            st.setInt(3, movie.getYear());
            st.setInt(4, movie.getDuration());
            st.setInt(5, movie.getId());

            st.execute();
            conn.commit();
        } catch (SQLException exc) {
            logger.error(exc.getMessage(), exc);
            conn.rollback();
            exc.printStackTrace();
        } finally {
            conn.setAutoCommit(true);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(Movie movie) throws SQLException {
        conn.setAutoCommit(false);
        try (PreparedStatement st = conn.prepareStatement(
                "DELETE FROM cinema.MOVIE WHERE ID = ?")) {
            st.setInt(1, movie.getId());

            st.execute();
            conn.commit();
        } catch (SQLException exc) {
            logger.error(exc.getMessage(), exc);
            conn.rollback();
        } finally {
            conn.setAutoCommit(true);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Movie> getAll() throws SQLException {
        List<Movie> list = new ArrayList<>();

        conn.setAutoCommit(false);
        try (Statement st = conn.createStatement()) {
            ResultSet resultSet = st.executeQuery("SELECT * FROM cinema.MOVIE");

            while (resultSet.next()) {
                list.add(createMovie(resultSet));
            }
            conn.commit();
        } catch (SQLException exc) {
            logger.error(exc.getMessage(), exc);
            conn.rollback();
        } finally {
            conn.setAutoCommit(true);
        }
        return list;
    }

    /**
     * creates entity
     *
     * @param resultSet query result set
     * @return entity
     */
    private Movie createMovie(ResultSet resultSet) throws SQLException {
        return new Movie(resultSet.getInt("ID"),
                resultSet.getString("NAME"),
                resultSet.getString("DESCRIPTION"),
                resultSet.getInt("YEAR"),
                resultSet.getInt("DURATION"));
    }
}
