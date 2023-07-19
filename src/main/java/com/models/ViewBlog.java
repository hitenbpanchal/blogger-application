package com.models;

public class ViewBlog {
	
	private int id;
	
	private int userId;
	
	private String name;
	
	private String title;
	
	private String content;

	public ViewBlog() {
		super();
	}

	
	public ViewBlog(int id, int userId, String name, String title, String content) {
		super();
		this.id = id;
		this.userId = userId;
		this.name = name;
		this.title = title;
		this.content = content;
	}

	public ViewBlog(String title, String content) {
		super();
		this.title = title;
		this.content = content;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getcontent() {
		return content;
	}

	public void setcontent(String text) {
		this.content = text;
	}


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	
	
	
	
	
}
