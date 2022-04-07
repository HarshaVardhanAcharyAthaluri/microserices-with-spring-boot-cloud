package com.training.userservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.training.userservice.dao.UserRepository;
import com.training.userservice.exceptions.UserNotFoundException;
import com.training.userservice.model.Users;

@Service
public class UserService {

	@Autowired
	UserRepository repo;
	
	
	public List<Users> getUsersByPage(int pageNo,int pageSize){
		 Pageable paging = PageRequest.of(pageNo, pageSize);
		 Page<Users> page = repo.findAll(paging);
		return  page.toList();
	}
	
	
	public List<Users> getUsersBySort(String prop){
		return  (List<Users>) repo.findAll(Sort.by(prop));
	}
	
	
	
	public List<Users> getUsers() {
		return (List<Users>) repo.findAll();
	}
	
	public Users getUserById(int id) {
		return repo.findById(id).orElseThrow(()->new UserNotFoundException("UserNot found "+ id));
	}
	public Users getUserName(String name) {
		return repo.findByname(name).orElseThrow(()->new UserNotFoundException("UserNot found "+ name));
	}
	
	public List<Users> getUserByAddr(String addr) {
		return repo.getByaddr(addr);
	}
	
	public Users insertUser(Users user) {
		return repo.save(user);
	}
	
	
	public List<Users> getUsersGrater(int range){
		return repo.getUsersGreaterThan3(range);
	}
	
	
	public Users updateUser(int id, Users user) {
		Users existingUser =  repo.findById(id).orElseThrow(()->new UserNotFoundException("UserNot found "+ id));
		
		if(user.getName()!=null)
		existingUser.setName(user.getName());
		if(user.getAddr()!=null)
			existingUser.setAddr(user.getAddr());
		repo.save(existingUser);
		return existingUser;
	}
	
	public String deleteUser(int id) {
		repo.deleteById(id);
		return "Deleted Succfully";
		
	}
}
