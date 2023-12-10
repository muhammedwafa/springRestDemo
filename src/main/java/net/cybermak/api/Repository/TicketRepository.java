package net.cybermak.api.Repository;

import net.cybermak.api.model.Ticket;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    // You can define custom query methods if needed
}
