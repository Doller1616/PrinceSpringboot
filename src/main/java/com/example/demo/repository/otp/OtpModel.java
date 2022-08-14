package com.example.demo.repository.otp;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import com.example.demo.repository.user.UserModel;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class OtpModel {

	 @SequenceGenerator(
	            name = "confirmation_token_sequence",
	            sequenceName = "confirmation_token_sequence",
	            allocationSize = 1
	    )
	    @Id
	    @GeneratedValue(
	            strategy = GenerationType.SEQUENCE,
	            generator = "confirmation_token_sequence"
	    )
	    private Long id;

	    @Column(nullable = false)
	    private String token;

	    @Column(nullable = false)
	    private LocalDateTime createdAt;

	    @Column(nullable = false)
	    private LocalDateTime expiresAt;

	    private LocalDateTime confirmedAt;

	    @ManyToOne
	    @JoinColumn( name = "user_id", nullable = false )
	    private UserModel userModel;

	    public OtpModel(String token,
	                    LocalDateTime createdAt,
	                    LocalDateTime expiresAt,
	                    UserModel userModel) {
	        this.token = token;
	        this.createdAt = createdAt;
	        this.expiresAt = expiresAt;
	        this.userModel = userModel;
	    }
	
	
}
