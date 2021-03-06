package com.oauthdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.oauthdemo.entity.UserEntity;
import com.oauthdemo.repository.UserRepository;

@Service(value = "userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity userEntity = userRepository.findByUsername(username);
		
		if(userEntity == null) {
			throw new BadCredentialsException("Bad Credentials");
		}
		
		new AccountStatusUserDetailsChecker().check(userEntity);
		
		return userEntity;
	}

}
