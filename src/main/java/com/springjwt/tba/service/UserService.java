package com.springjwt.tba.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.springjwt.tba.model.User;
import com.springjwt.tba.repository.UserRepository;

@Service
public class UserService implements UserDetailsService{
	
	
	@Autowired
	private UserRepository userRepository;
	
	public String RegisterUser(User user) {
		User userentity = new User();
		userentity.setFirstName(user.getFirstName());
		userentity.setLastName(user.getLastName());
		userentity.setEmail(user.getEmail());
		userentity.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		userRepository.save(userentity);
		return user.getFirstName()+user.getLastName()+" Registred Succesfully";
	}
	
	public ArrayList<User> getAllUsers(){
		List<User> allUsers = userRepository.findAll();
		ArrayList<User> allUsersfromdb = new ArrayList<>(allUsers);
		return allUsersfromdb;
		
	}

//    @Override
//    public UserDetails loadUserByUsername(String email)  {
//
//        //Write Logic to get the user from the DB
//        User user = userRepository.findFirstByEmail(email);
////        System.out.println(user);
//        if(user == null){
////            throw new UsernameNotFoundException("User not found",null);
//        	return null;
//        }
//        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), new ArrayList<>());
//    }
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
	    User user = userRepository.findFirstByEmail(email);
//	    System.out.println(user);
	    if (user == null) {
//	        String errorMessage = "User not found for email: " + email;
//	        System.err.println(errorMessage); // Print error message to the error console
//	        throw new UsernameNotFoundException(errorMessage);
	        return null;
	    }
	    return new org.springframework.security.core.userdetails.User(
	        user.getEmail(), user.getPassword(), new ArrayList<>()
	    );
	}

}

