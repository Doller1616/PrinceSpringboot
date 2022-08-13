package com.example.demo.repository.otp;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional(readOnly = true)
public interface OtpRepository extends JpaRepository<OtpModel,Long> {

	
	 Optional<OtpModel> findByToken(String token);

	    @Transactional
	    @Modifying
	    @Query("UPDATE OtpModel c " + "SET c.confirmedAt = ?2 " + "WHERE c.token = ?1")
	    int updateConfirmedAt(String token, LocalDateTime confirmedAt);
}
