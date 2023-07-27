package com.springjwt.tba.model;

import jakarta.persistence.*;
import lombok.Data;
@Data
@Entity
@Table(name = "Registered_Users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer userId;
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private String password;
	
}

