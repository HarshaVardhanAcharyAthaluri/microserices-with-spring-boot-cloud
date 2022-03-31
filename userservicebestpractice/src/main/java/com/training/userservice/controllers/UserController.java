package com.training.userservice.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.training.userservice.model.Users;
import com.training.userservice.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	UserService service;
	
	@RequestMapping("/hello")
	public String greet() {
		return "Hello Iam From UserService";
	}
	
	@GetMapping("/users")
	public ResponseEntity<List<Users>> getUsers() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("isloged", "succfully");
		return new ResponseEntity<List<Users>>(service.getUsers(), headers,HttpStatus.OK);
	}
	
	@GetMapping("/user/{id}")
	public ResponseEntity<Users> getUserById(@PathVariable int id) {
		return new ResponseEntity<Users>(service.getUserById(id), HttpStatus.OK);
	}
	
	@PostMapping("/user/adduser")
	public  ResponseEntity<Users> insertUser(@RequestBody Users user) {
		return new ResponseEntity<Users>(service.insertUser(user),HttpStatus.CREATED);
	}
	
	@PutMapping("/user/updateuser/{id}")
	public ResponseEntity<Users> updateUser(@PathVariable int id,@RequestBody Users user) {
		return new ResponseEntity<Users>(service.updateUser(id, user),HttpStatus.CREATED);
	}
	
	@DeleteMapping("/user/deleteuser/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable int id) {
		return new ResponseEntity<String>(service.deleteUser(id),HttpStatus.ACCEPTED);
	}
}
