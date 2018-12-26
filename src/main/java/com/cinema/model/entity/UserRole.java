package com.cinema.model.entity;

public enum UserRole {
    ADMIN(1),
    USER(2);

    private int id;

    UserRole(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
