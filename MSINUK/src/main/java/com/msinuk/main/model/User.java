package com.msinuk.main.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name= "user_details")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String firstName;
	private String lastName;
	
	@Column(unique = true)
	private String userName;
	
	private String password;
	
	private Long lastVisited;
	
	private Long[] wishlist;
	
	public User() {
		
	}

	public User(Long id, String firstName, String lastName, String userName, String password, Long lastVisited,
			Long[] wishlist) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		this.lastVisited = lastVisited;
		this.wishlist = wishlist;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getLastVisited() {
		return lastVisited;
	}

	public void setLastVisited(Long lastVisited) {
		this.lastVisited = lastVisited;
	}

	public Long[] getWishlist() {
		return wishlist;
	}

	public void setWishlist(Long[] wishlist) {
		this.wishlist = wishlist;
	}
	
}
