<%@page import="com.serviceimpl.CommentImpl"%>
<%@page import="com.dao.CommentDao"%>
<%@page import="com.serviceimpl.BlogImpl"%>
<%@page import="com.dao.BlogDao"%>
<%@page import="com.models.Comment"%>
<%@page import="java.util.Optional"%>
<%@page import="com.models.ViewBlog"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.dbcon.DbCon"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
String UserEmail = (String) session.getAttribute("UserEmail");
if (!Optional.ofNullable(UserEmail).isEmpty()) {
%>
<!DOCTYPE html>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<style>
body {
	margin: 0;
	padding: 0;
	overflow-x: hidden;
}

.content {
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
	width: 100vw;
	height: fit-content;
}

.card {
	width: 25rem;
	height: fit-content;
	margin-top: 1rem;
}

h1 {
	margin: 3rem;
	font-size: 2.5rem;
}

h6 {
	font-size: 1.1rem;
	font-family: system-ui
}

#viewComment {
	width: 24rem;
	padding: 4px;
	padding-left: 18px;
}

.cards {
	display: flex;
	flex-direction: column-reverse;
	align-items: center;
	justify-content: center;
	background: lightsteelblue;
	width: 50rem;
	padding: 4rem;
}

.gradient-custom-1 {
	/* fallback for old browsers */
	background: #cd9cf2;
	/* Chrome 10-25, Safari 5.1-6 */
	background: -webkit-linear-gradient(to top, rgba(205, 156, 242, 1),
		rgba(246, 243, 255, 1));
	/* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
	background: linear-gradient(to right;
	,
	rgba
	(205,
	156,
	242,
	1),
	rgba(246,
	243,
	255,
	1))
}

.navbar {
	
}
</style>
<title>Hello, world!</title>
</head>
<body>

	<nav class="navbar sticky-top navbar-expand-lg navbar-light bg-light">
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
				<li class="nav-item"><a class="nav-link" href="viewAllBlog.jsp">View
						All Blogs</a></li>
				<li class="nav-item"><a class="nav-link"
					href="addBlog.jsp?id=<%=session.getAttribute("id")%>&name= <%=session.getAttribute("UserName")%> ">Add
						new blogs</a></li>
				<li class="nav-item"><a class="nav-link" href="LogoutServlet">Log
						Out</a></li>
			</ul>
			<form class="form-inline my-2 my-lg-0">
				<input class="form-control mr-sm-2" type="search"
					placeholder="Search" aria-label="Search">
				<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
			</form>
		</div>
	</nav>
	<div class="content gradient-custom-1 overflow-auto">
		<h1>Your Blogs <%= session.getAttribute("UserName") %> </h1>
		<div class="cards">
			<%
			try {
				int id = (int) session.getAttribute("id");
				DbCon db = new DbCon();
				BlogDao blogDao = new BlogImpl();
				List<ViewBlog> list = blogDao.getMyBlogs(id);
				for (ViewBlog blog : list) {
			%>
			<div class="card border-dark mb-3 scroll" style="max-width: 18rem;">
				<%
				session.setAttribute("myBlogId", blog.getId());
				%>
				<div class="card-body text-dark">
					<h5 class="card-title">
						<%=blog.getTitle()%>
					</h5>
					<p class="card-text">
						<%=blog.getcontent()%>
					</p>
				</div>

				<div class="card-footer">
					<a class="" data-toggle="collapse" href="#blogcom<%=blog.getId()%>"
						role="button" aria-expanded="false"
						aria-controls="collapseExample"> View Comments </a>
				</div>
				<% 
				CommentDao commentDao = new CommentImpl();
				List<Comment> comList = commentDao.getAllComments(); 
					for(Comment com : comList ){
						if(com.getBlogPostId() == blog.getId()){
				%>
				<div class="collapse toggle" id="blogcom<%=blog.getId()%>">
					<div class="" id="viewComment">
						<div>
							<h6>
								<%= com.getUserName() %>
							</h6>
							<p>
								<%= com.getComments() %>
							</p>
						</div>
					</div>
				</div>
				<% }} %>
			</div>
			<%
				}}	catch (Exception e) {
					e.printStackTrace();
				}
			%>
		</div>

	</div>

	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js"
		integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js"
		integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
		crossorigin="anonymous"></script>
</body>
</html>
<%
} else {
System.out.println("Please Login First");
}
%>