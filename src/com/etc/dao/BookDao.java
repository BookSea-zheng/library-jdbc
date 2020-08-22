package com.etc.dao;

import java.util.List;

import com.etc.entity.Book;
import com.etc.entity.User;

public interface BookDao {

	void bookAdd(Book book);
	
	void bookDel(String  name);
	
	void bookUpdate(Book book);
	
	List<Book> bookQuery();
	
	
	
}
