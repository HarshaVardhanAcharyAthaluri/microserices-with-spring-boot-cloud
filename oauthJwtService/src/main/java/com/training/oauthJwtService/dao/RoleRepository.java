package com.training.oauthJwtService.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.training.oauthJwtService.model.Role;
import com.training.oauthJwtService.model.Roles;

public interface RoleRepository extends CrudRepository<Roles, Integer>{
	Optional<Roles> findByRole(Role role);

}
