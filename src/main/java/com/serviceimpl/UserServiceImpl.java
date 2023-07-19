package com.serviceimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dao.UserDao;
import com.dbcon.DbCon;
import com.models.User;

public class UserServiceImpl implements UserDao {

	@Override
	public int CreateUser(User user) throws ClassNotFoundException, SQLException {
		DbCon db = new DbCon();
		Connection con = db.getConnection();
		PreparedStatement ps = con.prepareStatement("insert into user values (?,?,?,?,?)");
		ps.setInt(1, user.getId());
		ps.setString(2, user.getName());
		ps.setString(3, user.getEmail());
		ps.setString(4, user.getPassword());
		ps.setString(5, user.getCity());		
		int update = ps.executeUpdate();
		return update;
	}

	@Override
	public List<User> getAllUsers(int pageNo,int limit) throws Exception, SQLException {

		ArrayList<User> list = new ArrayList<User>();
		DbCon db = new DbCon();
		Connection con = db.getConnection();
		PreparedStatement ps = con.prepareStatement("select * from user limit " + (pageNo * limit) + "," + limit);
		ResultSet set = ps.executeQuery();

		while (set.next()) {
			User user = new User(set.getInt(1), set.getString(2), set.getString(3),set.getString(4), set.getString(5));
			list.add(user);
		}
		return list;

	}

}
