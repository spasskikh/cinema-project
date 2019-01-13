package com.cinema.model.dao.impl;

import com.cinema.model.dao.AbstractDAO;
import com.cinema.model.entity.User;
import com.cinema.util.builder.UserBuilder;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Class provides operations for working with USER table in database
 *
 * @author Anton Spasskikh
 */
public class UserDAO extends AbstractDAO<User> {

    /**
     * {@inheritDoc}
     */
    @Override
    public void create(User user) {
        try (PreparedStatement st = conn.prepareStatement(
                "INSERT INTO cinema.USER (LOGIN, PASSWORD, ROLE_ID) VALUES (?, ?, ?)")) {
            st.setString(1, user.getLogin());
            st.setString(2, user.getPassword());
            st.setInt(3, user.getRole().getId());

            st.execute();
        } catch (SQLException exc) {
            logger.error(exc.getMessage(), exc);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User read(Integer id) {
        try (PreparedStatement st = conn.prepareStatement(
                "SELECT * FROM cinema.USER WHERE ID = ?")) {
            st.setInt(1, id);
            ResultSet resultSet = st.executeQuery();
            if (resultSet.next()) {
                return createUser(resultSet);
            }
        } catch (SQLException exc) {
            logger.error(exc.getMessage(), exc);
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(User user) {
        try (PreparedStatement st = conn.prepareStatement(
                "UPDATE cinema.USER" +
                        " SET LOGIN = ?, PASSWORD = ?, ROLE_ID = ?" +
                        "WHERE ID = ?")) {
            st.setString(1, user.getLogin());
            st.setString(2, user.getPassword());
            st.setInt(3, user.getRole().getId());
            st.setInt(4, user.getId());

            st.execute();
        } catch (SQLException exc) {
            logger.error(exc.getMessage(), exc);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(User user) {
        try (PreparedStatement st = conn.prepareStatement(
                "DELETE FROM cinema.USER WHERE ID = ?")) {
            st.setInt(1, user.getId());

            st.execute();
        } catch (SQLException exc) {
            logger.error(exc.getMessage(), exc);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<User> getAll() {
        List<User> list = new ArrayList<>();

        try (Statement st = conn.createStatement()) {
            ResultSet resultSet = st.executeQuery(
                    "SELECT * FROM cinema.USER");

            while (resultSet.next()) {
                list.add(createUser(resultSet));
            }
        } catch (SQLException exc) {
            logger.error(exc.getMessage(), exc);
        }
        return list;
    }

    /**
     * creates entity
     *
     * @param resultSet query result set
     * @return entity
     */
    private User createUser(ResultSet resultSet) throws SQLException {
        UserBuilder builder = new UserBuilder();
        return builder
                .buildId(resultSet.getInt("ID"))
                .buildLogin(resultSet.getString("LOGIN"))
                .buildPassword(resultSet.getString("PASSWORD"))
                .buildUserRole(resultSet.getInt("ROLE_ID"))
                .build();
    }
}
