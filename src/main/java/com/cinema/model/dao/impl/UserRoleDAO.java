package com.cinema.model.dao.impl;

import com.cinema.model.dao.AbstractDAO;
import com.cinema.model.entity.UserRole;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Class provides operations for working with USER_ROLE table in database
 *
 * @author Anton Spasskikh
 */
public class UserRoleDAO extends AbstractDAO<UserRole> {

    /**
     * {@inheritDoc}
     */
    @Override
    public void create(UserRole userRole) {
        try (PreparedStatement st = conn.prepareStatement(
                "INSERT INTO cinema.USER_ROLE VALUES (?, ?)")) {
            st.setInt(1, userRole.getId());
            st.setString(2, userRole.getRoleName());

            st.execute();
        } catch (SQLException exc) {
            logger.error(exc.getMessage(), exc);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserRole read(Integer id) {
        try (PreparedStatement st = conn.prepareStatement(
                "SELECT * FROM cinema.USER_ROLE WHERE ID = ?")) {
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
    public void update(UserRole userRole) {
        try (PreparedStatement st = conn.prepareStatement(
                "UPDATE cinema.USER_ROLE" +
                        " SET ROLE_NAME = ?" +
                        "WHERE ID = ?")) {
            st.setString(1, userRole.getRoleName());
            st.setInt(2, userRole.getId());

            st.execute();
        } catch (SQLException exc) {
            logger.error(exc.getMessage(), exc);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(UserRole userRole) {
        try (PreparedStatement st = conn.prepareStatement(
                "DELETE FROM cinema.USER WHERE ID = ?")) {
            st.setInt(1, userRole.getId());

            st.execute();
        } catch (SQLException exc) {
            logger.error(exc.getMessage(), exc);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UserRole> getAll() {
        List<UserRole> list = new ArrayList<>();

        try (Statement st = conn.createStatement()) {
            ResultSet resultSet = st.executeQuery(
                    "SELECT * FROM cinema.USER_ROLE");

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
    private UserRole createUser(ResultSet resultSet) throws SQLException {
        return new UserRole(
                resultSet.getInt("ID"),
                resultSet.getString("ROLE_NAME"));
    }
}
