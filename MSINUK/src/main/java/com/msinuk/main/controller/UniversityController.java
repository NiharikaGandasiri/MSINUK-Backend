package com.msinuk.main.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.msinuk.main.model.UniversityDetails;
import com.msinuk.main.service.UniversityService;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/api/v1/")
public class UniversityController {
	
	@Autowired
	private UniversityService service;
	
	@GetMapping("/Universities")
	public List<UniversityDetails> getUniversities(){
		return this.service.getUniversities();
	}
	@GetMapping("/addUniversities")
	public List<UniversityDetails> addUniversities() throws JsonMappingException, JsonProcessingException{
		return this.service.addUniversities();
	}
	
	@GetMapping("/getUniversities")
	public List<UniversityDetails> getUniversitiesByName(@RequestParam("uname") String universityName, @RequestParam("cname") String courseName,
			@RequestParam("department") String department){
		return this.service.getUniversitiesByName(universityName,courseName,department);
	}
	@GetMapping("/getUniversity")
	public Optional<UniversityDetails> getUniversityByName(@RequestParam("id") String unid){
		return this.service.getUniversityById(unid);
	}
	@GetMapping("/getWishList")
	public List<UniversityDetails> getWishList(@RequestParam("ids") String unids){
		return this.service.getWishList(unids);
	}

}
