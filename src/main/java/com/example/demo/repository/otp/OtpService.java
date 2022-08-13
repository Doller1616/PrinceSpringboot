package com.example.demo.repository.otp;

import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OtpService {

	private final OtpRepository otpRepository;

    public void saveConfirmationToken(OtpModel otpModel) {
    	otpRepository.save(otpModel);
    }

    public Optional<OtpModel> getToken(String otp) {
        return otpRepository.findByToken(otp);
    }

    public int setConfirmedAt(String otp) {
        return otpRepository.updateConfirmedAt(otp, LocalDateTime.now());
    }
}
