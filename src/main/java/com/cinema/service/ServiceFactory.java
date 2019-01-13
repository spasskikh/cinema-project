package com.cinema.service;

import com.cinema.service.impl.*;
import com.cinema.util.constants.ServiceKey;

import java.util.HashMap;
import java.util.Map;

import static com.cinema.util.constants.ServiceKey.*;

/**
 * Class contains instances of service classes
 *
 * @author Anton Spasskikh
 */
public class ServiceFactory {

    /**
     * map for instances
     */
    private static Map<ServiceKey, Service> services;

    static {
        services = new HashMap<>();
        services.put(MOVIE_SERVICE, new MovieService());
        services.put(USER_SERVICE, new UserService());
        services.put(ATTENDANCE_SERVICE, new AttendanceService());
        services.put(TIMESLOT_SERVICE, new TimeSlotService());
        services.put(SHOWTIME_SERVICE, new ShowTimeService());
        services.put(ORDER_SERVICE, new OrderService());
        services.put(SEAT_SERVICE, new SeatService());
    }

    /**
     * defines service
     *
     * @param name service identifier
     * @return service entity
     */
    public static Service getService(ServiceKey name) {
        return services.get(name);
    }
}
