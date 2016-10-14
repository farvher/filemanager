package com.farvher.filemanager.entity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

	Page<User> findAll(Pageable pageable);
	
	
}
