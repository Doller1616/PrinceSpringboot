package com.example.demo.controllers;
import com.example.demo.models.QuotesModel;
import com.example.demo.reposatory.QuotesReposatory;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("employee")
public class Employee {
	
	@Autowired
	QuotesReposatory quotesReposatory;
	
	@PostMapping("/add")
	public ResponseEntity<String> isServerWorking(@Valid @RequestBody QuotesModel user) {
		QuotesModel quotesModel =  quotesReposatory.save(user);
    	
		return ResponseEntity.ok("User data is valid"+ quotesModel.toString());
	}

}
