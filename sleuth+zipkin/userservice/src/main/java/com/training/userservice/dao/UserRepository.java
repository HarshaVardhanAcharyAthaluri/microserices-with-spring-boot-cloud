package com.training.userservice.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.training.userservice.model.Users;

public interface UserRepository extends PagingAndSortingRepository<Users, Integer>{

	public Optional<Users> findByname(String name);
	
	public List<Users> getByaddr(String name);
	
	@Query(value="select * from users where id >= :range",nativeQuery = true)
	public List<Users> getUsersGreaterThan3(@Param("range") int range);
	
}
