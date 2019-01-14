package com.cinema.controller.command.impl;

import com.cinema.model.entity.User;
import com.cinema.model.entity.UserRole;
import com.cinema.service.impl.UserService;
import com.cinema.util.CommandManager;
import com.cinema.util.ResourceManager;
import com.cinema.util.constants.CommandKey;
import com.cinema.util.constants.PageKey;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

class TestLogin {

    private ViewLoginPage loginPage;
    private HttpServletRequest req;
    private HttpServletResponse resp;
    private RequestDispatcher dispatcher;
    private UserService userService;
    private User user;
    private User admin;

    private static final String LOGIN = "Login";
    private static final String PASS = "Password";

    @BeforeEach
    void init() {
        loginPage = new ViewLoginPage();
        req = Mockito.mock(HttpServletRequest.class);
        resp = Mockito.mock(HttpServletResponse.class);
        dispatcher = Mockito.mock(RequestDispatcher.class);
        userService = Mockito.mock(UserService.class);
        loginPage.userService = userService;
        user = new User(1, "user", "user", new UserRole(1, "USER"));
        admin = new User(2, "admin", "admin", new UserRole(1, "ADMIN"));
    }

    @Test
    void test_GET() throws ServletException, IOException {
        Mockito.when(req.getMethod()).thenReturn("GET");
        Mockito.when(req.getRequestDispatcher(PageKey.LOGIN_PAGE.toString())).thenReturn(dispatcher);

        loginPage.execute(req, resp);

        Mockito.verify(req).getMethod();
        Mockito.verify(req).getRequestDispatcher(PageKey.LOGIN_PAGE.toString());
        Mockito.verify(dispatcher).forward(req, resp);
        Mockito.verifyNoMoreInteractions(req, resp, dispatcher);
    }

    @Test
    void test_POST_bad_login() throws ServletException, IOException {
        String paramResult = "login";

        Mockito.when(req.getMethod()).thenReturn("POST");
        Mockito.when(req.getParameter(LOGIN)).thenReturn(paramResult);
        Mockito.when(userService.getUser(paramResult)).thenReturn(null);
        Mockito.when(req.getRequestDispatcher(PageKey.LOGIN_PAGE.toString())).thenReturn(dispatcher);

        loginPage.execute(req, resp);

        Mockito.verify(req).getMethod();
        Mockito.verify(req).getParameter(LOGIN);
        Mockito.verify(userService).getUser(paramResult);
        Mockito.verify(req).setAttribute("msg", ResourceManager.INSTANCE.getValue("incorrectCredentials"));
        Mockito.verify(req).getRequestDispatcher(PageKey.LOGIN_PAGE.toString());
        Mockito.verify(dispatcher).forward(req, resp);
        Mockito.verifyNoMoreInteractions(req, resp, dispatcher, userService);
    }


    @Test
    void test_POST_ok_user() throws ServletException, IOException {
        String loginResult = "user";
        String passwordResult = "user";
        HttpSession session = Mockito.mock(HttpSession.class);

        Mockito.when(req.getMethod()).thenReturn("POST");
        Mockito.when(req.getParameter(LOGIN)).thenReturn(loginResult);
        Mockito.when(userService.getUser(loginResult)).thenReturn(user);
        Mockito.when(req.getParameter(PASS)).thenReturn(passwordResult);
        Mockito.when(userService.isValid(user, passwordResult)).thenReturn(true);
        Mockito.when(req.getSession()).thenReturn(session);

        loginPage.execute(req, resp);

        Mockito.verify(req).getMethod();
        Mockito.verify(req).getParameter(LOGIN);
        Mockito.verify(userService).getUser(loginResult);
        Mockito.verify(req).getParameter(PASS);
        Mockito.verify(userService).isValid(user, passwordResult);
        Mockito.verify(req).getSession();
        Mockito.verify(session).setAttribute("user", user);
        Mockito.verify(resp).sendRedirect(CommandManager.getRedirect(CommandKey.COMM_VIEW_HOME_PAGE.toString()));
        Mockito.verifyNoMoreInteractions(req, resp, userService, session);
    }


    @Test
    void test_POST_ok_admin() throws ServletException, IOException {
        String loginResult = "admin";
        String passwordResult = "admin";
        HttpSession session = Mockito.mock(HttpSession.class);

        Mockito.when(req.getMethod()).thenReturn("POST");
        Mockito.when(req.getParameter(LOGIN)).thenReturn(loginResult);
        Mockito.when(userService.getUser(loginResult)).thenReturn(admin);
        Mockito.when(req.getParameter(PASS)).thenReturn(passwordResult);
        Mockito.when(userService.isValid(admin, passwordResult)).thenReturn(true);
        Mockito.when(req.getSession()).thenReturn(session);

        loginPage.execute(req, resp);

        Mockito.verify(req).getMethod();
        Mockito.verify(req).getParameter(LOGIN);
        Mockito.verify(userService).getUser(loginResult);
        Mockito.verify(req).getParameter(PASS);
        Mockito.verify(userService).isValid(admin, passwordResult);
        Mockito.verify(req).getSession();
        Mockito.verify(session).setAttribute("user", admin);
        Mockito.verify(resp).sendRedirect(CommandManager.getRedirect(CommandKey.COMM_VIEW_ADMIN_PAGE.toString()));
        Mockito.verifyNoMoreInteractions(req, resp, userService, session);
    }
}
