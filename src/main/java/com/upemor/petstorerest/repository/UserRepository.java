package com.upemor.petstorerest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.upemor.petstorerest.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
	User findByEmailAndPassword(String email, String password);
	
	User findById(int id);
	
	User findByEmail(String email);
	
	boolean existsUserByEmail(String email);
	
	User findByUsername(String username);
}