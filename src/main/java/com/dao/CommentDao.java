package com.dao;

import java.sql.SQLException;
import java.util.List;

import com.models.Comment;

public interface CommentDao {
	
	public int AddComment(Comment com) throws Exception;
	
	public List<Comment> getAllComments() throws ClassNotFoundException, SQLException, Exception;
}
