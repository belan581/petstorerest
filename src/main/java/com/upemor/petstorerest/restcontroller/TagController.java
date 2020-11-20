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
		try {
			List<Tag> tags = tagService.listAllTags();
			if(tags.isEmpty()) {
				return new ResponseEntity<List<Tag>>(tags, HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<List<Tag>>(tags, HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity<List<Tag>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Tag> findById(@PathVariable("id") final int id){
		Tag tag = tagService.findById(id);
		if(tag == null) {
			return new ResponseEntity<Tag>(tag, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Tag>(tag, HttpStatus.OK);
		
	}
	
	@PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Tag> createTag(@RequestBody final Tag tag) {
		if(!tagService.createTag(tag)) {
			return new ResponseEntity<Tag>(tag,HttpStatus.CONFLICT);
		}
		return new ResponseEntity<Tag>(tag, HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Tag> updateTag(@PathVariable("id") final int id, @RequestBody Tag tag) {
		Tag currentTag = tagService.findById(id);
		if (currentTag == null) {
			return new ResponseEntity<Tag>(tag, HttpStatus.NOT_FOUND);
		}
		Tag updatedTag = tagService.updateTag(id, tag);
		return new ResponseEntity<Tag>(updatedTag, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Tag> deleteTag(@PathVariable("id") final int id) {
		Tag currentTag = tagService.findById(id);
		if (currentTag == null) {
			return new ResponseEntity<Tag>(currentTag, HttpStatus.NOT_FOUND);
		}
		tagService.deleteTag(id);
		return new ResponseEntity<Tag>(HttpStatus.NO_CONTENT);
	}

}
