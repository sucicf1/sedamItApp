package com.ivsucic.service;

import com.ivsucic.model.User;

public interface UserService {
	void save(User user);

	User findByUsername(String username);
}
