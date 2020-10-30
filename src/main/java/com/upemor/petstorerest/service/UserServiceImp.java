package com.upemor.petstorerest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.upemor.petstorerest.model.User;
import com.upemor.petstorerest.repository.UserRepository;

@Service
public class UserServiceImp implements UserService {
	
	@Autowired
	private UserRepository userRepository;

	public User findUserForLogin(String email, String password) {
		//BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        //password = encoder.encode(password);
		return userRepository.findByEmailAndPassword(email, password);
	}
	
	public List<User> listAllUsers(){
		return userRepository.findAll();
	}

	public User findById(int id) {
		User user = userRepository.findById(id); 
		return user;
	}

	
	public boolean createUser(User user) {
		if (userRepository.findByEmail(user.getEmail()) != null) {
			return false;
			}
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
		userRepository.saveAndFlush(user);
		return true;
	}

	public User updateUser(int id, User user) {
		User currentUser = userRepository.findById(id);
		currentUser.setUsername(user.getUsername());
		currentUser.setFirstname(user.getFirstname());
		currentUser.setLastname(user.getLastname());
		currentUser.setEmail(user.getEmail());
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        currentUser.setPassword(encoder.encode(user.getPassword()));
		currentUser.setStatus(user.isStatus());
		currentUser.setRole(user.getRole());
		userRepository.saveAndFlush(currentUser);
		
		return currentUser;
	}

	public void deleteUser(int id) {
		userRepository.deleteById(id);	
	}

	public boolean existsUserByEmail(String email) {
		return userRepository.existsUserByEmail(email);
	}
}
