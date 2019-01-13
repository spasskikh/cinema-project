package com.cinema.model.entity;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Class describing order entity
 *
 * @author Anton Spasskikh
 */
public class Order {

    /**
     * order id field
     */
    private Integer id;

    /**
     * order date field
     */
    private LocalDate date;

    /**
     * order user field
     */
    private User user;

    /**
     * order showtime field
     */
    private ShowTime showTime;

    /**
     * order seat field
     */
    private Seat seat;

    /**
     * constructor without parameters
     */
    public Order() {
    }

    /**
     * constructor with parameters, sets all fields
     */
    public Order(Integer id, LocalDate date, User user, ShowTime showTime, Seat seat) {
        this.id = id;
        this.date = date;
        this.user = user;
        this.showTime = showTime;
        this.seat = seat;
    }

    /**
     * @return {@link #id}
     */
    public Integer getId() {
        return id;
    }

    /**
     * sets order id
     *
     * @param id {@link #id}
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return {@link #date}
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * sets order date
     *
     * @param date {@link #date}
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * @return {@link #user}
     */
    public User getUser() {
        return user;
    }

    /**
     * sets order user
     *
     * @param user {@link #user}
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * @return {@link #showTime}
     */
    public ShowTime getShowTime() {
        return showTime;
    }

    /**
     * sets order showtime
     *
     * @param showTime {@link #showTime}
     */
    public void setShowTime(ShowTime showTime) {
        this.showTime = showTime;
    }

    /**
     * @return {@link #seat}
     */
    public Seat getSeat() {
        return seat;
    }

    /**
     * sets order seat
     *
     * @param seat {@link #seat}
     */
    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) &&
                Objects.equals(date, order.date) &&
                Objects.equals(user, order.user) &&
                Objects.equals(showTime, order.showTime) &&
                Objects.equals(seat, order.seat);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, date, user, showTime, seat);
    }
}
