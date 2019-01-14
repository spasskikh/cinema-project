package com.cinema.model.dao.impl;

import com.cinema.model.dao.AbstractDAO;
import com.cinema.model.entity.Seat;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Class provides operations for working with SEAT table in database
 *
 * @author Anton Spasskikh
 */
public class SeatDAO extends AbstractDAO<Seat> {

    /**
     * {@inheritDoc}
     */
    @Override
    public void create(Seat seat) {
        try (PreparedStatement st = conn.prepareStatement(
                "INSERT INTO cinema.SEAT (NUMBER) VALUES (?)")) {
            st.setInt(1, seat.getNumber());

            st.execute();
        } catch (SQLException exc) {
            logger.error(exc.getMessage(), exc);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Seat read(Integer id) {
        try (PreparedStatement st = conn.prepareStatement(
                "SELECT * FROM cinema.SEAT WHERE ID = ?")) {
            st.setInt(1, id);
            ResultSet resultSet = st.executeQuery();
            if (resultSet.next()) {
                return createSeat(resultSet);
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
    public void update(Seat seat) {
        try (PreparedStatement st = conn.prepareStatement(
                "UPDATE cinema.SEAT" +
                        " SET NUMBER = ?" +
                        " WHERE ID = ?")) {
            st.setInt(1, seat.getNumber());
            st.setInt(2, seat.getId());

            st.execute();
        } catch (SQLException exc) {
            logger.error(exc.getMessage(), exc);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(Seat seat) {
        try (PreparedStatement st = conn.prepareStatement(
                "DELETE FROM cinema.SEAT WHERE ID = ?")) {
            st.setInt(1, seat.getId());

            st.execute();
        } catch (SQLException exc) {
            logger.error(exc);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Seat> getAll() {
        List<Seat> list = new ArrayList<>();

        try (Statement st = conn.createStatement()) {
            ResultSet resultSet = st.executeQuery("SELECT * FROM cinema.SEAT");

            while (resultSet.next()) {
                list.add(createSeat(resultSet));
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
    private Seat createSeat(ResultSet resultSet) throws SQLException {
        return new Seat(
                resultSet.getInt("ID"),
                resultSet.getInt("NUMBER"));
    }
}
