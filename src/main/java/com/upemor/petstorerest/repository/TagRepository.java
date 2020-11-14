package com.upemor.petstorerest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.upemor.petstorerest.model.Tag;

@Repository
public interface TagRepository extends JpaRepository<Tag, Integer> {
	
	Tag findById(int id);

	Tag findByName(String name);
}
