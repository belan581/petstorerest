package com.upemor.petstorerest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.upemor.petstorerest.model.Category;
import com.upemor.petstorerest.repository.CategoryRepository;

@Service
public class CategoryServiceImp implements CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;

	public List<Category> listAllCategories() {
		return categoryRepository.findAll();
	}

	
	public Category findById(int id) { 
		return categoryRepository.findById(id);
	}


	public boolean createCategory(Category category) {
		if (categoryRepository.findByName(category.getName()) != null) {
			return false;
		}
		categoryRepository.saveAndFlush(category);
		return true;
	}


	public Category updateCategory(int id, Category category) {
		if (categoryRepository.findById(category.getId()) != null) {
			return category;
		}
		return categoryRepository.saveAndFlush(category);
	}

	public void deleteCategory(int id) {
		categoryRepository.deleteById(id);
		
	}

}
