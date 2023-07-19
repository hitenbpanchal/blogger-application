package com.authfilter;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;

import com.dbcon.DbCon;
import com.mysql.cj.util.StringUtils;

/**
 * Servlet Filter implementation class AuthFilter
 */
@WebFilter("/StudentServlet")
public class AuthFilter extends HttpFilter implements Filter {

	/**
	 * @see HttpFilter#HttpFilter()
	 */
	public AuthFilter() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		// pass the request along the filter chain
		String email = request.getParameter("email");
		System.out.println(email);
		String password = request.getParameter("pass");
		System.out.println(password);

		DbCon db = new DbCon();

		if (!validateUser(email, password)) {
			System.out.println("Login Failed");
			RequestDispatcher rd = request.getRequestDispatcher("invalid.jsp");
			rd.forward(request, response);
			return;
		}

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

				if (!(email.equals(DbEmail) && password.equals(DbPass))) {
					System.out.println("Login Failed");
					RequestDispatcher rd = request.getRequestDispatcher("invalid.jsp");
					rd.forward(request, response);
					return;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

	boolean validateUser(String username, String password) {
		if (StringUtils.isNullOrEmpty(username) | StringUtils.isNullOrEmpty(password)) {
			return false;
		}
		return true;
	}

}
