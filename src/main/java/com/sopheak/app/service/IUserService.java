package com.sopheak.app.service;

import com.sopheak.app.entities.User;

public interface IUserService {
	public User findUserByUsername(String username);
}
