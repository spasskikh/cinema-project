package com.cinema.controller.command.impl;

import com.cinema.controller.command.Command;
import com.cinema.model.entity.Seat;
import com.cinema.model.entity.ShowTime;
import com.cinema.model.entity.User;
import com.cinema.service.ServiceFactory;
import com.cinema.service.impl.OrderService;
import com.cinema.service.impl.SeatService;
import com.cinema.service.impl.ShowTimeService;
import com.cinema.util.CommandManager;
import com.cinema.util.constants.CommandKey;
import com.cinema.util.constants.PageKey;
import com.cinema.util.constants.ServiceKey;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Class implementing Command interface
 *
 * @author Anton Spasskikh
 */
public class OrderTicket implements Command {

    /**
     * showtime service field
     */
    private ShowTimeService showTimeService = (ShowTimeService) ServiceFactory.getService(ServiceKey.SHOWTIME_SERVICE);

    /**
     * order service field
     */
    private OrderService orderService = (OrderService) ServiceFactory.getService(ServiceKey.ORDER_SERVICE);

    /**
     * seat service field
     */
    private SeatService seatService = (SeatService) ServiceFactory.getService(ServiceKey.SEAT_SERVICE);

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getMethod().equalsIgnoreCase("POST")) {
            User user = (User) req.getSession().getAttribute("user");
            if (user == null) {
                resp.sendRedirect(CommandManager.getRedirect(CommandKey.COMM_VIEW_LOGIN_PAGE.toString()));
                return;
            }

            Seat seat = seatService.getSeat(Integer.parseInt(req.getParameter("seatId")));
            ShowTime showTime = showTimeService.getShowTime(Integer.parseInt(req.getParameter("showTimeId")));
            orderService.create(user, showTime, seat);

            resp.sendRedirect(CommandManager.getRedirect(CommandKey.COMM_VIEW_HOME_PAGE.toString()));
        } else {
            String showTimeId = req.getParameter("showTimeId");
            ShowTime showTime = showTimeService.getShowTime(Integer.parseInt(showTimeId));
            List<Seat> freeSeats = seatService.getFreeSeats(orderService.getAlreadyBought(showTime));

            req.setAttribute("showTime", showTime);
            req.setAttribute("seats", freeSeats);
            req.getRequestDispatcher(PageKey.ORDER_TICKET_PAGE.toString()).forward(req, resp);
        }
    }
}


