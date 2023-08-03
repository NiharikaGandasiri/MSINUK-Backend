package com.msinuk.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.msinuk.main.model.PartTimeJobs;
import com.msinuk.main.repository.PartTimeRepo;

@Service
public class PartTimeService {
	
	@Autowired
	private PartTimeRepo repo;
	
	public List<PartTimeJobs> getJobs() {
		return this.repo.findAll();
	}

	public PartTimeJobs save(PartTimeJobs job) {
		return this.repo.save(job);
	}
}
