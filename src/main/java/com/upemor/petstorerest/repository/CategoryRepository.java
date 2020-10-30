package com.upemor.petstorerest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.upemor.petstorerest.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
