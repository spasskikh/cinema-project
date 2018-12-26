package com.cinema.model.entity;

public class Movie {

    private Integer id;
    private String name;
    private String description;
    private Integer year;
    private Integer duration;

    public Movie() {
    }

    public Movie(Integer id, String name, String description, Integer year, Integer duration) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.year = year;
        this.duration = duration;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }
}
