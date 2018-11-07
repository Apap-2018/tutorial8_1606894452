package com.apap.tutorial6.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.apap.tutorial6.model.UserRoleModel;
import com.apap.tutorial6.repository.UserRoleDB;

@Service
public class UserRoleServiceImpl implements UserRoleService {
	@Autowired
	private UserRoleDB	userDb;
	
	@Override
	public UserRoleModel addUser(UserRoleModel user) {
		String pass = encrypt(user.getPassword());
		user.setPassword(pass);
		return userDb.save(user);
		
	}

	@Override
	public String encrypt(String password) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(password);
		return hashedPassword;
	}

	@Override
	public UserRoleModel findByUsername(String username) {
		UserRoleModel user = userDb.findByUsername(username);
		return user;
	}

	@Override
	public boolean decrypt(String oldInput, String oldGet) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		boolean hashedPassword = passwordEncoder.matches(oldInput, oldGet);
		return hashedPassword;
	}
	
}
