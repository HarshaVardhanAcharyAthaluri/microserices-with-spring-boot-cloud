package com.training.oauthJwtService.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.training.oauthJwtService.dao.UserRepository;
import com.training.oauthJwtService.model.Users;
@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	UserRepository repo;
	
	
	@Transactional
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user =  repo.findByusername(username)
				.orElseThrow(()-> new UsernameNotFoundException("Username Not fouond "+ username));
		return UserDeatailsImpl.build(user);
	}

	
}
