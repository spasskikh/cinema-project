package com.cinema.model.dao.impl;

import com.cinema.model.dao.AbstractDAO;
import com.cinema.model.entity.Seat;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SeatsDAO extends AbstractDAO<Seat> {

    @Override
    public void create(Seat seat) {
        try (PreparedStatement st = conn.prepareStatement(
                "INSERT INTO cinema.SEATS VALUES (?, ?)")) {
            st.setInt(1, seat.getId());
            st.setInt(2, seat.getNumber());

            st.execute();
        } catch (SQLException exc) {
            logger.error(exc);
        }
    }

    @Override
    public Seat read(Integer id) {
        try (PreparedStatement st = conn.prepareStatement(
                "SELECT * FROM cinema.SEATS WHERE ID = ?")) {
            st.setInt(1, id);
            ResultSet resultSet = st.executeQuery();
            resultSet.next();
            return createSeat(resultSet);
        } catch (SQLException exc) {
            logger.error(exc);
            return null;
        }
    }

    @Override
    public void update(Seat seat) {
        try (PreparedStatement st = conn.prepareStatement(
                "UPDATE cinema.SEATS" +
                        " SET" +
                        "  NUMBER = ?" +
                        "WHERE ID = ?")) {
            st.setInt(1, seat.getNumber());
            st.setInt(2, seat.getId());

            st.execute();
        } catch (SQLException exc) {
            logger.error(exc);
        }
    }

    @Override
    public void delete(Seat seat) {
        try (PreparedStatement st = conn.prepareStatement(
                "DELETE FROM cinema.SEATS WHERE ID = ?")) {
            st.setInt(1, seat.getId());

            st.execute();
        } catch (SQLException exc) {
            logger.error(exc);
        }
    }

    @Override
    public List<Seat> getAll() {
        List<Seat> list = new ArrayList<>();

        try (Statement st = conn.createStatement()) {
            ResultSet resultSet = st.executeQuery("SELECT * FROM cinema.SEATS");

            while (resultSet.next()) {
                list.add(createSeat(resultSet));
            }
        } catch (SQLException exc) {
            logger.error(exc);
        }
        return list;
    }

    private Seat createSeat(ResultSet resultSet) throws SQLException {
        return new Seat(resultSet.getInt("ID"), resultSet.getInt("NUMBER"));
    }
}
