package com.cinema.model.dao.impl;

import com.cinema.model.dao.AbstractDAO;
import com.cinema.model.entity.Movie;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MoviesDAO extends AbstractDAO<Movie> {

    @Override
    public void create(Movie movie) {
        try (PreparedStatement st = conn.prepareStatement(
                "INSERT INTO cinema.MOVIES VALUES (?, ?, ?, ?, ?)")) {
            st.setInt(1, movie.getId());
            st.setString(2, movie.getName());
            st.setString(3, movie.getDescription());
            st.setInt(4, movie.getYear());
            st.setInt(5, movie.getDuration());

            st.execute();
        } catch (SQLException exc) {
            logger.error(exc);
        }
    }

    @Override
    public Movie read(Integer id) {
        try (PreparedStatement st = conn.prepareStatement(
                "SELECT * FROM cinema.MOVIES WHERE ID = ?")) {
            st.setInt(1, id);
            ResultSet resultSet = st.executeQuery();

            return createMovie(resultSet);
        } catch (SQLException exc) {
            logger.error(exc);
            return null;
        }
    }

    @Override
    public void update(Movie movie) {
        try (PreparedStatement st = conn.prepareStatement(
                "UPDATE cinema.MOVIES" +
                        " SET" +
                        "  NAME = ?" +
                        " ,DESCRIPTION = ?" +
                        " ,YEAR = ?" +
                        " ,DURATION = ?" +
                        "WHERE ID = ?")) {
            st.setString(1, movie.getName());
            st.setString(2, movie.getDescription());
            st.setInt(3, movie.getYear());
            st.setInt(4, movie.getDuration());
            st.setInt(5, movie.getId());

            st.execute();
        } catch (SQLException exc) {
            logger.error(exc);
        }
    }

    @Override
    public void delete(Movie movie) {
        try (PreparedStatement st = conn.prepareStatement(
                "DELETE FROM cinema.MOVIES WHERE ID = ?")) {
            st.setInt(1, movie.getId());

            st.execute();
        } catch (SQLException exc) {
            logger.error(exc);
        }
    }

    @Override
    public List<Movie> getAll() {
        List<Movie> list = new ArrayList<>();

        try (Statement st = conn.createStatement()) {
            ResultSet resultSet = st.executeQuery("SELECT * FROM cinema.MOVIES");

            while (resultSet.next()) {
                list.add(createMovie(resultSet));
            }
        } catch (SQLException exc) {
            logger.error(exc);
        }
        return list;
    }

    private Movie createMovie(ResultSet resultSet) throws SQLException {
        return new Movie(resultSet.getInt("ID"),
                resultSet.getString("NAME"),
                resultSet.getString("DESCRIPTION"),
                resultSet.getInt("YEAR"),
                resultSet.getInt("DURATION"));
    }
}
