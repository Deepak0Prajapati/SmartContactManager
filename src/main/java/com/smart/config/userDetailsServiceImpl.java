package com.smart.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.smart.dao.UserRepository;
import com.smart.entities.User;

public class userDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User userByUserName = repository.getUserByUserName(username);
		
		if(userByUserName==null) {
			throw new UsernameNotFoundException("could not found user!!");
		}
		
		ciustomUserDetails ciustomUserDetails=new ciustomUserDetails(userByUserName);

		
		return ciustomUserDetails;
	}

}
