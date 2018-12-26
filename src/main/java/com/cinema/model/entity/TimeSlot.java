package com.cinema.model.entity;

import java.time.LocalTime;

public class TimeSlot {

    private Integer id;
    private LocalTime from;
    private LocalTime till;
    private Integer duration;

    public TimeSlot() {
    }

    public TimeSlot(Integer id, LocalTime from, LocalTime till, Integer duration) {
        this.id = id;
        this.from = from;
        this.till = till;
        this.duration = duration;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalTime getFrom() {
        return from;
    }

    public void setFrom(LocalTime from) {
        this.from = from;
    }

    public LocalTime getTill() {
        return till;
    }

    public void setTill(LocalTime till) {
        this.till = till;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }
}
