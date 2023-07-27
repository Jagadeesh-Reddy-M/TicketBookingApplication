package com.springjwt.tba.model;

import java.util.Date;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Ticket")
@Data
@NoArgsConstructor
public class Ticket {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long ticketId;
	
	private String firstName;
	
	private String lastName;
	
	private String sourceStation;
	
	private String destinationStation;
	
	private String email;
	
	private Long mobileNumber;
	
	private Date travelDate = new Date();
	
	
	
}
