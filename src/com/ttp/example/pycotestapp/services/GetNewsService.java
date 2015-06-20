package com.ttp.example.pycotestapp.services;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ttp.example.pycotestapp.object.GetNewsResponseObject;
import com.ttp.example.pycotestapp.utils.StringUtils;

public class GetNewsService {
	private static final String URL = "https://ajax.googleapis.com/ajax/services/feed/load?v=1.0&q=http://www.engadget.com/rss.xml";

	public GetNewsResponseObject getResponseObject()
			throws ClientProtocolException, IOException {
		GetNewsResponseObject responseObject = null;
		String responseJson = RestClient.executeHttpGet(URL);

		if (StringUtils.isNotEmpty(responseJson)) {
			Gson gson = new GsonBuilder().setDateFormat(
					"EEE, dd MMM yyyy HH:mm:ss Z").create();
			// Gson gson = new Gson();
			responseObject = gson.fromJson(responseJson,
					GetNewsResponseObject.class);
		}

		return responseObject;
	}
}
