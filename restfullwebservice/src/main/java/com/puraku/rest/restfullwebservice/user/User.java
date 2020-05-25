package com.puraku.rest.restfullwebservice.user;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

@Entity
public class User {
	
	
	@Id
	@GeneratedValue
	Integer id;
	
	String firstName;
//	String middleName;
//	String lastName;
	Date dateOfBirth;
	
	@OneToMany(mappedBy="user")
	private List<Post> posts;
	
	public List<Post> getPosts() {
		return posts;
	}
	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
	public User() {
		// TODO Auto-generated constructor stub
	}
	public User(int i, String firstName, Date dateOfBirth) {
		super();
		this.id = i;
		this.firstName = firstName;
		this.dateOfBirth = dateOfBirth;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	
	/*
	 * String cur_Address1,cur_Address2,cur_Address3; String
	 * cur_taluk,cur_district,cur_state,cur_pincode; String
	 * perm_Address1,perm_Address2,perm_Address3; String
	 * perm_taluk,perm_district,perm_state,perm_pincode;
	 * 
	 * String idProofType; String idProofNumber;
	 * 
	 * String emailId;
	 * 
	 * String primaryMobileNo; String secondaryMobileNo;
	 */	

}
