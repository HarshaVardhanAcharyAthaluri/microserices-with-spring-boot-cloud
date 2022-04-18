package com.training.userservice.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.training.userservice.dto.OrderDto;
import com.training.userservice.dto.UserDto;
import com.training.userservice.model.Users;
import com.training.userservice.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	UserService service;
	
	@Autowired
	RestTemplate rt;
	
	@Value("${orderservice.adress}")
	String orderserviceaddr; 
	
	
	@HystrixCommand(fallbackMethod = "myFaulttolerant")
	@GetMapping(value = "/hello")
	public String greet() {
		return rt.getForObject(orderserviceaddr+"/greet", String.class);
	}
	
	
	public String myFaulttolerant() {
		return "Fallback Happend Please visit after sometime";
	}
	
	
	
	
	@HystrixCommand(fallbackMethod = "myFaulttolerant")
	@GetMapping("/userorder/{userId}")
	public UserDto getOrdeDetails(@PathVariable int userId) {
		UserDto usrdto = new UserDto();
		Users user = service.getUserById(userId);
		usrdto.setId(user.getId());
		usrdto.setName(user.getName());
		usrdto.setAddr(user.getAddr());

		usrdto.setOrders(rt.getForObject(orderserviceaddr+"/order/"+userId,List.class));
		
		return usrdto;
	}
	
	@GetMapping("/userpage/{pageSize}/{pageNo}")
	public ResponseEntity<List<Users>> getUsersByPage(@PathVariable int pageNo,@PathVariable int pageSize) {
		return new ResponseEntity<List<Users>>(service.getUsersByPage(pageNo, pageSize),HttpStatus.OK);
	}
	
	@GetMapping("/userbysort/{prop}")
	public ResponseEntity<List<Users>> getUsersBySort(@PathVariable String prop) {
		return new ResponseEntity<List<Users>>(service.getUsersBySort(prop),HttpStatus.OK);
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
	
	@GetMapping("/userbyname/{name}")
	public ResponseEntity<Users> getUserByName(@PathVariable String name) {
		return new ResponseEntity<Users>(service.getUserName(name), HttpStatus.OK);
	}
	
	@GetMapping("/usersbyaddr/{addr}")
	public ResponseEntity<List<Users>> getUserByaddr(@PathVariable String addr) {
		return new ResponseEntity<List<Users>>(service.getUserByAddr(addr), HttpStatus.OK);
	}
	
	
	@GetMapping("/usersgrater/{range}")
	public ResponseEntity<List<Users>> getUsersGrater(@PathVariable int range){
		return new ResponseEntity<List<Users>>(service.getUsersGrater(range),HttpStatus.OK);
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
