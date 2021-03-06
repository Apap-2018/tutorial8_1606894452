package com.apap.tutorial6.service;

import com.apap.tutorial6.model.UserRoleModel;

public interface UserRoleService {
	UserRoleModel addUser(UserRoleModel user);
	public String encrypt(String password);
	public boolean decrypt(String oldInput, String oldGet);
	UserRoleModel findByUsername(String username);
}
