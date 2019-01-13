package com.cinema.controller.command.impl;

import com.cinema.controller.command.Command;
import com.cinema.service.ServiceFactory;
import com.cinema.service.impl.AttendanceService;
import com.cinema.util.Pagination;
import com.cinema.util.constants.PageKey;
import com.cinema.util.constants.ServiceKey;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Class implementing Command interface
 *
 * @author Anton Spasskikh
 */
public class AttendanceReport implements Command {

    /**
     * attendance report service instance
     */
    private AttendanceService attendService = (AttendanceService) ServiceFactory.getService(ServiceKey.ATTENDANCE_SERVICE);

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Pagination paginator = Pagination.getPaginator(attendService.getAll(), 10);

        req.setAttribute("noOfPages", paginator.getNumberOfPages());
        req.setAttribute("currentPage", paginator.getCurrentPage(req.getParameter("page")));
        req.setAttribute("orders", paginator.paginate());

        req.getRequestDispatcher(PageKey.ATTEND_REP_PAGE.toString()).forward(req, resp);
    }
}
