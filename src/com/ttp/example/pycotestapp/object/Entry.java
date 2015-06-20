package com.ttp.example.pycotestapp.object;

import java.util.Date;

import com.google.gson.annotations.SerializedName;

public class Entry {
	@SerializedName("title")
	private String title;

	@SerializedName("link")
	private String link;

	@SerializedName("author")
	private String author;

	@SerializedName("publishedDate")
	private Date publishedDate;

	@SerializedName("contentSnippet")
	private String contentSnippet;

	@SerializedName("content")
	private String content;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Date getPublishedDate() {
		return publishedDate;
	}

	public void setPublishedDate(Date publishedDate) {
		this.publishedDate = publishedDate;
	}

	public String getContentSnippet() {
		return contentSnippet;
	}

	public void setContentSnippet(String contentSnippet) {
		this.contentSnippet = contentSnippet;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
