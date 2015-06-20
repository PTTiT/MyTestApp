package com.ttp.example.pycotestapp.object;

import com.google.gson.annotations.SerializedName;

public class ResponseData {
	@SerializedName("feed")
	private Feed feed;

	public Feed getFeed() {
		return feed;
	}

	public void setFeed(Feed feed) {
		this.feed = feed;
	}
}
