package com.upemor.petstorerest.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Orderpet {
	
	@Id
	@GeneratedValue
	private int id;
	
	private Date created;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pet_id")
    private Pet pet;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}



	public Orderpet() {
		// TODO Auto-generated constructor stub
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

}
