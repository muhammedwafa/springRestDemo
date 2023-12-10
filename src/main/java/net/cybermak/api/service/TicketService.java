package net.cybermak.api.service;
import net.cybermak.api.Repository.TicketRepository;
import net.cybermak.api.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

import java.util.List;
import java.util.Optional;
@Service
@Validated
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    // Create a new ticket
    public Ticket createTicket(@Valid Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    // Read all tickets
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    // Read a ticket
    public Optional<Ticket> getTicket(Long ticketId) {
        return ticketRepository.findById(ticketId);
    }

    // Update a ticket
    public Optional<Ticket> updateTicket(Long ticketId, @Valid Ticket updatedTicket) {
        return ticketRepository.findById(ticketId)
                .map(existingTicket -> {
                    updatedTicket.setTicketId(existingTicket.getTicketId());
                    return ticketRepository.save(updatedTicket);
                });
    }

    // Delete a ticket
    public void deleteTicket(Long ticketId) {
        try {
            ticketRepository.deleteById(ticketId);
        } catch (EmptyResultDataAccessException ignored) {
            // Log or handle as needed
        }
    }
}
