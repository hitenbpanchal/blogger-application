<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
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
.content {
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
}

form {
	width: 50%;
}

h1 {
	margin: 3rem;
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

button {
	widows: 100%;
}
</style>

<title>Add blogs</title>
</head>
<body class="gradient-custom-1">

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
			<form action="userSearchlist.jsp" method="post"
				class="form-inline my-2 my-lg-0">
				<input class="form-control mr-sm-2" type="search" name=""
					placeholder="Search" aria-label="Search">
				<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
			</form>
		</div>
	</nav>

	<div class="content">
		<h1>
			Hello
			<%
		String name = request.getParameter("name");
		out.print(name);
		%>
		</h1>

		<form action="viewBlogServlet" method="post">

			<input type=hidden id="thisField" name="id"
				value="<%String id = request.getParameter("id");
out.print(id);%>">
			<input type=hidden id="thisField" name="name"
				value="<%String name2 = request.getParameter("name");
out.print(name2);%>">
			
			
			<div class="input-group mb-3">
				<input type="file" class="form-control" name="image" id="inputGroupFile02">
				<label class="input-group-text" for="inputGroupFile02">Upload</label>
			</div>

			<!-- Email input -->
			<div class="form-outline mb-4">
				<input type="text" name="title" id="form1Example13"
					class="form-control form-control-lg" /> <label class="form-label"
					for="form1Example13">Blog Title</label>
			</div>

			<div class="form-group">
				<label for="exampleFormControlTextarea1"> Write your
					thoughts here........... </label>
				<textarea class="form-control" name="content"
					id="exampleFormControlTextarea1" rows="3"></textarea>
			</div>
			<!-- Submit button -->
			<button type="submit" class="btn btn-primary">POST</button>
		</form>
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