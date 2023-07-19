package com.dao;

import java.sql.SQLException;
import java.util.List;

import com.models.ViewBlog;

public interface BlogDao {
	
	public int AddBlog(ViewBlog blog) throws ClassNotFoundException, SQLException;
	
	public List<ViewBlog> getAllBlogs() throws Exception, SQLException;
	
	public List<ViewBlog> getMyBlogs(int id) throws Exception, SQLException;
}
