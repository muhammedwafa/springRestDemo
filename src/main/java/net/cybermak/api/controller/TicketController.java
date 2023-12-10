package net.cybermak.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import net.cybermak.api.model.Ticket;
import net.cybermak.api.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/tickets")
public class TicketController {
    @Autowired
    private TicketService ticketService;

    // Create a new ticket
    @Operation(summary = "Create a new ticket")
    @ApiResponse(responseCode = "200", description = "Ticket created successfully")
    @PostMapping
    public ResponseEntity<String> createTicket(@Valid @RequestBody Ticket ticket) {
        Ticket createdTicket = ticketService.createTicket(ticket);
        return ResponseEntity.ok("Ticket created successfully. Ticket ID: " + createdTicket.getTicketId());
    }

    // Read all tickets
    @GetMapping
    public ResponseEntity<List<Ticket>> getAllTickets() {
        List<Ticket> tickets = ticketService.getAllTickets();
        return ResponseEntity.ok(tickets);
    }

    // Read a ticket
    @GetMapping("/{ticketId}")
    public ResponseEntity<Ticket> getTicket(@PathVariable Long ticketId) {
        return ticketService.getTicket(ticketId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Update a ticket
    @PutMapping("/{ticketId}")
    public ResponseEntity<String> updateTicket(@PathVariable Long ticketId, @Valid @RequestBody Ticket updatedTicket) {
        return ticketService.updateTicket(ticketId, updatedTicket)
                .map(ticket -> ResponseEntity.ok("Ticket updated successfully. Ticket ID: " + ticket.getTicketId()))
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete a ticket
    @DeleteMapping("/{ticketId}")
    public ResponseEntity<String> deleteTicket(@PathVariable Long ticketId) {
        ticketService.deleteTicket(ticketId);
        return ResponseEntity.ok("Ticket deleted successfully. Ticket ID: " + ticketId);
    }


    @GetMapping("/check")
    public ResponseEntity<String> checkDeployment(@RequestParam(name = "userName", required = false, defaultValue = "Muhammed Wafa") String name) {
        return new ResponseEntity<String>("Hello, " + name + ". The App Deployed Successfully", HttpStatus.OK);
    }


}

