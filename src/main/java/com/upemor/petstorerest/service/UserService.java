package com.upemor.petstorerest.service;

import java.util.List;
import java.util.Optional;

import com.upemor.petstorerest.model.User;

public interface UserService {
	
	User findUserForLogin(String email, String password);
	
	List<User> listAllUsers();
	
	Optional<User> findById(int id);
	
	boolean createUser(User user);
	
	User updateUser(int id, User user);
	
	void deleteUser(int id);
	
	boolean existsUserByEmail(String email);

}
