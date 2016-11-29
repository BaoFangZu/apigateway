package com.exposure.gateway.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.exposure.common.SessionUser;
import com.exposure.common.UserRepository;

@Service
public class IamUserDetailsService implements UserDetailsService  {
	@Autowired
	UserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		SessionUser user = userRepo.findByUsername(username).get(0);
		return user;
	}
}
