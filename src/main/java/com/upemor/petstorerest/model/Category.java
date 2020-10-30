package com.upemor.petstorerest.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Category {
	
	@Id
	@GeneratedValue
	private int id;
	
	private String name;
	
	@OneToMany(mappedBy = "category", cascade = {
	        CascadeType.ALL
	    })
	
	private List < Pet > pets;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Category() {
		// TODO Auto-generated constructor stub
	}

}
