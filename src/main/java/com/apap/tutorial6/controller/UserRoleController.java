package com.apap.tutorial6.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.apap.tutorial6.model.UserRoleModel;
import com.apap.tutorial6.service.UserRoleService;

@Controller
@RequestMapping("/user")
public class UserRoleController {
	@Autowired
	private UserRoleService userService;
	
	@RequestMapping(value="/addUser", method=RequestMethod.POST)
	private String addUserSubmit(@ModelAttribute UserRoleModel user, HttpServletRequest request) {
		String password = request.getParameter("password");
		boolean flag = false;
//		System.out.println(password.length());
		for(int i = 0 ;  i < password.length() ;i++) {
			char x = password.charAt(i);
			System.out.println(x);
			if(Character.isDigit(x)) {
				flag = true;
				System.out.println(i);
			}
		}
		if(password.length() >= 8 && flag == true ) {
			userService.addUser(user);
			return "home";
		}
		return "failPassword";
	}
	
	@RequestMapping(value="/updatePassword", method =RequestMethod.GET)
	private String updatePassword() {
		return "ubahPassword";
	}
	
	@RequestMapping(value="/updatePassword", method =RequestMethod.POST)
	private String updatePasswordSubmit(HttpServletRequest request) {
		String passwordLama = (request.getParameter("passwordLama"));
		String passwordBaru1 = (request.getParameter("passwordBaru1"));
		String passwordBaru2 = (request.getParameter("passwordBaru2"));
		UserRoleModel user = userService.findByUsername(request.getRemoteUser());
		boolean cek = userService.decrypt(passwordLama, user.getPassword());
		boolean flag1 = false;
		for(int i = 0 ;  i < passwordBaru1.length() ;i++) {
			if(Character.isDigit(passwordBaru1.charAt(i))) {
				flag1 = true;
			}
		}
		boolean flag2 = false;
		for(int i = 0 ;  i < passwordBaru2.length() ;i++) {
			if(Character.isDigit(passwordBaru2.charAt(i))) {
				flag2 = true;
			}
		}
		if(passwordBaru1.length() >= 8 && passwordBaru2.length() == 8 && flag1 == true && flag2 == true ) {
			if(passwordBaru1.equals(passwordBaru2)) {
//				System.out.println("test1");
				if (cek == true) {
//					System.out.println("test2");
					String newPass = userService.encrypt(passwordBaru1);
					user.setPassword(newPass);
					return "home";
				}
			}
		}
		
		if(passwordBaru1.equals(passwordBaru2)) {
//			System.out.println("test1");
			if (cek == true) {
//				System.out.println("test2");
				String newPass = userService.encrypt(passwordBaru1);
				user.setPassword(newPass);
				return "home";
			}
		}
//		System.out.println("test3");
		return "failPassword";
	}
	
}
