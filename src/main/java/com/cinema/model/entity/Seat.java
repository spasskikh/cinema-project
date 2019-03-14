package com.cinema.model.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * Class describing seat entity
 *
 * @author Anton Spasskikh
 */
@Entity
@Table(name = "seat")
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "NUMBER")
    private Integer number;

    public Seat() {
    }

    public Seat(Long id, Integer number) {
        this.id = id;
        this.number = number;
    }

    /**
     * @return {@link #id}
     */
    public Long getId() {
        return id;
    }

    /**
     * sets seat id
     *
     * @param id {@link #id}
     */
    public void setId(Long id) {
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
