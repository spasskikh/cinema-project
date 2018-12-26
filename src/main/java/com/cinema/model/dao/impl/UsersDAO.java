package com.cinema.model.dao.impl;

import com.cinema.model.dao.AbstractDAO;
import com.cinema.model.entity.User;
import com.cinema.model.entity.UserRole;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UsersDAO extends AbstractDAO<User> {

    @Override
    public void create(User user) {

        boolean exists = checkRole(user.getRole());

        if (!exists) {
            createRole(user.getRole());
        }

        try (PreparedStatement st = conn.prepareStatement(
                "INSERT INTO cinema.USERS VALUES (?, ?, ?, ?)")) {
            st.setInt(1, user.getId());
            st.setString(2, user.getLogin());
            st.setString(3, user.getPassword());
            st.setInt(4, user.getRole().getId());
            st.execute();
        } catch (SQLException exc) {
            logger.error(exc);
        }
    }

    private void createRole(UserRole role) {
        try (PreparedStatement st = conn.prepareStatement(
                "INSERT INTO cinema.ROLES VALUES (?, ?)")) {
            st.setInt(1, role.getId());
            st.setString(2, role.toString());
            st.execute();
        } catch (SQLException exc) {
            logger.error(exc);
        }
    }

    private boolean checkRole(UserRole role) {
        try (PreparedStatement st = conn.prepareStatement(
                "SELECT * FROM cinema.ROLES WHERE ID = ?")) {
            st.setInt(1, role.getId());
            ResultSet resultSet = st.executeQuery();
            return resultSet.next();

        } catch (SQLException exc) {
            logger.error(exc);
            return false;
        }
    }


    @Override
    public User read(Integer id) {
        try (PreparedStatement st = conn.prepareStatement(
                "SELECT" +
                        "  users.ID" +
                        " ,users.LOGIN" +
                        " ,users.PASSWORD" +
                        " ,roles.ROLE_NAME" +
                        " FROM cinema.USERS users" +
                        " JOIN cinema.ROLES roles ON users.ROLE_ID = roles.ID" +
                        " WHERE users.id = ?")) {
            st.setInt(1, id);

            ResultSet resultSet = st.executeQuery();
            resultSet.next();
            return createUser(resultSet);
        } catch (SQLException exc) {
            System.out.println(exc);
            logger.error(exc);
            return null;
        }
    }

    @Override
    public void update(User user) {
        try (PreparedStatement st = conn.prepareStatement(
                "UPDATE cinema.USERS SET" +
                        "  LOGIN = ?" +
                        " ,PASSWORD = ?" +
                        " ,ROLE_ID = ?" +
                        "WHERE ID = ?")) {
            st.setString(1, user.getLogin());
            st.setString(2, user.getPassword());
            st.setInt(3, user.getRole().getId());
            st.setInt(4, user.getId());

            st.execute();
        } catch (SQLException exc) {
            logger.error(exc);
        }
    }

    @Override
    public void delete(User user) {
        try (PreparedStatement st = conn.prepareStatement(
                "DELETE FROM cinema.USERS WHERE ID = ?")) {
            st.setInt(1, user.getId());

            st.execute();
        } catch (SQLException exc) {
            logger.error(exc);
        }
    }

    @Override
    public List<User> getAll() {
        List<User> list = new ArrayList<>();

        try (Statement st = conn.createStatement()) {
            ResultSet resultSet = st.executeQuery(
                    "SELECT" +
                            "  users.ID" +
                            " ,users.LOGIN" +
                            " ,users.PASSWORD" +
                            " ,roles.ROLE_NAME" +
                            " FROM cinema.USERS users" +
                            " JOIN cinema.ROLES roles ON users.ROLE_ID = roles.ID");

            while (resultSet.next()) {
                list.add(createUser(resultSet));
            }
        } catch (SQLException exc) {
            logger.error(exc);
        }
        return list;
    }

    private User createUser(ResultSet resultSet) throws SQLException {
        return new User(resultSet.getInt("ID"),
                resultSet.getString("LOGIN"),
                resultSet.getString("PASSWORD"),
                UserRole.valueOf(resultSet.getString("ROLE_NAME")));
    }

    public User getByLogin(String login) {
        try (PreparedStatement st = conn.prepareStatement(
                "SELECT" +
                        "  users.ID" +
                        " ,users.LOGIN" +
                        " ,users.PASSWORD" +
                        " ,roles.ROLE_NAME" +
                        " FROM cinema.USERS users" +
                        " JOIN cinema.ROLES roles ON users.ROLE_ID = roles.ID" +
                        " WHERE users.LOGIN LIKE ?")) {
            st.setString(1, login);

            ResultSet resultSet = st.executeQuery();
            resultSet.next();
            return createUser(resultSet);
        } catch (SQLException exc) {
            logger.error(exc);
            return null;
        }
    }
}
