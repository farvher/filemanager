package com.farvher.filemanager.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.farvher.filemanager.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	
	User findFirstByUserName(String userName);

}
