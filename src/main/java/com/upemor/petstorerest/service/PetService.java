package com.upemor.petstorerest.service;

import java.util.List;

import com.upemor.petstorerest.model.Pet;

public interface PetService {
	//Operaciones crud en base de datos
	
		List<Pet> listAllPets();
		
		Pet findById(int id);
		
		boolean createPet(Pet pet);
		
		Pet updatePet(int id, Pet pet);
		
		void deletePet(int id);
}
