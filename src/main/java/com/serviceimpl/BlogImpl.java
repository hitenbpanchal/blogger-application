package com.serviceimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dao.BlogDao;
import com.dbcon.DbCon;
import com.models.ViewBlog;

public class BlogImpl implements BlogDao {

	@Override
	public int AddBlog(ViewBlog blog) throws ClassNotFoundException, SQLException {
		DbCon db = new DbCon();
		Connection con = db.getConnection();
		PreparedStatement ps = con.prepareStatement("insert into blogpost values (?,?,?,?,?)");
		ps.setInt(1, blog.getId());
		ps.setInt(2, blog.getUserId());
		ps.setString(3, blog.getName());
		ps.setString(4, blog.getTitle());
		ps.setString(5, blog.getcontent());
		
		int query = ps.executeUpdate();
		
		return query;
	}

	@Override
	public List<ViewBlog> getAllBlogs()  {

		ArrayList<ViewBlog> list = new ArrayList<ViewBlog>();
		
		DbCon db = new DbCon();
		Connection con;
		try {
			con = db.getConnection();
			PreparedStatement ps = con.prepareStatement("select * from blogpost order by id desc");
			ResultSet set = ps.executeQuery();
			while (set.next()) {
				ViewBlog blogs = new ViewBlog(set.getInt(1), set.getInt(2), set.getString(3), set.getString(4), set.getString(5));
				list.add(blogs);
			}
			return list;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<ViewBlog> getMyBlogs(int id) throws Exception, SQLException {

		ArrayList<ViewBlog> list = new ArrayList<ViewBlog>();
		
		DbCon db = new DbCon();
		Connection con = db.getConnection();
		PreparedStatement ps = con.prepareStatement("select * from blogpost where userId=?");
		ps.setInt(1, id);
		ResultSet set = ps.executeQuery();

		while (set.next()) {
			ViewBlog blogs = new ViewBlog(set.getInt(1), set.getInt(2), set.getString(3), set.getString(4), set.getString(5));
			list.add(blogs);
		}
		return list;

	}

}
