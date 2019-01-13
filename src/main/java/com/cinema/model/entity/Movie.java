package com.cinema.model.entity;

import java.util.Objects;

/**
 * Class describing movie entity
 *
 * @author Anton Spasskikh
 */
public class Movie {

    /**
     * movie id field
     */
    private Integer id;

    /**
     * movie name field
     */
    private String name;

    /**
     * movie description field
     */
    private String description;

    /**
     * movie year field
     */
    private Integer year;

    /**
     * movie duration field
     */
    private Integer duration;

    /**
     * constructor without parameters
     */
    public Movie() {
    }

    /**
     * constructor with parameters, sets all fields
     */
    public Movie(Integer id, String name, String description, Integer year, Integer duration) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.year = year;
        this.duration = duration;
    }

    /**
     * @return {@link #id}
     */
    public Integer getId() {
        return id;
    }

    /**
     * sets movie id
     *
     * @param id {@link #id}
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return {@link #name}
     */
    public String getName() {
        return name;
    }

    /**
     * sets movie name
     *
     * @param name {@link #name}
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return {@link #description}
     */
    public String getDescription() {
        return description;
    }

    /**
     * sets movie description
     *
     * @param description {@link #description}
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return {@link #year}
     */
    public Integer getYear() {
        return year;
    }

    /**
     * sets movie year
     *
     * @param year {@link #year}
     */
    public void setYear(Integer year) {
        this.year = year;
    }

    /**
     * @return {@link #duration}
     */
    public Integer getDuration() {
        return duration;
    }

    /**
     * sets movie duration
     *
     * @param duration {@link #duration}
     */
    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Objects.equals(id, movie.id) &&
                Objects.equals(name, movie.name) &&
                Objects.equals(description, movie.description) &&
                Objects.equals(year, movie.year) &&
                Objects.equals(duration, movie.duration);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int prime = 31;
        int result;
        result = prime + id;
        result = prime * result + name.hashCode();
        result = prime * result + description.hashCode();
        result = prime * result + year;
        result = prime * result + duration;
        return result;
    }
}
