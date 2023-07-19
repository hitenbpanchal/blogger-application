package com.student.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.CommentDao;
import com.dbcon.DbCon;
import com.models.Comment;
import com.serviceimpl.CommentImpl;

/**
 * Servlet implementation class AddCommentServlet
 */
@WebServlet("/AddCommentServlet")
public class AddCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCommentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String comment = request.getParameter("comment");
		System.out.println("=================================>"+comment);
		HttpSession session = request.getSession(false);
		int UserId = (int) session.getAttribute("id");
		String username = (String) session.getAttribute("UserName");
		int blogPostId = Integer.parseInt(request.getParameter("blogPostId"));
		
		Comment com = new Comment(0, UserId, username, blogPostId, comment);
		
		CommentDao commentDao = new CommentImpl();
		try {
			commentDao.AddComment(com);
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.sendRedirect("viewAllBlog.jsp");
		

	}

}
