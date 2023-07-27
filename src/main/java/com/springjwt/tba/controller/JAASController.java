package com.springjwt.tba.controller;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springjwt.tba.model.User;
import com.springjwt.tba.service.UserService;
import com.springjwt.tba.util.JwtUtil;

import jakarta.servlet.http.HttpServletResponse;

@RestController
public class JAASController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;
	
	@PostMapping("/sign-up")
	public String addUser(@RequestBody User newUser) {
		return userService.RegisterUser(newUser);
	}
	
//	@PostMapping("/login")
//    public ResponseEntity<?> createAuthenticationToken(@RequestBody User authenticationUser, HttpServletResponse response) throws  IOException {
//		try {
//            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationUser.getEmail(), authenticationUser.getPassword()));
//        } catch (AuthenticationException e) {
//            return new ResponseEntity<>("Invalid user name" , HttpStatus.UNAUTHORIZED);
//        } 
//		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//       
//		final UserDetails userDetails = userService.loadUserByUsername(authenticationUser.getEmail());
//		System.out.println(userDetails);
//		final String passworddb = userDetails.getPassword();
//		final String password = (authenticationUser.getPassword());
//		
//		final String jwt = jwtUtil.generateToken(userDetails.getUsername());
////		
////		System.out.println(passwordEncoder.encode("jaggu"));
////		System.out.println(passwordEncoder.encode("jaggu"));
//		if(passwordEncoder.matches(password, passworddb)) {
//			return ResponseEntity.ok(jwt);
//		}else {
//			return new ResponseEntity<>("You Entered a Wrong Password. Please Check it once and try again" , HttpStatus.UNAUTHORIZED);
//			
//		}
//        
//		
//        
//    }
	
		@PostMapping("/login")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody User authenticationUser, HttpServletResponse response) throws IOException {
	    final UserDetails userDetails = userService.loadUserByUsername(authenticationUser.getEmail());
	    if(userDetails == null) {
	    	return new ResponseEntity<>("You Entered The Wrong Email. Please check it and try again.", HttpStatus.UNAUTHORIZED);   
	    }

	    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	    boolean passwordMatches = passwordEncoder.matches(authenticationUser.getPassword(), userDetails.getPassword());
	    if (passwordMatches) {
	        // Password is correct, generate JWT token
	        final String jwt = jwtUtil.generateToken(userDetails.getUsername());
	        return ResponseEntity.ok(jwt);
	    } else {
	        return new ResponseEntity<>("You Entered The Wrong Password. Please check it and try again.", HttpStatus.UNAUTHORIZED);
	    }
	}

	
	@GetMapping("/getAllUsers")
	public ArrayList<User> getAllUsers(){
		return userService.getAllUsers();
	}
}

