package com.wade.demo.service;

import java.util.List;

import com.wade.demo.domain.entity.User;

public interface UserService {

	List<User> listUsers();
	
	User getById(long id);
}
