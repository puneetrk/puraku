package com.puraku.rest.restfullwebservice.user;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Post {
	

	@Id
	@GeneratedValue
	private Integer id;
	
	public Integer getId() {
		return id;
	}

	private String description;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonIgnore
	private User user;


	@Override
	public String toString() {
		return "Post [id=" + id + ", description=" + description + "]";
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Post(String description, User user) {
		super();
		this.description = description;
		this.user = user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
