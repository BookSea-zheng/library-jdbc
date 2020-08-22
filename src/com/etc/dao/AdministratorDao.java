package com.etc.dao;

import java.util.List;

import com.etc.entity.Administrator;

import com.etc.entity.User;

public interface AdministratorDao {

	void administratorAdd(Administrator administrator);
	
	void administratorDel(int  id);
	
	void administratorUpdate(Administrator administrator);
	
	List<Administrator> administratorQuery();
	
	
	
}

