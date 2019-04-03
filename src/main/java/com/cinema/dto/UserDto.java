package com.cinema.dto;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UserDto {

    @NotNull
    @NotEmpty
    private String login;

    @NotNull
    @NotEmpty
    private String password;

    private String matchingPassword;

    public UserDto() {
    }

    public UserDto(@NotNull @NotEmpty String login, @NotNull @NotEmpty String password, String matchingPassword) {
        this.login = login;
        this.password = password;
        this.matchingPassword = matchingPassword;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }

    @AssertTrue
    private boolean isValid() {
        return password != null && password.equals(matchingPassword);
    }
}