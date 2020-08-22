package com.etc.dao;

import java.util.List;

import com.etc.entity.User;

public interface UserDao {

	void userAdd(User user);
	
	void userDel(String  name);
	
	void userUpdate(User user);
	
	List<User> userQuery();
	
	
	
}
