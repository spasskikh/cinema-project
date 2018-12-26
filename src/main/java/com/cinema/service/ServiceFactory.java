package com.cinema.service;


import com.cinema.exception.NoSuchServiceExc;
import com.cinema.service.impl.MovieService;
import com.cinema.service.impl.UserService;

public class ServiceFactory {

    private enum ServiceList {
        MOVIE_SERVICE, USER_SERVICE
    }

    public static Service getService(String id){

        ServiceList serviceList = ServiceList.valueOf(id.toUpperCase());

        switch (serviceList) {
            case MOVIE_SERVICE:
                return new MovieService();
            case USER_SERVICE:
                return new UserService();
            default:
                throw new NoSuchServiceExc("No such serviceList: " + id);
        }
    }
}
