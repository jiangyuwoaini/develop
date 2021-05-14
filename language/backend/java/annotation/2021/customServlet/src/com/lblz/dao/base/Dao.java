package com.lblz.dao.base;

import java.util.List;

public interface Dao<T> {
	int save(T obj);
	
	int update(T obj);
	
	int delete(T obj);
	
	List<T> findAll();
}
