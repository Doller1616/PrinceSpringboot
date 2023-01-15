package com.spring.crud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class DefaultController {

	@GetMapping("/")
	public String initialResponse() {
		return "<b> Server Started </b>";
	}
}
