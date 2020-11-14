package com.upemor.petstorerest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.upemor.petstorerest.model.Pet;

@Repository
public interface PetRepository extends JpaRepository<Pet, Integer> {
	Pet findById(int id);

	Pet findByName(String name);
}