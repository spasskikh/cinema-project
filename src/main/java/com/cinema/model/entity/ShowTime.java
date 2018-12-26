package com.cinema.model.entity;

import java.time.LocalDate;

public class ShowTime {

    private Integer id;
    private LocalDate date;
    private Movie movie;
    private User user;
    private TimeSlot timeSlot;
    private Seat seat;

    public ShowTime() {
    }

    public ShowTime(Integer id, LocalDate date, Movie movie, User user, TimeSlot timeSlot, Seat seat) {
        this.id = id;
        this.date = date;
        this.movie = movie;
        this.user = user;
        this.timeSlot = timeSlot;
        this.seat = seat;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public TimeSlot getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(TimeSlot timeSlot) {
        this.timeSlot = timeSlot;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }
}
