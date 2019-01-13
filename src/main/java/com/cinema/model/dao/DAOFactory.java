package com.cinema.model.dao;

import com.cinema.model.dao.impl.*;
import com.cinema.util.constants.DAOKey;

import java.util.HashMap;
import java.util.Map;

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
    private static Map<DAOKey, AbstractDAO> daoMap;

    static {
        daoMap = new HashMap<>();
        daoMap.put(USER_ROLE_DAO, new UserRoleDAO());
        daoMap.put(USER_DAO, new UserDAO());
        daoMap.put(MOVIE_DAO, new MovieDAO());
        daoMap.put(SEAT_DAO, new SeatDAO());
        daoMap.put(TIMESLOT_DAO, new TimeSlotDAO());
        daoMap.put(SHOWTIME_DAO, new ShowTimeDAO());
        daoMap.put(ORDER_DAO, new OrderDAO());
    }

    /**
     * defines dao
     *
     * @param key dao identifier
     * @return dao entity
     */
    public static AbstractDAO getDAO(DAOKey key) {
        return daoMap.get(key);
    }
}
