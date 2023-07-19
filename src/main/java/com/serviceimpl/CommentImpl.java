package com.serviceimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dao.CommentDao;
import com.dbcon.DbCon;
import com.models.Comment;

public class CommentImpl implements CommentDao {

	@Override
	public int AddComment(Comment com) throws Exception {
			DbCon db = new DbCon();
			Connection con = db.getConnection();
			PreparedStatement ps = con.prepareStatement("insert into blogpost_comments values (?,?,?,?,?)");
			ps.setInt(1, 0);
			ps.setInt(2, com.getUserId());
			ps.setString(3, com.getUserName());
			ps.setInt(4, com.getBlogPostId());
			ps.setString(5, com.getComments());
			
			int update = ps.executeUpdate();
			
			return update;
}

	@Override
	public List<Comment> getAllComments() throws Exception, SQLException {

		ArrayList<Comment> list = new ArrayList<Comment>();
		DbCon db = new DbCon();
		Connection con = db.getConnection();
		PreparedStatement ps = con.prepareStatement("select * from blogpost_comments order by id desc");
		ResultSet set = ps.executeQuery();

		while (set.next()) {
			Comment com = new Comment(set.getInt(1), set.getInt(2), set.getString(3), set.getInt(4), set.getString(5));
			list.add(com);
		}
		return list;

	}
	
}
