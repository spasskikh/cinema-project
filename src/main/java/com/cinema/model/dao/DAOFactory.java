package com.cinema.model.dao;

import com.cinema.exception.NoSuchDAOExc;
import com.cinema.model.dao.impl.*;

public class DAOFactory {

    private enum Entities {
        MOVIES, SEATS, TIME_SLOTS, USERS, SHOWTIME
    }

    public static AbstractDAO getDAO(String id) throws NoSuchDAOExc {

        Entities e = Entities.valueOf(id.toUpperCase());

        switch (e) {
            case MOVIES:
                return new MoviesDAO();
            case SEATS:
                return new SeatsDAO();
            case TIME_SLOTS:
                return new TimeSlotsDAO();
            case USERS:
                return new UsersDAO();
            case SHOWTIME:
                return new ShowTimeDAO();
            default:
                throw new NoSuchDAOExc("No such DAO: " + id);
        }
    }
}
