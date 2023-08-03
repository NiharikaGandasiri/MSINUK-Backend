package com.msinuk.main.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.msinuk.main.model.LoginResponse;
import com.msinuk.main.model.User;

import jakarta.transaction.Transactional;

@Repository
public interface UserRepo extends JpaRepository<User, Long>{

	User findByUserName(String username);
	
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query("UPDATE User u set u.lastVisited = ?1, u.wishlist = ?2 where u.id = ?3")
	void setUserInfoById(Long lastVisited, Long[] wishlist, Long userId);

}
