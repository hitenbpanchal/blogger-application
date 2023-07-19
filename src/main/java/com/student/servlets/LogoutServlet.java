package com.student.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dbcon.DbCon;

/**
 * Servlet implementation class LogoutServlet
 */
@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LogoutServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		HttpSession session = request.getSession(false);
		session.setAttribute("isLoggedOut", "true");
		session.setAttribute("isLoggedIn", "false");
		session.setAttribute("action", "Log Out");

		DbCon db = new DbCon();

		try {
			Connection con1 = db.getConnection();
			Integer id1 = (Integer) session.getAttribute("id");

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

			db.addActivity(id1, (String) session.getAttribute("isLoggedIn"), createTime,
					(String) session.getAttribute("action"), lastTime, (String) session.getAttribute("isLoggedOut"));

		} catch (Exception e) {
			// TODO: handle exception
		}

		session.invalidate();

		RequestDispatcher rd = request.getRequestDispatcher("Index.html");
		PrintWriter out = response.getWriter();
		out.append("<html><center><b>Logout user sucessfully</b></center><html>");
		rd.include(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
