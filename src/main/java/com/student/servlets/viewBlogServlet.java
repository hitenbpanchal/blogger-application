package com.student.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.dao.BlogDao;
import com.dbcon.DbCon;
import com.models.ViewBlog;
import com.serviceimpl.BlogImpl;

/**
 * Servlet implementation class viewBlogServlet
 */
@WebServlet("/viewBlogServlet")
public class viewBlogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public viewBlogServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int UserId = Integer.parseInt(request.getParameter("id"));
		System.out.println("This is ViewBlog Servlet idddd: "+UserId);
		String name = request.getParameter("name");
		String content = request.getParameter("content");
		String title = request.getParameter("title");
//		 Part part = request.getPart("image");
//		 String fileName = part.getSubmittedFileName();
		String image = request.getParameter("image");
		System.out.println("This is image file=============================>>>"+image);
		System.out.println(name);
		System.out.println(content);
		
		ViewBlog blog = new ViewBlog(0, UserId, name, title, content);
		
		BlogDao blogDao = new BlogImpl();
		
		try {
			blogDao.AddBlog(blog);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		response.sendRedirect("viewAllBlog.jsp");	
	}

}
