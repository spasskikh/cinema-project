package com.cinema.model.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Class describing ticket entity
 *
 * @author Anton Spasskikh
 */
@Entity
@Table(name = "ticket")
public class Ticket implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date")
    private LocalDate date;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "showtime_id")
    private Showtime showtime;

    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "seat_id")
    private Seat seat;

    public Ticket() {
    }

    public Ticket(Long id, LocalDate date, User user, Showtime showtime, Seat seat) {
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
     * sets ticket id
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
     * sets ticket date
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
     * sets ticket user
     *
     * @param user {@link #user}
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * @return {@link #showtime}
     */
    public Showtime getShowtime() {
        return showtime;
    }

    /**
     * sets ticket showtime
     *
     * @param showtime {@link #showtime}
     */
    public void setShowtime(Showtime showtime) {
        this.showtime = showtime;
    }

    /**
     * @return {@link #seat}
     */
    public Seat getSeat() {
        return seat;
    }

    /**
     * sets ticket seat
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
        Ticket ticket = (Ticket) o;
        return Objects.equals(id, ticket.id) &&
                Objects.equals(date, ticket.date) &&
                Objects.equals(user, ticket.user) &&
                Objects.equals(showtime, ticket.showtime) &&
                Objects.equals(seat, ticket.seat);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, date, user, showtime, seat);
    }
}
