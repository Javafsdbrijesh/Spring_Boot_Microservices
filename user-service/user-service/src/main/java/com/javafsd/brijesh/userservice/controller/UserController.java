package com.javafsd.brijesh.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javafsd.brijesh.userservice.entity.ResponseTemplateView;
import com.javafsd.brijesh.userservice.entity.User;
import com.javafsd.brijesh.userservice.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/")
	public User saveUser(@RequestBody User user){		
		return userService.saveUser(user);
		
	}
	
	@GetMapping("/{id}")
	public ResponseTemplateView getUserWithDepartmentId(@PathVariable("id") Long userId) {
		
		return userService.getUserWithDepartmentId(userId);
		
	}
}
