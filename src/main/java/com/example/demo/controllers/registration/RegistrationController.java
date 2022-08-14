package com.example.demo.controllers.registration;

import lombok.AllArgsConstructor;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.*;

import com.example.demo.repository.otp.OtpModel;
import com.example.demo.repository.user.UserModel;

@RestController
@RequestMapping(path = "api/v1/registration")
@AllArgsConstructor
public class RegistrationController {

   private final RegistrationService registrationService;

    @PostMapping
    public OtpModel register(@Valid @RequestBody UserModel userModel) {
        return registrationService.register(userModel);
    }

    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token) {
        return registrationService.confirmToken(token);
    }

}
