package com.cinema.model.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Class describing order entity
 *
 * @author Anton Spasskikh
 */
@Entity
@Table(name = "order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "DATE")
    private LocalDate date;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "showtime_id")
    private ShowTime showtime;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "seat_id")
    private Seat seat;

    public Order() {
    }

    public Order(Long id, LocalDate date, User user, ShowTime showtime, Seat seat) {
        this.id = id;
        this.date = date;
        this.user = user;
        this.showtime = showtime;
        this.seat = seat;
    }

    /**
     * @return {@link #id}
     */
    public Long getId() {
        return id;
    }

    /**
     * sets order id
     *
     * @param id {@link #id}
     */
    public void setId(Long id) {
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
     * @return {@link #showtime}
     */
    public ShowTime getShowtime() {
        return showtime;
    }

    /**
     * sets order showtime
     *
     * @param showtime {@link #showtime}
     */
    public void setShowtime(ShowTime showtime) {
        this.showtime = showtime;
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
                Objects.equals(showtime, order.showtime) &&
                Objects.equals(seat, order.seat);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, date, user, showtime, seat);
    }
}
