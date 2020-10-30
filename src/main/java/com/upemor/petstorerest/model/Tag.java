package com.upemor.petstorerest.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Tag")
public class Tag {
	
	@Id
	@GeneratedValue
	private int id;
	
	private String name;
	
	@OneToMany(mappedBy = "tag", cascade = {
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

	public Tag() {
		// TODO Auto-generated constructor stub
	}
	
}
