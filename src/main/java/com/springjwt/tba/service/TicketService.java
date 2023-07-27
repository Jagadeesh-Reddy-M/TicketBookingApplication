package com.springjwt.tba.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springjwt.tba.model.Ticket;
import com.springjwt.tba.repository.TicketRepository;


@Service
public class TicketService {
	
	@Autowired
	private TicketRepository ticketRepository;
	
	//BuyTicket
	public String BuyTicket(Ticket buyTicket) {
		ticketRepository.save(buyTicket);
		return "Thankyou for choosing Us your ticket is Confirmed /n Happy Journey /n Please click button showTicket for ticket details";
	}
	
	//show Ticket 
	
	public Ticket ShowTicket(Long ticketId) {
		return ticketRepository.findById(ticketId).orElse(new Ticket());
	}
	
	//update Ticket
	
	public Ticket updateTicket(Long ticketId , Ticket ticket) {
		Ticket updateTicket = ShowTicket(ticketId);
		ticket.setFirstName(updateTicket.getFirstName());
		ticket.setLastName(updateTicket.getLastName());
		ticket.setSourceStation(updateTicket.getDestinationStation());
		ticket.setSourceStation(updateTicket.getSourceStation());
		ticket.setEmail(updateTicket.getEmail());
		ticket.setMobileNumber(updateTicket.getMobileNumber());
		return ticketRepository.save(ticket);
	}
	
	//Cancel Ticket
	public String CancelTicket(Long ticketId) {
		ticketRepository.deleteById(ticketId);
		return "Your Ticket is cancelled ";
	}
}
