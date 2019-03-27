package com.cinema.service.impl;

import com.cinema.model.dao.TicketDao;
import com.cinema.model.entity.Ticket;
import com.cinema.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketDao ticketDao;

    @Override
    public Ticket create(Ticket ticket) {
        return ticketDao.saveAndFlush(ticket);
    }

    @Override
    public Ticket read(long id) {
        return ticketDao.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Ticket update(Ticket ticket) {
        return ticketDao.saveAndFlush(ticket);
    }

    @Override
    public void delete(long id) {
        ticketDao.deleteById(id);
    }

    @Override
    public List<Ticket> getAll() {
        return ticketDao.findAll();
    }
}
