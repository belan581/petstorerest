package com.upemor.petstorerest.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Pet {
	
	@Id
	@GeneratedValue
	private int id;
	
	private String name;
	
	private String photourl;
	
	private boolean status;
	
	private float price;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private Category category;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tag_id")
    private Tag tag;
	
	@OneToMany(mappedBy = "pet", cascade = {
	        CascadeType.ALL
	    })
	
	private List < Orderpet > orders;

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

	public String getPhotourl() {
		return photourl;
	}

	public void setPhotourl(String photourl) {
		this.photourl = photourl;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public Pet() {
		// TODO Auto-generated constructor stub
	}

}
