package com.upemor.petstorerest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upemor.petstorerest.model.Category;
import com.upemor.petstorerest.model.Pet;
import com.upemor.petstorerest.model.Tag;
import com.upemor.petstorerest.repository.CategoryRepository;
import com.upemor.petstorerest.repository.PetRepository;
import com.upemor.petstorerest.repository.TagRepository;

@Service
public class PetServiceImp implements PetService{
	
	@Autowired
	private PetRepository petRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private TagRepository tagRepository;

	public List<Pet> listAllPets() {
		return petRepository.findAll();
	}

	public Pet findById(int id) {
		return petRepository.findById(id);
	}

	public boolean createPet(Pet pet) {
		if(pet.equals(petRepository.findByName(pet.getName()))) {
			return false;
		}
		Category cat = categoryRepository.findById(pet.getCategory().getId());
		Tag tag = tagRepository.findById(pet.getTag().getId());
		pet.setCategory(cat);
		pet.setTag(tag);
		petRepository.save(pet);
		return true;
	}

	public Pet updatePet(int id, Pet pet) {
		Pet current = petRepository.findById(id);
		current.setName(pet.getName());
		current.setPhotourl(pet.getPhotourl());
		current.setPrice(pet.getPrice());
		current.setStatus(pet.isStatus());
		current.setCategory(pet.getCategory());
		current.setTag(pet.getTag());
		petRepository.save(current);
		return current;
	}

	public void deletePet(int id) {
		petRepository.deleteById(id);
	}

}
