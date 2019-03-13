package com.cinema.model.dao;

import com.cinema.model.dao.impl.*;
import com.cinema.util.constants.DAOKey;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static com.cinema.util.constants.DAOKey.*;

/**
 * Class contains instances of dao classes
 *
 * @author Anton Spasskikh
 */
public class DAOFactory {

    /**
     * map for instances
     */
    private static Map<DAOKey, Supplier<AbstractDAO>> daoMap;

    static {
        daoMap = new HashMap<>();
        daoMap.put(USER_ROLE_DAO, UserRoleDAO::new);
        daoMap.put(USER_DAO, UserDAO::new);
        daoMap.put(MOVIE_DAO, MovieDAO::new);
        daoMap.put(SEAT_DAO, SeatDAO::new);
        daoMap.put(TIMESLOT_DAO, TimeSlotDAO::new);
        daoMap.put(SHOWTIME_DAO, ShowTimeDAO::new);
        daoMap.put(ORDER_DAO, OrderDAO::new);
    }

    /**
     * defines dao
     *
     * @param key dao identifier
     * @return dao entity
     */
    public static AbstractDAO getDAO(DAOKey key) {
        return daoMap.get(key).get();
    }
}
