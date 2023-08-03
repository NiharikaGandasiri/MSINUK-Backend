package com.msinuk.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.msinuk.main.model.PartTimeJobs;

public interface PartTimeRepo extends JpaRepository<PartTimeJobs, Long> {

}
