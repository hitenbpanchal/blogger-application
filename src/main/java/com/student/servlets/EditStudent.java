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
import java.util.Optional;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dbcon.DbCon;
import com.mysql.cj.Session;

/**
 * Servlet implementation class EditStudent
 */
@WebServlet("/edit")
public class EditStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditStudent() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idd = request.getParameter("id");
		int id = Integer.parseInt(idd);
		
		DbCon db = new DbCon();
		
		try {
			Connection con1 = db.getConnection();
			try {
				HttpSession session = request.getSession(false);
				String userEmail = (String) session.getAttribute("UserEmail");
				session.setAttribute("action", "UPDATE");
				session.setAttribute("isLoggedIn", "true");
				session.setAttribute("isLoggedOut", "false");
				
				Integer id1 = (Integer) session.getAttribute("id");
				
				//creation time of session
				Date creationTime = new Date(session.getCreationTime());
				 DateFormat dateFormat = new SimpleDateFormat("MM-dd hh:mm:ss");
				 String createTime = dateFormat.format(creationTime);
				//last Access time of session
				Date lastAccessTime = new Date(session.getLastAccessedTime());
				String lastTime = dateFormat.format(lastAccessTime);
				System.out.println("==============================>");
				System.out.println(creationTime);
				System.out.println(lastAccessTime);
				
				db.addActivity(id1,(String) session.getAttribute("isLoggedIn"), createTime,(String) session.getAttribute("action"), lastTime,(String) session.getAttribute("isLoggedOut"));
				
				if(!Optional.ofNullable(userEmail).isEmpty()) {
					Connection con = db.getConnection();
					PreparedStatement ps = con.prepareStatement("select * from user where id=?");
					ps.setInt(1, id);
					ResultSet set = ps.executeQuery();
					
					if(set.next()) {
						PrintWriter out = response.getWriter();
						
						out.append("<!DOCTYPE html>\r\n"
								+ "<html>\r\n"
								+ "<head>\r\n"
								+ "<meta charset=\"UTF-8\">\r\n"
								+ "<title>Insert title here</title>\r\n"
								+ "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css\" integrity=\"sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T\" crossorigin=\"anonymous\">\r\n"
								+ "<link rel=\"stylesheet\" href=\"styles.css\">\r\n"
								+ "</head>\r\n"
								+ "<body>\r\n"
								+ "<section class=\"vh-100\">\r\n"
								+ "  <div class=\"container py-5 h-100\">\r\n"
								+ "    <div class=\"row d-flex align-items-center justify-content-center h-100\">\r\n"
								+ "      <div class=\"col-md-8 col-lg-7 col-xl-6\">\r\n"
								+ "        <img src=\"https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-login-form/draw2.svg\"\r\n"
								+ "          class=\"img-fluid\" alt=\"Phone image\">\r\n"
								+ "      </div>\r\n"
								+ "      <div class=\"col-md-7 col-lg-5 col-xl-5 offset-xl-1\">\r\n"
								+ "        <form action=\"edit\" method=\"post\">\r\n"
								+ "		\r\n"
								+ "			<input type=\"hidden\" name=\"id\" id=\"id\" value="+set.getString(1)+" class=\"form-control form-control-lg\" />\r\n"
								+ "         <input type=\"hidden\" name=\"pass\" id=\"pass\" value="+set.getString(4)+" class=\"form-control form-control-lg\" />\r\n"
								+ "          <!-- Name input -->\r\n"
								+ "          <div class=\"form-outline mb-4\">\r\n"
								+ "            <input type=\"text\" name=\"name\" value="+set.getString(2)+" id=\"form1Example13\" class=\"form-control form-control-lg\" />\r\n"
								+ "            <label class=\"form-label\" for=\"form1Example13\">Name</label>\r\n"
								+ "          </div>\r\n"
								+ "		  \r\n"
								+ "		  <!-- Email input -->\r\n"
								+ "          <div class=\"form-outline mb-4\">\r\n"
								+ "            <input type=\"email\" name=\"email\" value="+set.getString(3)+" id=\"form1Example13\" class=\"form-control form-control-lg\" />\r\n"
								+ "            <label class=\"form-label\" for=\"form1Example13\">Email address</label>\r\n"
								+ "          </div>\r\n"
								+ "\r\n"
								+ "          <!-- City input -->\r\n"
								+ "          <div class=\"form-outline mb-4\">\r\n"
								+ "            <input type=\"text\" name=\"city\" value="+set.getString(5)+" id=\"form1Example23\" class=\"form-control form-control-lg\" />\r\n"
								+ "            <label class=\"form-label\" for=\"form1Example23\">City</label>\r\n"
								+ "          </div>\r\n"
								+ "\r\n"
								+ "          <!-- Submit button -->\r\n"
								+ "          <button type=\"submit\" class=\"btn btn-primary btn-lg btn-block\">Update User</button>\r\n"
								+ "\r\n"
								+ "          <div class=\"divider d-flex align-items-center my-4\">\r\n"
								+ "            <p class=\"text-center fw-bold mx-3 mb-0 text-muted\">OR</p>\r\n"
								+ "          </div>\r\n"
								+ "\r\n"
								+ "          <a class=\"btn btn-primary btn-lg btn-block\" style=\"background-color: #3b5998\" href=\"#!\"\r\n"
								+ "            role=\"button\">\r\n"
								+ "            <i class=\"fab fa-facebook-f me-2\"></i>Continue with Facebook\r\n"
								+ "          </a>\r\n"
								+ "          <a class=\"btn btn-primary btn-lg btn-block\" style=\"background-color: #55acee\" href=\"#!\"\r\n"
								+ "            role=\"button\">\r\n"
								+ "            <i class=\"fab fa-twitter me-2\"></i>Continue with Twitter</a>\r\n"
								+ "\r\n"
								+ "        </form>\r\n"
								+ "      </div>\r\n"
								+ "    </div>\r\n"
								+ "  </div>\r\n"
								+ "</section>\r\n"
								+ "</body>\r\n"
								+ "</html>");
					}
				}

			} catch (Exception e) {
				System.out.println("logout");
				PrintWriter out1 = response.getWriter();
				out1.append("<html><center><b>  Please Login First!!  </b></center><html>");
				RequestDispatcher rd = request.getRequestDispatcher("Index.html");
				rd.include(request, response);
			}
			
			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String city = request.getParameter("city");
		int id = Integer.parseInt(request.getParameter("id"));
		
		DbCon db = new DbCon();
		
		try {
			Connection con = db.getConnection();
			PreparedStatement ps1 = con.prepareStatement("update user set name=?, email=?, city=? where id=?");
			ps1.setString(1, name);
			ps1.setString(2, email);
			ps1.setString(3, city);
			ps1.setInt(4, id);
			boolean executeUpdate = ps1.executeUpdate() > 0;
			if(executeUpdate) {
				PrintWriter out = response.getWriter();
				out.append("<html>\r\n"
						+ "  <head>\r\n"
						+ "    <link href=\"https://fonts.googleapis.com/css?family=Nunito+Sans:400,400i,700,900&display=swap\" rel=\"stylesheet\">\r\n"
						+ "  </head>\r\n"
						+ "    <style>\r\n"
						+ "      body {\r\n"
						+ "        text-align: center;\r\n"
						+ "        padding: 40px 0;\r\n"
						+ "        background: #EBF0F5;\r\n"
						+ "      }\r\n"
						+ "        h1 {\r\n"
						+ "          color: #88B04B;\r\n"
						+ "          font-family: \"Nunito Sans\", \"Helvetica Neue\", sans-serif;\r\n"
						+ "          font-weight: 900;\r\n"
						+ "          font-size: 40px;\r\n"
						+ "          margin-bottom: 10px;\r\n"
						+ "        }\r\n"
						+ "        p {\r\n"
						+ "          color: #404F5E;\r\n"
						+ "          font-family: \"Nunito Sans\", \"Helvetica Neue\", sans-serif;\r\n"
						+ "          font-size:20px;\r\n"
						+ "          margin: 0;\r\n"
						+ "        }\r\n"
						+ "      i {\r\n"
						+ "        color: #9ABC66;\r\n"
						+ "        font-size: 100px;\r\n"
						+ "        line-height: 200px;\r\n"
						+ "        margin-left:-15px;\r\n"
						+ "      }\r\n"
						+ "      .card {\r\n"
						+ "        background: white;\r\n"
						+ "        padding: 60px;\r\n"
						+ "        border-radius: 4px;\r\n"
						+ "        box-shadow: 0 2px 3px #C8D0D8;\r\n"
						+ "        display: inline-block;\r\n"
						+ "        margin: 0 auto;\r\n"
						+ "      }\r\n"
						+ "    </style>\r\n"
						+ "    <body>\r\n"
						+ "      <div class=\"card\">\r\n"
						+ "      <div style=\"border-radius:200px; height:200px; width:200px; background: #F8FAF5; margin:0 auto;\">\r\n"
						+ "        <i class=\"checkmark\">✓</i>\r\n"
						+ "      </div>\r\n"
						+ "        <h1>Success</h1> \r\n"
						+ "        <p>"+name+" Updated Successfully!! </p>\r\n"
						+ "      </div>\r\n"
						+ "    </body>\r\n"
						+ "</html>");
//				RequestDispatcher rd = request.getRequestDispatcher("/StudentServlet");
//				rd.include(request, response);
				HttpSession session = request.getSession(false);
				int page = (int) session.getAttribute("ctPage");
				response.sendRedirect("userlist.jsp?page="+page);
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
