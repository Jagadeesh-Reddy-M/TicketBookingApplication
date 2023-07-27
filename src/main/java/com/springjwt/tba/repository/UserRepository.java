package com.springjwt.tba.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springjwt.tba.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	User findFirstByEmail(String email);
}
