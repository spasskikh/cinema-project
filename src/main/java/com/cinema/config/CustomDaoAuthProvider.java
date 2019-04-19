package com.cinema.config;

import com.cinema.model.entity.User;
import com.cinema.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;

@Component
public class CustomDaoAuthProvider implements AuthenticationProvider {

    @Autowired
    private UserService userService;

    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {
        String login = auth.getName();
        String password = auth.getCredentials().toString();
        try {
            User byLogin = userService.findByLogin(login);
            if (userService.isPasswordValid(password, byLogin.getPassword())) {
                return new UsernamePasswordAuthenticationToken(login, password, Collections.emptyList());
            } else {
                throw new BadCredentialsException("Incorrect credentials");
            }
        } catch (EntityNotFoundException exc) {
            throw new BadCredentialsException("User not found");
        }
    }

    @Override
    public boolean supports(Class<?> auth) {
        return auth.equals(UsernamePasswordAuthenticationToken.class);
    }
}
