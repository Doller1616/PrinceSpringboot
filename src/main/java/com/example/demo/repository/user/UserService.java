package com.example.demo.repository.user;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.repository.otp.OtpModel;
import com.example.demo.repository.otp.OtpService;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final static String USER_NOT_FOUND_MSG =
            "user with email %s not found";

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final OtpService otpService;

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                String.format(USER_NOT_FOUND_MSG, email)));
    }
    
    
//Create User in DB -------------------------------------------------------------------------------------------------
    public String signUpUser(UserModel userModel) {
        boolean userExists = userRepository.findByEmail(userModel.getEmail())
                .isPresent();

        if (userExists) {
            // TODO check of attributes are the same and
            // TODO if email not confirmed send confirmation email.

            throw new IllegalStateException("email already taken");
        }

        String encodedPassword = bCryptPasswordEncoder
                .encode(userModel.getPassword());

        userModel.setPassword(encodedPassword);

        userRepository.save(userModel);
        
//--------- Generate OTP And Save it in to the Otp Repository -------------------------------------------
 
        String token = UUID.randomUUID().toString();

        OtpModel otpModel = new OtpModel(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                userModel
        );
        
        otpService.saveConfirmationToken(otpModel);

        return token;
    }

//-------------------------------------------------------------------------------------------------------------   
    
    public int enableAppUser(String email) {
        return userRepository.enableUser(email);
    }
}
