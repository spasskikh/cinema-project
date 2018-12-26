package com.cinema.model.entity;

public class Seat {

    private Integer id;
    private Integer number;

    public Seat() {
    }

    public Seat(Integer id, Integer number) {
        this.id = id;
        this.number = number;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
