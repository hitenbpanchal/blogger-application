<%@page import="com.serviceimpl.UserServiceImpl"%>
<%@page import="com.dao.UserDao"%>
<%@page import="java.util.Optional"%>
<%@page import="com.models.User"%>
<%@page import="java.util.List"%>
<%@page import="com.dbcon.DbCon"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
String userEmail = (String) session.getAttribute("UserEmail");
if (!Optional.ofNullable(userEmail).isEmpty()) {
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User List</title>

<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Roboto&display=swap"
	rel="stylesheet">
<style>
html, body, .intro {
	height: 100%;
	font-family: 'Roboto', sans-serif;
}

h1 {
	text-align: center;
	background: linear-gradient(to bottom, rgba(205, 156, 242, 1),
		rgba(246, 243, 255, 1));
	margin: 5px;
	padding: 5px;
	color: darkslategrey;
}

.gradient-custom-1 {
	/* fallback for old browsers */
	background: #cd9cf2;
	/* Chrome 10-25, Safari 5.1-6 */
	background: -webkit-linear-gradient(to top, rgba(205, 156, 242, 1),
		rgba(246, 243, 255, 1));
	/* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
	background: linear-gradient(to top, rgba(205, 156, 242, 1),
		rgba(246, 243, 255, 1))
}

table td, table th {
	text-overflow: ellipsis;
	white-space: nowrap;
	overflow: hidden;
}

tbody td {
	font-weight: 500;
	color: black;
}

.page {
	margin-top: 25px;
}

.pagediv {
	display: flex;
	justify-content: center;
	align-items: center
}

body {
	overflow: hidden;
}
</style>
</head>
<body>
	<%
	final int limit = 6;
	String path2 = request.getContextPath();
	System.out.println("this is: " + path2);
	%>

	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<a class="navbar-brand" href="#"> <%=session.getAttribute("UserName")%>
		</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item active"><a class="nav-link"
					href="userlist.jsp?page=<%=session.getAttribute("ctPage")%>">Home
						<span class="sr-only">(current)</span>
				</a></li>
				<li class="nav-item"><a class="nav-link" href="myBlog.jsp">My
						Blogs</a></li>
				<li class="nav-item"><a class="nav-link"
					href="addBlog.jsp?id=<%=session.getAttribute("id")%>&name= <%=session.getAttribute("UserName")%> ">Add
						new Blogs</a></li>
				<li class="nav-item"><a class="nav-link" href="viewAllBlog.jsp">View
						All Blogs</a></li>
				<li class="nav-item"><a class="nav-link" href="LogoutServlet">Log
						Out</a></li>
			</ul>
			<form action="userSearchlist.jsp" method="GET"
				class="form-inline my-2 my-lg-0">
				<input class="form-control mr-sm-2" type="search" name="pattern"
					placeholder="Search" aria-label="Search">
				<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
			</form>
		</div>
	</nav>

	<section class="intro">
		<div class="gradient-custom-1 h-100">
			<h1>
				Hello
				<%=session.getAttribute("UserName")%>
			</h1>
			<div class="mask d-flex align-items-center h-100">
				<div class="container">
					<div class="row justify-content-center">
						<div class="col-12">
							<div class="table-responsive bg-white">
								<table class="table mb-0">
									<thead>
										<tr>
											<th scope="col">ID</th>
											<th scope="col">NAME</th>
											<th scope="col">EMAIL</th>
											<th scope="col">CITY</th>
											<th colspan="2" scope="col">ACTION</th>
										</tr>
									</thead>
									<tbody>
										<%
										try {
											int pageNo = Integer.parseInt(request.getParameter("page"));
											session.setAttribute("ctPage", pageNo);
											UserDao userDao = new UserServiceImpl();
											List<User> list = userDao.getAllUsers(pageNo, limit);
											for (User user : list) {
										%>
										<tr>

											<td><%=user.getId()%></td>
											<td><%=user.getName()%></td>
											<td><%=user.getEmail()%></td>
											<td><%=user.getCity()%></td>
											<td><a href="edit?id=<%=user.getId()%>"> Edit </a></td>
											<td><a href="delete?id=<%=user.getId()%>"> Delete </a></td>
										</tr>
										<%
										}
										} catch (Exception e) {
										}
										%>

									</tbody>
								</table>
							</div>
						</div>
					</div>
					<div class="pagediv">
						<nav class="page d-flex" aria-label="Page navigation example">
							<ul class="pagination">
								<%
								int count = new DbCon().getCount();
								int pageNo = count / limit;
								System.out.println("Number of pages " + pageNo);
								for (int i = 0; i <= pageNo; i++) {
								%>
								<li class="page-item"><a class="page-link"
									href="userlist.jsp?page=<%=i%>"> <%=i + 1%></a></li>
								<%
								}
								%>
							</ul>
						</nav>
					</div>
				</div>
			</div>
		</div>
	</section>

</body>
</html>
<%
} else {

response.sendRedirect("loginfirst.jsp");
}
%>
