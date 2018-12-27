package com.cinema.service;

import com.cinema.service.impl.MovieService;
import com.cinema.service.impl.UserService;
import com.cinema.util.constants.ServicesKey;

import static com.cinema.util.constants.ServicesKey.*;

import java.util.HashMap;
import java.util.Map;

/**
 * class contains instances of service classes
 *
 * @author Anton Spasskikh
 */
public class ServiceFactory {

    /**
     * Map for instances
     */
    private static Map<ServicesKey, Service> services;

    static {
        services = new HashMap<>();
        services.put(MOVIE_SERVICE, new MovieService());
        services.put(USER_SERVICE, new UserService());
    }

    /**
     * defines service
     *
     * @param name service identifier
     * @return service entity
     */
    public static Service getService(ServicesKey name) {
        return services.get(name);
    }
}
