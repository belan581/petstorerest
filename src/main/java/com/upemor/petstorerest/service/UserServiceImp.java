package com.upemor.petstorerest.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.upemor.petstorerest.model.User;
import com.upemor.petstorerest.repository.UserRepository;

@Service
public class UserServiceImp implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder; //Inyeccion del bean encoder definido en la clase SecutiryConfig

	public User findUserForLogin(String email, String password) {
		//BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        //password = encoder.encode(password);
		return userRepository.findByEmailAndPassword(email, password);
	}
	
	public List<User> listAllUsers(){
		return userRepository.findAll();
	}

	public Optional<User> findById(int id) {
		return userRepository.findById(id);
	}

	
	public boolean createUser(User user) {
		if (userRepository.findByEmail(user.getEmail()) != null || userRepository.findByUsername(user.getUsername())!=null)  {
			return false;
			}
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
		userRepository.saveAndFlush(user);
		user.setPassword("");
		return true;
	}

	public User updateUser(int id, User user) {
		User currentUser = userRepository.findById(id)
				.orElseThrow(()-> new EntityNotFoundException());
		currentUser.setUsername(user.getUsername());
		currentUser.setFirstname(user.getFirstname());
		currentUser.setLastname(user.getLastname());
		currentUser.setEmail(user.getEmail());
        currentUser.setPassword(passwordEncoder.encode(user.getPassword()));
		currentUser.setStatus(user.isStatus());
		currentUser.setRole(user.getRole());
		userRepository.saveAndFlush(currentUser);
		currentUser.setPassword("");
		
		return currentUser;
	}

	public void deleteUser(int id) {
		userRepository.deleteById(id);	
	}

	public boolean existsUserByEmail(String email) {
		return userRepository.existsUserByEmail(email);
	}
}
