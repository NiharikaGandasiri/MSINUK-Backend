package com.msinuk.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.msinuk.main.model.PartTimeJobs;
import com.msinuk.main.service.PartTimeService;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/api/v1/")
public class PartTimeController {
	
	@Autowired
	private PartTimeService service;
	
	@GetMapping("/getJobs")
	public List<PartTimeJobs> getJobs(){
		return this.service.getJobs();
	}
	
	@PostMapping("/addJob")
	public PartTimeJobs addJob(PartTimeJobs job) {
		return this.service.save(job);
	}
}
