package com.cinema.model.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Class describing showtime entity
 *
 * @author Anton Spasskikh
 */
@Entity
@Table(name = "showtime")
public class ShowTime {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "DATE")
    private LocalDate date;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "MOVIE_ID")
    private Movie movie;

    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "TIME_SLOT_ID")
    private TimeSlot timeSlot;


    public ShowTime() {
    }

    public ShowTime(Long id, LocalDate date, Movie movie, TimeSlot timeSlot) {
        this.id = id;
        this.date = date;
        this.movie = movie;
        this.timeSlot = timeSlot;
    }

    /**
     * @return {@link #id}
     */
    public Long getId() {
        return id;
    }

    /**
     * sets showtime id
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
     * sets showtime date
     *
     * @param date {@link #date}
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * @return {@link #movie}
     */
    public Movie getMovie() {
        return movie;
    }

    /**
     * sets showtime movie
     *
     * @param movie {@link #movie}
     */
    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    /**
     * @return {@link #timeSlot}
     */
    public TimeSlot getTimeSlot() {
        return timeSlot;
    }

    /**
     * sets showtime time slot
     *
     * @param timeSlot {@link #timeSlot}
     */
    public void setTimeSlot(TimeSlot timeSlot) {
        this.timeSlot = timeSlot;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShowTime showTime = (ShowTime) o;
        return Objects.equals(id, showTime.id) &&
                Objects.equals(date, showTime.date) &&
                Objects.equals(movie, showTime.movie) &&
                Objects.equals(timeSlot, showTime.timeSlot);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, date, movie, timeSlot);
    }
}
