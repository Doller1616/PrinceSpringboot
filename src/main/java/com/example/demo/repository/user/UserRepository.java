package com.example.demo.repository.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


	@Repository
	@Transactional(readOnly = true)
	public interface UserRepository extends JpaRepository<UserModel, Long> {

	    Optional<UserModel> findByEmail(String email);

	    @Transactional
	    @Modifying
	    @Query("UPDATE UserModel a " + "SET a.enabled = TRUE WHERE a.email = ?1")
	    int enableUser(String email);

	}

