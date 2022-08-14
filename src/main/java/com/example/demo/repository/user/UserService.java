package com.example.demo.repository.user;

import lombok.AllArgsConstructor;

import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final static String USER_NOT_FOUND_MSG =
            "user with email %s not found";

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                String.format(USER_NOT_FOUND_MSG, email)));
    }
    
    
//Create User in DB -------------------------------------------------------------------------------------------------

    public UserModel signUpUser(UserModel userModel) {
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

        UserModel result =  userRepository.save(userModel);
        return result;
    }

//-------------------------------------------------------------------------------------------------------------   
    
    public int enableAppUser(String email) {
        return userRepository.enableUser(email);
    }
}
