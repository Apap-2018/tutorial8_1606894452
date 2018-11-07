package com.apap.tutorial6.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.apap.tutorial6.model.UserRoleModel;
import com.apap.tutorial6.service.UserRoleService;

@Controller
public class PageController {
	
	
	
	@RequestMapping("/")
	public String home() {
			return "home";
	}
	
	@RequestMapping("/login")
	public String login() {
			return "login";
	}


	
}
