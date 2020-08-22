package com.etc.dao;

import java.util.List;

import com.etc.entity.Book;
import com.etc.entity.Record;
import com.etc.entity.User;

public interface RecordDao {

	void recordAdd(Record record);
	
	void recordDel(String  name);
	
	void recordUpdate(Record record);
	
	List<Record> recordQuery();
	
	
	
}
