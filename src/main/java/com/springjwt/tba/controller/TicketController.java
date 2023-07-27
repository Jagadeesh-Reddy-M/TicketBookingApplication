package com.springjwt.tba.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.springjwt.tba.model.Ticket;
import com.springjwt.tba.service.TicketService;

@RestController
@RequestMapping("/ticket")
public class TicketController {
	
	@Autowired
	private TicketService ticketService;
	
	@PostMapping("/BuyTicket")
	public String createTicket(@RequestBody Ticket ticket) {
		return ticketService.BuyTicket(ticket);
	}
	
	@PutMapping("/updateTicket/{ticketId}")
	public Ticket updateTicket(@PathVariable("ticketId") Long ticketId, @RequestBody Ticket ticket ) {
		return ticketService.updateTicket(ticketId , ticket);
	}
	
	@GetMapping("/showticket/{ticketId}")
	public Ticket getTicket(@PathVariable("ticketId") Long ticketId) {
		return ticketService.ShowTicket(ticketId);
	}
	
	@DeleteMapping("/cancelTicket/{ticketId}")
	public String cancelTicket(@PathVariable("ticketId") Long ticketId) {
		return ticketService.CancelTicket(ticketId);
	}
	
}
