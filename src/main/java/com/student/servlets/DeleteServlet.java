package com.student.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
 * Servlet implementation class DeleteServlet
 */
@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("id"));
		DbCon db = new DbCon();
		try {
			Connection con1 = db.getConnection();
			HttpSession session = request.getSession(false);
			session.setAttribute("action", "DELETE");
			Integer idd = (Integer) session.getAttribute("id");
			session.setAttribute("isLoggedIn", "true");
			session.setAttribute("isLoggedOut", "false");
			int page = (int) session.getAttribute("ctPage");
			
			
			
			Date creationTime = new Date(session.getCreationTime());
			 DateFormat dateFormat = new SimpleDateFormat("MM-dd hh:mm:ss");
			 String createTime = dateFormat.format(creationTime);
			//last Access time of session
			Date lastAccessTime = new Date(session.getLastAccessedTime());
			String lastTime = dateFormat.format(lastAccessTime);
			System.out.println("==============================>");
			System.out.println(creationTime);
			System.out.println(lastAccessTime);
						
			db.addActivity(idd, (String) session.getAttribute("isLoggedIn"), createTime,(String) session.getAttribute("action"), lastTime,(String) session.getAttribute("isLoggedOut"));
						
			Connection con = db.getConnection();
			PreparedStatement ps = con.prepareStatement("delete from user where id=?");
			ps.setInt(1, id);
			boolean update = ps.executeUpdate() > 0;
			if(update) {
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
						+ "        <p> User Deleted Successfully!! </p>\r\n"
						+ "      </div>\r\n"
						+ "    </body>\r\n"
						+ "</html>");
//				RequestDispatcher rd = request.getRequestDispatcher("Index.html");
//				rd.include(request, response);
				response.sendRedirect("userlist.jsp?page="+page);
				
//				rd.include(request, response);
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
		doGet(request, response);
	}

}
