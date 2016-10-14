package com.farvher.filemanager.entity;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import com.farvher.filemanager.entity.User;

public interface UserDAO extends CrudRepository<User,Integer> {
	
	Page<User> findAll(Pageable pageable);
	
	List<User> findByPass(String pass);
	
	

}
