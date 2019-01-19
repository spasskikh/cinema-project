package com.cinema.model.entity;

import java.time.LocalTime;
import java.util.Objects;

/**
 * Class describing user entity
 *
 * @author Anton Spasskikh
 */
public class TimeSlot {

    /**
     * time slot id field
     */
    private Integer id;

    /**
     * time slot beginning time field
     */
    private LocalTime from;

    /**
     * time slot ending time field
     */
    private LocalTime till;

    /**
     * constructor without parameters
     */
    public TimeSlot() {
    }

    /**
     * constructor with parameters, sets all fields
     */
    public TimeSlot(Integer id, LocalTime from, LocalTime till) {
        this.id = id;
        this.from = from;
        this.till = till;
    }

    /**
     * @return {@link #id}
     */
    public Integer getId() {
        return id;
    }

    /**
     * sets time slot id
     *
     * @param id {@link #id}
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return {@link #from}
     */
    public LocalTime getFrom() {
        return from;
    }

    /**
     * sets time slot beginning time
     *
     * @param from {@link #from}
     */
    public void setFrom(LocalTime from) {
        this.from = from;
    }

    /**
     * @return {@link #till}
     */
    public LocalTime getTill() {
        return till;
    }

    /**
     * sets time slot ending time
     *
     * @param till {@link #till}
     */
    public void setTill(LocalTime till) {
        this.till = till;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimeSlot timeSlot = (TimeSlot) o;
        return Objects.equals(id, timeSlot.id) &&
                Objects.equals(from, timeSlot.from) &&
                Objects.equals(till, timeSlot.till);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, from, till);
    }
}
