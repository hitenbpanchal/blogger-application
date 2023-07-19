package com.dbcon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.models.Comment;
import com.models.User;
import com.models.ViewBlog;

public class DbCon {

	public Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/new_db", "root", "Hiten@123");
		return con;
	}

	public void CloseConnection(Connection con) throws SQLException {
		con.close();
	}

	
	public List<User> getSearchUsers(String pattern) throws Exception, SQLException {

		ArrayList<User> list = new ArrayList<User>();
		String query = "SELECT * FROM user WHERE name LIKE '" + pattern +"%'";
		System.out.println("query ==> "+query);
		Connection con = getConnection();
		
		PreparedStatement ps = con.prepareStatement(query);
		ResultSet set = ps.executeQuery();

		while (set.next()) {
			User user = new User(set.getInt(1), set.getString(2), set.getString(3),set.getString(4), set.getString(5));
			System.out.println("value ="+set.getString(2));
			list.add(user);
		}
		return list;

	}
	
	public void addActivity(int id, String isLoggedin, String createTime, String action, String lastTime,
			String isLoggedOut) throws Exception, SQLException {
		Connection con = getConnection();
		PreparedStatement ps = con.prepareStatement("insert into activity_table values(?,?,?,?,?,?)");
		ps.setInt(1, id);
		ps.setString(2, isLoggedin);
		ps.setString(3, createTime);
		ps.setString(4, action);
		ps.setString(5, lastTime);
		ps.setString(6, isLoggedOut);

		int update = ps.executeUpdate();
		System.out.println("Activity table Query: " + update);
	}
	
	public int getCount() throws ClassNotFoundException, SQLException {
		Connection con = getConnection();
		PreparedStatement ps = con.prepareStatement("select count(*) from user");
		ResultSet set = ps.executeQuery();
		int count = 0;
		if(set.next()) {
			count = set.getInt(1);
		}
		return count;
	}

}
