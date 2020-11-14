package com.upemor.petstorerest.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.upemor.petstorerest.model.Category;
import com.upemor.petstorerest.service.CategoryService;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("/")
	public ResponseEntity<List<Category>> listallCategories(){
		List<Category> categories = categoryService.listAllCategories();
		if(categories.isEmpty()) {
			return new ResponseEntity<List<Category>>(categories, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Category>>(categories, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Category> findById(@PathVariable("id") final int id){
		Category tag = categoryService.findById(id);
		if(tag == null) {
			return new ResponseEntity<Category>(tag, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Category>(tag, HttpStatus.OK);
		
	}
	
	@PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Category> createCategory(@RequestBody final Category category) {
		if(!categoryService.createCategory(category)) {
			return new ResponseEntity<Category>(category,HttpStatus.CONFLICT);
		}
		return new ResponseEntity<Category>(category, HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Category> updateCategory(@PathVariable("id") final int id, @RequestBody Category tag) {
		Category currentCategory = categoryService.findById(id);
		if (currentCategory == null) {
			return new ResponseEntity<Category>(currentCategory, HttpStatus.NOT_FOUND);
		}
		Category updatedCategory = categoryService.updateCategory(id, currentCategory);
		return new ResponseEntity<Category>(updatedCategory, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Category> deleteCategory(@PathVariable("id") final int id) {
		Category currentCategory = categoryService.findById(id);
		if (currentCategory == null) {
			return new ResponseEntity<Category>(currentCategory, HttpStatus.NOT_FOUND);
		}
		categoryService.deleteCategory(id);
		return new ResponseEntity<Category>(HttpStatus.NO_CONTENT);
	}

}
