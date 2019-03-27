package com.cinema.model.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.Objects;

/**
 * Class describing time slot entity
 *
 * @author Anton Spasskikh
 */
@Entity
@Table(name = "time_slot")
public class TimeSlot implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "start_time")
    private LocalTime startTime;

    @Column(name = "end_time")
    private LocalTime endTime;

    public TimeSlot() {
    }

    public TimeSlot(Long id, LocalTime startTime, LocalTime endTime) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * @return {@link #id}
     */
    public Long getId() {
        return id;
    }

    /**
     * sets time slot id
     *
     * @param id {@link #id}
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return {@link #startTime}
     */
    public LocalTime getStartTime() {
        return startTime;
    }

    /**
     * sets time slot beginning time
     *
     * @param from {@link #startTime}
     */
    public void setStartTime(LocalTime from) {
        this.startTime = from;
    }

    /**
     * @return {@link #endTime}
     */
    public LocalTime getEndTime() {
        return endTime;
    }

    /**
     * sets time slot ending time
     *
     * @param endTime {@link #endTime}
     */
    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
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
                Objects.equals(startTime, timeSlot.startTime) &&
                Objects.equals(endTime, timeSlot.endTime);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, startTime, endTime);
    }
}
