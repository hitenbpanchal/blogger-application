package com.models;

public class Comment {
	
	private int id;
	
	private int userId;
	
	private String userName;
	
	private int blogPostId;
	
	private String comments;

	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Comment(int id, int userId, String userName, int blogPostId, String comments) {
		super();
		this.id = id;
		this.userId = userId;
		this.userName = userName;
		this.blogPostId = blogPostId;
		this.comments = comments;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getBlogPostId() {
		return blogPostId;
	}

	public void setBlogPostId(int blogPostId) {
		this.blogPostId = blogPostId;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
	
	
}
