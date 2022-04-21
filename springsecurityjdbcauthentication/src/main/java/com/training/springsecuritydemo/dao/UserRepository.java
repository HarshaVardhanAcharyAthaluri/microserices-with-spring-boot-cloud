package com.training.springsecuritydemo.dao;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.training.springsecuritydemo.model.Users;


public interface UserRepository extends PagingAndSortingRepository<Users, Integer>{
	public Optional<Users> findByname(String name);
	
}
