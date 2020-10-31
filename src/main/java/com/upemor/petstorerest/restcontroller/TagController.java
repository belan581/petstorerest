package com.upemor.petstorerest.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.upemor.petstorerest.exception.UserErrorException;
import com.upemor.petstorerest.model.Tag;
import com.upemor.petstorerest.model.User;
import com.upemor.petstorerest.service.TagService;

@RestController
@RequestMapping("/api/tag")
public class TagController {
	
	@Autowired
	private TagService tagService;
	
	@GetMapping("/")
	public ResponseEntity<List<Tag>> listallTags(){
		List<Tag> tags = tagService.listAllTags();
		return new ResponseEntity<List<Tag>>(tags, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Tag> findById(@PathVariable("id") final int id){
		Tag tag = tagService.findById(id);
		return new ResponseEntity<Tag>(tag, HttpStatus.OK);
		
	}

}
