package com.wade.demo.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wade.demo.domain.entity.User;
import com.wade.demo.service.UserService;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("")
	public List<User> listUsers() {
		return userService.listUsers();
	}
	
	@GetMapping("/{id:\\d+}")
	public User getById(@PathVariable("id") long id) {
		return userService.getById(id);
	}
}
