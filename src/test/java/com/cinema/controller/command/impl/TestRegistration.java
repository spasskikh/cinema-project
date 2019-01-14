package com.cinema.controller.command.impl;

import com.cinema.model.entity.User;
import com.cinema.service.impl.UserService;
import com.cinema.util.ResourceManager;
import com.cinema.util.constants.PageKey;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

class TestRegistration {

    private ViewRegistrationPage registrationPage;
    private HttpServletRequest req;
    private HttpServletResponse resp;
    private RequestDispatcher dispatcher;
    private UserService userService;

    private static final String LOGIN = "Login";
    private static final String PASS = "Password";
    private static final String RE_PASS = "Re-Password";

    @BeforeEach
    void init() {
        registrationPage = new ViewRegistrationPage();
        req = Mockito.mock(HttpServletRequest.class);
        resp = Mockito.mock(HttpServletResponse.class);
        dispatcher = Mockito.mock(RequestDispatcher.class);
        userService = Mockito.mock(UserService.class);
        registrationPage.userService = userService;
    }

    @Test
    void test_GET() throws ServletException, IOException {
        Mockito.when(req.getMethod()).thenReturn("GET");
        Mockito.when(req.getRequestDispatcher(PageKey.REGISTRATION_PAGE.toString())).thenReturn(dispatcher);

        registrationPage.execute(req, resp);

        Mockito.verify(req).getMethod();
        Mockito.verify(req).getRequestDispatcher(PageKey.REGISTRATION_PAGE.toString());
        Mockito.verify(dispatcher).forward(req, resp);
        Mockito.verifyNoMoreInteractions(req, resp, dispatcher);
    }

    @Test
    void test_POST_bad_login() throws ServletException, IOException {
        Mockito.when(req.getMethod()).thenReturn("POST");
        Mockito.when(req.getParameter(LOGIN)).thenReturn(LOGIN);
        Mockito.when(req.getParameter(PASS)).thenReturn(PASS);
        Mockito.when(req.getParameter(RE_PASS)).thenReturn(RE_PASS);
        Mockito.when(userService.validateLoginInput(LOGIN)).thenReturn(false);
        Mockito.when(req.getRequestDispatcher(PageKey.REGISTRATION_PAGE.toString())).thenReturn(dispatcher);

        registrationPage.execute(req, resp);

        Mockito.verify(req).getMethod();
        Mockito.verify(req).getParameter(LOGIN);
        Mockito.verify(req).getParameter(PASS);
        Mockito.verify(req).getParameter(RE_PASS);
        Mockito.verify(userService).validateLoginInput(LOGIN);
        Mockito.verify(req).setAttribute("msg", ResourceManager.INSTANCE.getValue("incorrectCredentials"));
        Mockito.verify(req).getRequestDispatcher(PageKey.REGISTRATION_PAGE.toString());
        Mockito.verify(dispatcher).forward(req, resp);
        Mockito.verifyNoMoreInteractions(req, resp, dispatcher, userService);
    }

    @Test
    void test_POST_bad_pass() throws ServletException, IOException {
        Mockito.when(req.getMethod()).thenReturn("POST");
        Mockito.when(req.getParameter(LOGIN)).thenReturn(LOGIN);
        Mockito.when(req.getParameter(PASS)).thenReturn(PASS);
        Mockito.when(req.getParameter(RE_PASS)).thenReturn(RE_PASS);
        Mockito.when(userService.validateLoginInput(LOGIN)).thenReturn(true);
        Mockito.when(userService.validatePasswordInput(PASS, RE_PASS)).thenReturn(false);
        Mockito.when(req.getRequestDispatcher(PageKey.REGISTRATION_PAGE.toString())).thenReturn(dispatcher);

        registrationPage.execute(req, resp);

        Mockito.verify(req).getMethod();
        Mockito.verify(req).getParameter(LOGIN);
        Mockito.verify(req).getParameter(PASS);
        Mockito.verify(req).getParameter(RE_PASS);
        Mockito.verify(userService).validateLoginInput(LOGIN);
        Mockito.verify(userService).validatePasswordInput(PASS, RE_PASS);
        Mockito.verify(req).setAttribute("msg", ResourceManager.INSTANCE.getValue("incorrectCredentials"));
        Mockito.verify(req).getRequestDispatcher(PageKey.REGISTRATION_PAGE.toString());
        Mockito.verify(dispatcher).forward(req, resp);
        Mockito.verifyNoMoreInteractions(req, resp, dispatcher, userService);
    }

    @Test
    void test_POST_user_exists() throws ServletException, IOException {
        Mockito.when(req.getMethod()).thenReturn("POST");
        Mockito.when(req.getParameter(LOGIN)).thenReturn(LOGIN);
        Mockito.when(req.getParameter(PASS)).thenReturn(PASS);
        Mockito.when(req.getParameter(RE_PASS)).thenReturn(RE_PASS);
        Mockito.when(userService.validateLoginInput(LOGIN)).thenReturn(true);
        Mockito.when(userService.validatePasswordInput(PASS, RE_PASS)).thenReturn(true);
        Mockito.when(userService.getUser(LOGIN)).thenReturn(new User());
        Mockito.when(req.getRequestDispatcher(PageKey.REGISTRATION_PAGE.toString())).thenReturn(dispatcher);

        registrationPage.execute(req, resp);

        Mockito.verify(req).getMethod();
        Mockito.verify(req).getParameter(LOGIN);
        Mockito.verify(req).getParameter(PASS);
        Mockito.verify(req).getParameter(RE_PASS);
        Mockito.verify(userService).validateLoginInput(LOGIN);
        Mockito.verify(userService).validatePasswordInput(PASS, RE_PASS);
        Mockito.verify(userService).getUser(LOGIN);
        Mockito.verify(req).setAttribute("msg", ResourceManager.INSTANCE.getValue("incorrectCredentials"));
        Mockito.verify(req).getRequestDispatcher(PageKey.REGISTRATION_PAGE.toString());
        Mockito.verify(dispatcher).forward(req, resp);
        Mockito.verifyNoMoreInteractions(req, resp, dispatcher, userService);
    }

    @Test
    void test_POST_ok() throws ServletException, IOException {
        Mockito.when(req.getMethod()).thenReturn("POST");
        Mockito.when(req.getParameter(LOGIN)).thenReturn(LOGIN);
        Mockito.when(req.getParameter(PASS)).thenReturn(PASS);
        Mockito.when(req.getParameter(RE_PASS)).thenReturn(RE_PASS);
        Mockito.when(userService.validateLoginInput(LOGIN)).thenReturn(true);
        Mockito.when(userService.validatePasswordInput(PASS, RE_PASS)).thenReturn(true);
        Mockito.when(userService.getUser(LOGIN)).thenReturn(null);
        Mockito.when(req.getRequestDispatcher(PageKey.LOGIN_PAGE.toString())).thenReturn(dispatcher);

        registrationPage.execute(req, resp);

        Mockito.verify(req).getMethod();
        Mockito.verify(req).getParameter(LOGIN);
        Mockito.verify(req).getParameter(PASS);
        Mockito.verify(req).getParameter(RE_PASS);
        Mockito.verify(userService).validateLoginInput(LOGIN);
        Mockito.verify(userService).validatePasswordInput(PASS, RE_PASS);
        Mockito.verify(userService).getUser(LOGIN);
        Mockito.verify(userService).createUser(LOGIN, PASS);
        Mockito.verify(req).setAttribute("msg", ResourceManager.INSTANCE.getValue("registrationSuccess"));
        Mockito.verify(req).getRequestDispatcher(PageKey.LOGIN_PAGE.toString());
        Mockito.verify(dispatcher).forward(req, resp);
        Mockito.verifyNoMoreInteractions(req, resp, dispatcher, userService);
    }


}
