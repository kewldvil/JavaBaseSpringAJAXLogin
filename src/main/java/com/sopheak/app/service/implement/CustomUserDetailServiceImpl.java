package com.sopheak.app.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sopheak.app.dao.IUserDao;
import com.sopheak.app.entities.User;
import com.sopheak.app.service.IUserService;



@Service
public class CustomUserDetailServiceImpl  implements UserDetailsService{

	@Autowired
	IUserDao userserviceimpl;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userserviceimpl.findUserByUsername(username);
		if (user == null) {
			System.out.println("User not found");
			throw new UsernameNotFoundException("User not found");
		}
		return user;
	}

}
