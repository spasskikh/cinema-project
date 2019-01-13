package com.cinema.controller.command.impl;

import com.cinema.controller.command.Command;
import com.cinema.service.ServiceFactory;
import com.cinema.service.impl.ShowTimeService;
import com.cinema.util.Pagination;
import com.cinema.util.ResourceManager;
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
public class CancelShowTime implements Command {

    /**
     * showtime service field
     */
    private ShowTimeService showTimeService = (ShowTimeService) ServiceFactory.getService(ServiceKey.SHOWTIME_SERVICE);

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getMethod().equalsIgnoreCase("POST")) {
            cancelShowTime(req, resp);
        } else {
            Pagination paginator = Pagination.getPaginator(showTimeService.getAll(), 10);

            req.setAttribute("noOfPages", paginator.getNumberOfPages());
            req.setAttribute("currentPage", paginator.getCurrentPage(req.getParameter("page")));
            req.setAttribute("showTimes", paginator.paginate());

            req.getRequestDispatcher(PageKey.CANCEL_SHOWTIME_PAGE.toString()).forward(req, resp);
        }
    }

    /**
     * reads passed parameters and deletes showtime instance
     *
     * @param req  request from client to server
     * @param resp response from server to client
     * @throws ServletException if ServletException occurs
     * @throws IOException      if IOException occurs
     */
    private void cancelShowTime(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        showTimeService.delete(Integer.parseInt(req.getParameter("showTimeId")));
        req.setAttribute("msg", ResourceManager.INSTANCE.getValue("showTimeSuccessCancel"));
        req.getRequestDispatcher(PageKey.ADMIN_PAGE.toString()).forward(req, resp);
    }
}
