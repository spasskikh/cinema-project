package com.cinema.model.entity;

import java.util.Objects;

/**
 * Class describing user entity
 *
 * @author Anton Spasskikh
 */
public class Seat {

    /**
     * seat id field
     */
    private Integer id;

    /**
     * seat number field
     */
    private Integer number;

    /**
     * constructor without parameters
     */
    public Seat() {
    }

    /**
     * constructor with parameters, sets all fields
     */
    public Seat(Integer id, Integer number) {
        this.id = id;
        this.number = number;
    }

    /**
     * @return {@link #id}
     */
    public Integer getId() {
        return id;
    }

    /**
     * sets seat id
     *
     * @param id {@link #id}
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return {@link #number}
     */
    public Integer getNumber() {
        return number;
    }

    /**
     * sets seat number
     *
     * @param number {@link #number}
     */
    public void setNumber(Integer number) {
        this.number = number;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seat seat = (Seat) o;
        return Objects.equals(id, seat.id) &&
                Objects.equals(number, seat.number);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, number);
    }
}
