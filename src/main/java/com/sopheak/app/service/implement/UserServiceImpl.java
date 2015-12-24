package com.sopheak.app.service.implement;

import org.springframework.beans.factory.annotation.Autowired;

import com.sopheak.app.dao.IUserDao;
import com.sopheak.app.entities.User;
import com.sopheak.app.service.IUserService;

public class UserServiceImpl implements IUserService {
	
	@Autowired
	IUserDao userimpl;
	
	
	@Override
	public User findUserByUsername(String username) {
		return userimpl.findUserByUsername(username);
	}

}
