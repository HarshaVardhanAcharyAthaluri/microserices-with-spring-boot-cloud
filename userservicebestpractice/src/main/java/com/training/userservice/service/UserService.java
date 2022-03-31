package com.training.userservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.training.userservice.exceptions.UserNotFoundException;
import com.training.userservice.model.Users;

@Service
public class UserService {

	
List<Users> userList = new ArrayList<Users>();
	
	public UserService() {
		Users user1 = new Users(1, "Vivek","Hyd","123456");
		Users user2 = new Users(2,"Anand","Bang","adcb");
		Users user3 = new Users(3,"Abhisikth","chennai","demo");
		userList.add(user1);
		userList.add(user2);
		userList.add(user3);
		
	}
	
	public List<Users> getUsers() {
		return userList;
	}
	
	public Users getUserById(int id) {
		return userList.stream()
				.filter(x-> x.getId() == id).findFirst()
				.orElseThrow(()->new UserNotFoundException("UserNot found "+ id));
	}
	
	public Users insertUser(Users user) {
		userList.add(user);
		
		return userList.stream()
				.filter(x-> x.getId() == user.getId()).findFirst()
				.orElseThrow(()->new RuntimeException("failed to add user"));
		
	}
	
	public Users updateUser(int id, Users user) {
		Users existingUser =  userList.stream()
						.filter(x-> x.getId() == id).findFirst()
						.orElseThrow(()->new UserNotFoundException("UserNot found "+ id));
		
		if(user.getName()!=null)
		existingUser.setName(user.getName());
		if(user.getAddr()!=null)
			existingUser.setAddr(user.getAddr());
		return existingUser;
	}
	
	public String deleteUser(int id) {
		Users existingUser =  userList.stream()
				.filter(x-> x.getId() == id).findFirst()
				.orElseThrow(()->new UserNotFoundException("UserNot found "+ id));
		
		boolean isremoved = userList.remove(existingUser);
		
		if(isremoved)
			return "User Removed Succfully";
		
		return "Unable to delete";
		
	}
}
