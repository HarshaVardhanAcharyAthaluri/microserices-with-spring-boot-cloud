package com.training.oauthJwtService.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.training.oauthJwtService.model.Users;

public interface UserRepository extends CrudRepository<Users, Integer>{

	public Optional<Users> findByusername(String username);
}
