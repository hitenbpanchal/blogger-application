package com.student.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dbcon.DbCon;
import com.mysql.cj.Session;

/**
 * Servlet implementation class MyBlogServlet
 */
@WebServlet("/MyBlogServlet")
public class MyBlogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyBlogServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		Integer id = (Integer) session.getAttribute("id");
		int page = (int) session.getAttribute("ctPage");
		System.out.println("This is Integer id of myBlog page: "+id);
		
		DbCon db = new DbCon();
		
		try {
			Connection con = db.getConnection();
			PreparedStatement ps = con.prepareStatement("select * from blogpost where userId=?");
			ps.setInt(1, id);
			ResultSet set = ps.executeQuery();
			
			PrintWriter out = response.getWriter();
			out.append("<!doctype html>\r\n"
					+ "<html lang=\"en\">\r\n"
					+ "  <head>\r\n"
					+ "    <!-- Required meta tags -->\r\n"
					+ "    <meta charset=\"utf-8\">\r\n"
					+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">\r\n"
					+ "\r\n"
					+ "    <!-- Bootstrap CSS -->\r\n"
					+ "    <link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css\" integrity=\"sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T\" crossorigin=\"anonymous\">\r\n"
					+ "	<style>\r\n"
					+ "	.scroll {\r\n"
					+ "	</style>\r\n"
					+ "    <title>Blogs</title>\r\n"
					+ "  </head>\r\n"
					+ "  <body>\r\n"
					+ "\r\n"
					//This is Navbar
					+"	<nav class=\"navbar navbar-expand-lg navbar-light bg-light\">\r\n"
					+ "  <a class=\"navbar-brand\" href=\"#\">Navbar</a>\r\n"
					+ "  <button class=\"navbar-toggler\" type=\"button\" data-toggle=\"collapse\" data-target=\"#navbarSupportedContent\" aria-controls=\"navbarSupportedContent\" aria-expanded=\"false\" aria-label=\"Toggle navigation\">\r\n"
					+ "    <span class=\"navbar-toggler-icon\"></span>\r\n"
					+ "  </button>\r\n"
					+ "\r\n"
					+ "  <div class=\"collapse navbar-collapse\" id=\"navbarSupportedContent\">\r\n"
					+ "    <ul class=\"navbar-nav mr-auto\">\r\n"
					+ "      <li class=\"nav-item active\">\r\n"
					+ "        <a class=\"nav-link\" href=userlist.jsp?page="+page+"> Home <span class=\"sr-only\">(current)</span></a>\r\n"
					+ "	    <li class=\"nav-item\">\r\n"
					+ "        <a class=\"nav-link\" href=\"viewBlogServlet\">View All Blogs</a>\r\n"
					+ "      </li>"
					+ "      <li class=\"nav-item\">\r\n"
					+ "        <a class=\"nav-link\" href=addBlog.jsp?id="+session.getAttribute("id")+"&name="+session.getAttribute("UserName")+">Add New Blogs</a>\r\n"
					+ "      </li>\r\n"
					+ "	    <li class=\"nav-item\">\r\n"
					+ "        <a class=\"nav-link\" href=\"LogoutServlet\">Log Out</a>\r\n"
					+ "      </li>\r\n"
					+ "    </ul>\r\n"
					+ "    <form class=\"form-inline my-2 my-lg-0\">\r\n"
					+ "      <input class=\"form-control mr-sm-2\" type=\"search\" placeholder=\"Search\" aria-label=\"Search\">\r\n"
					+ "      <button class=\"btn btn-outline-success my-2 my-sm-0\" type=\"submit\">Search</button>\r\n"
					+ "    </form>\r\n"
					+ "  </div>\r\n"
					+ "</nav>"
					//This is Blog Posts
					+"<div class=\"content\">"
					+ "<h1> Your Blogs  </h1>");
					while(set.next()) {
						out.append("<div class=\"card border-dark mb-3 scroll\" style=\"max-width: 18rem;\">\r\n"
								+ "  <div class=\"card-header\">"+set.getString(3)+"</div>\r\n"
								+ "  <div class=\"card-body text-dark\">\r\n"
								+ "  <h5 class=\"card-title\">"+set.getString(4)+"</h5>"
								+ "    <p class=\"card-text\"> "+set.getString(5)+" </p>\r\n"
								+ "  </div>\r\n"
								+ "</div>"
								+ "</div>");
					}
					out.append("    <script src=\"https://code.jquery.com/jquery-3.3.1.slim.min.js\" integrity=\"sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo\" crossorigin=\"anonymous\"></script>\r\n"
							+ "    <script src=\"https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js\" integrity=\"sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1\" crossorigin=\"anonymous\"></script>\r\n"
							+ "    <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js\" integrity=\"sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM\" crossorigin=\"anonymous\"></script>\r\n"
							+ "  </body>\r\n"
							+ "</html>");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
