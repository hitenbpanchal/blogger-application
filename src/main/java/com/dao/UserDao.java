package com.dao;

import java.sql.SQLException;
import java.util.List;

import com.models.User;

public interface UserDao {
	
	public int CreateUser(User user) throws ClassNotFoundException, SQLException;
	
	public List<User> getAllUsers(int pageNo,int limit) throws Exception, SQLException;

}
