package com.msinuk.main.model;

import java.util.Map;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name= "university_details")
public class UniversityDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "university_name")
	private String universityName;
	private String address;
	@ElementCollection
	@Column(length = 3000)
	private Map<String,String[]> courses;
	@Column(length = 10000)
	private String courseMap;
	private double rating;
	@Column(length=2000)
	private String description;
	@Column(length = 2000)
	private String[] images;
	private String contactDetails;
	@Column(length = 2000)
	private String reviews;
	
	
	public UniversityDetails() {
		
	}
	
	public UniversityDetails(long id, String universityName, String address, Map<String,String[]> courses, double rating,
			String description, String[] images, String contactDetails, String reviews, String courseMap) {
		this.id = id;
		this.universityName = universityName;
		this.address = address;
		this.courses = courses;
		this.rating = rating;
		this.description = description;
		this.images = images;
		this.contactDetails = contactDetails;
		this.reviews = reviews;
		this.courseMap = courseMap;
	}
	public String[] getImages() {
		return images;
	}
	public void setImages(String[] images) {
		this.images = images;
	}
	public String getContactDetails() {
		return contactDetails;
	}
	public void setContactDetails(String contactDetails) {
		this.contactDetails = contactDetails;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUniversityName() {
		return universityName;
	}
	public void setUniversityName(String universityName) {
		this.universityName = universityName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Map<String,String[]> getCourses() {
		return courses;
	}
	public void setCourses(Map<String,String[]> courses) {
		this.courses = courses;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public String getReviews() {
		return reviews;
	}
	public void setReviews(String reviews) {
		this.reviews = reviews;
	}

	public String getCourseMap() {
		return courseMap;
	}

	public void setCourseMap(String courseMap) {
		this.courseMap = courseMap;
	}
	

}
