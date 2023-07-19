package com.student.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dbcon.DbCon;
import com.models.User;
import com.mysql.cj.util.StringUtils;

/**
 * Servlet implementation class StudentServlet
 */
@WebServlet("/StudentServlet")
public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final int limit = 4;

	public StudentServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		System.out.println(email);
		String password = request.getParameter("pass");
		System.out.println(password);

		DbCon db = new DbCon();
		Connection con;
		try {
			HttpSession session = request.getSession(false);
			System.out.println(session);
			try {
//				HttpSession session = request.getSession(false);
				String userEmail = (String) session.getAttribute("UserEmail");
				System.out.println("==================this is session email value: " + userEmail);
				boolean sessionEmail = Optional.ofNullable(userEmail).isEmpty();
				boolean sessionval = Optional.ofNullable(session).isEmpty();

			} catch (Exception e) {
				System.out.println("logout");
				PrintWriter out1 = response.getWriter();
				out1.append("<html><center><b>  Please Login First!! hahah </b></center><html>");
				RequestDispatcher rd = request.getRequestDispatcher("Index.html");
				rd.include(request, response);
			}

			;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("confirm session error");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		System.out.println(email);
		String password = request.getParameter("pass");
		System.out.println(password);
		String path = request.getContextPath();
		System.out.println("this is request path: "+path);

		DbCon db = new DbCon();

		try {
			Connection con = db.getConnection();
			PreparedStatement ps = con.prepareStatement("select * from user where email=?");
			ps.setString(1, email);
			ResultSet set = ps.executeQuery();

			if (set.next()) {
				String DbEmail = set.getString("email");
				String DbPass = set.getString("password");
				System.out.println("this is db email: " + DbEmail);
				System.out.println("this is db pass: " + DbPass);
				int idd = set.getInt("id");

				HttpSession session = request.getSession();
				session.setAttribute("UserEmail", email);
				session.setAttribute("UserName", set.getString(2));
				session.setAttribute("id", idd);
				session.setAttribute("isLoggedIn", "true");
				session.setAttribute("isLoggedOut", "false");
				session.setAttribute("action", "USER LIST");

				// creation time of session
				Date creationTime = new Date(session.getCreationTime());
				DateFormat dateFormat = new SimpleDateFormat("MM-dd hh:mm:ss");
				String createTime = dateFormat.format(creationTime);
				// last Access time of session
				Date lastAccessTime = new Date(session.getLastAccessedTime());
				String lastTime = dateFormat.format(lastAccessTime);
				System.out.println("==============================>");
				System.out.println(creationTime);
				System.out.println(lastAccessTime);

				HttpSession session2 = request.getSession(false);
				String userEmail = (String) session2.getAttribute("UserEmail");

				db.addActivity(idd, (String) session.getAttribute("isLoggedIn"), createTime,
						(String) session.getAttribute("action"), lastTime,
						(String) session.getAttribute("isLoggedOut"));
				response.sendRedirect("userlist.jsp?page=0");
				
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	boolean validateUser(String username, String password) {
		if (StringUtils.isNullOrEmpty(username) | StringUtils.isNullOrEmpty(password)) {
			return false;
		}
		return true;
	}


	public void getUrl(PrintWriter out) throws ClassNotFoundException, SQLException {
		int count = new DbCon().getCount();
		int pageNo = count / limit;
		System.out.println("Number of pages " + pageNo);
		for (int i = 0; i <= pageNo; i++) {
			out.append(
					"<div style=\"d-flex;flex-direction: row;display: inline-block;\"><a style=\"padding: 15px;color:black;\" href=/studentapp/StudentServlet?page="
							+ i + ">" + (i + 1) + "</a></div>");
		}
	}
}
