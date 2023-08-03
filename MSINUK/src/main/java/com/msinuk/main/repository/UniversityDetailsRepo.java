package com.msinuk.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.msinuk.main.model.UniversityDetails;
@Repository
public interface UniversityDetailsRepo extends JpaRepository<UniversityDetails, Long>{

	List<UniversityDetails> findAllByOrderByRatingDesc();
	
	
}
