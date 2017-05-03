package com.farvher.filemanager.services;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.farvher.filemanager.entity.User;
import com.farvher.filemanager.repository.RoleRepository;
import com.farvher.filemanager.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public void save(User user) {
		user.setPass(bCryptPasswordEncoder.encode(user.getPass()));
		user.setRoles(roleRepository.findAll());
		userRepository.save(user);

	}

	@Override
	public User findByUsername(String username) {
		return userRepository.findFirstByUserName(username);
	}

}
