package com.farvher.filemanager.services;

import com.farvher.filemanager.entity.User;

public interface UserService {
	
	void save(User user);
	
	User findByUsername(String username);
}
