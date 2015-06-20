package com.ttp.example.pycotestapp.object;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Feed {
	@SerializedName("title")
	private String title;

	@SerializedName("feedUrl")
	private String feedUrl;

	@SerializedName("link")
	private String link;

	@SerializedName("author")
	private String author;

	@SerializedName("description")
	private String description;

	@SerializedName("type")
	private String type;

	@SerializedName("entries")
	private List<Entry> entries;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFeedUrl() {
		return feedUrl;
	}

	public void setFeedUrl(String feedUrl) {
		this.feedUrl = feedUrl;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Entry> getEntries() {
		return entries;
	}

	public void setEntries(List<Entry> entries) {
		this.entries = entries;
	}
}
