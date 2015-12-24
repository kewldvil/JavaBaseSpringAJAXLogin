package com.sopheak.app.dao;

import com.sopheak.app.entities.User;

public interface IUserDao {
	public User findUserByUsername(String username);
}
