package com.msinuk.main.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name= "partTime_Jobs")
public class PartTimeJobs {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String jobName;
	@Column(length = 10000)
	private String jobDescription;
	private String jobImage;
	private String salary;
	private String location;
	private String address;
	private String shiftTimings;
	private String contactDetails;
	@Column(length = 10000)
	private String requirements;
	
	
	public PartTimeJobs() {
		
	}
	
	public PartTimeJobs(long id, String jobName, String jobDescription, String jobImage, String salary, String location,
			String address, String contactDetails, String shiftTimings, String requirements) {
		this.id = id;
		this.jobName = jobName;
		this.jobDescription = jobDescription;
		this.jobImage = jobImage;
		this.salary = salary;
		this.location = location;
		this.address = address;
		this.contactDetails = contactDetails;
		this.shiftTimings = shiftTimings;
		this.requirements = requirements;
		
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public String getJobDescription() {
		return jobDescription;
	}
	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}
	public String getJobImage() {
		return jobImage;
	}
	public void setJobImage(String jobImage) {
		this.jobImage = jobImage;
	}
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getContactDetails() {
		return contactDetails;
	}
	public void setContactDetails(String contactDetails) {
		this.contactDetails = contactDetails;
	}

	public String getShiftTimings() {
		return shiftTimings;
	}

	public void setShiftTimings(String shiftTimings) {
		this.shiftTimings = shiftTimings;
	}

	public String getRequirements() {
		return requirements;
	}

	public void setRequirements(String requirements) {
		this.requirements = requirements;
	}
	

}
