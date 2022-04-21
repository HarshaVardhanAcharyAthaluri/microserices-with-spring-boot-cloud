package com.training.springsecuritydemo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.training.springsecuritydemo.dao.UserRepository;
import com.training.springsecuritydemo.model.Users;

@RestController
public class UserController {

	@Autowired
	UserRepository repo;

	@Autowired
	PasswordEncoder encoder;
	
	@PostMapping("/signup")
	public Users saveUser(@RequestBody Users u) {
		u.setPassword(encoder.encode(u.getPassword()));
		return repo.save(u);
	}
}
