package com.training.userservice.controllers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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

@RestController
public class UserController {

	List<Users> userList = new ArrayList<Users>();
	
	public UserController() {
		Users user1 = new Users(1, "Vivek","Hyd");
		Users user2 = new Users(2,"Anand","Bang");
		Users user3 = new Users(3,"Abhisikth","chennai");
		userList.add(user1);
		userList.add(user2);
		userList.add(user3);
		
	}
	
	@RequestMapping("/hello")
	public String greet() {
		return "Hello Iam From UserService";
	}
	
	@GetMapping("/users")
	public List<Users> getUsers() {
		return userList;
	}
	
	
	@GetMapping("/user/{id}")
	public Users getUserById(@PathVariable int id) {
		return userList.stream()
				.filter(x-> x.getId() == id).findFirst()
				.orElseThrow(()->new RuntimeException("UserNot found "+ id));
	}
	
	
	@PostMapping("/adduser")
	public Users insertUser(@RequestBody Users user) {
		userList.add(user);
		
		return userList.stream()
				.filter(x-> x.getId() == user.getId()).findFirst()
				.orElseThrow(()->new RuntimeException("failed to add user"));
		
	}
	
	@PutMapping("/updateuser/{id}")
	public Users updateUser(@PathVariable int id,@RequestBody Users user) {
		
		Users existingUser =  userList.stream()
						.filter(x-> x.getId() == id).findFirst()
						.orElseThrow(()->new RuntimeException("UserNot found "+ id));
		
		if(user.getName()!=null)
		existingUser.setName(user.getName());
		if(user.getAddr()!=null)
			existingUser.setAddr(user.getAddr());
		
		return existingUser;
		
	}
	
	@DeleteMapping("/deleteuser/{id}")
	public String deleteUser(@PathVariable int id) {
		Users existingUser =  userList.stream()
				.filter(x-> x.getId() == id).findFirst()
				.orElseThrow(()->new RuntimeException("UserNot found "+ id));
		
		boolean isremoved = userList.remove(existingUser);
		
		if(isremoved)
			return "User Removed Succfully";
		
		return "Unable to delete";
		
	}
	
	
	
	
	
}
