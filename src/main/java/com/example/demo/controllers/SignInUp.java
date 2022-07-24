package com.example.demo.controllers;
import com.example.demo.models.QuotesModel;
import com.example.demo.reposatory.QuotesReposatory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class SignInUp {
	
	@Autowired
	QuotesReposatory quotesReposatory;
	
	@GetMapping("/")
	public String isServerWorking() {
//		QuotesModel quotesModel =  quotesReposatory.save(new QuotesModel("A", "D"));
//		System.out.println("Data:::::::::::::::::::::::: "+ quotesModel);
		return "Hello Sign_in_up ................";
	}

}
