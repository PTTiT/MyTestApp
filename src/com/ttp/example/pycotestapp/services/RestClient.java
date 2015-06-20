package com.ttp.example.pycotestapp.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

public final class RestClient {

	/** The time it takes for our client to timeout */
	public static final int HTTP_TIMEOUT = 30 * 1000; // milliseconds

	/** Single instance of our HttpClient */
	private static DefaultHttpClient mHttpClient;

	/**
	 * Get our single instance of our HttpClient object.
	 * 
	 * @return an HttpClient object with connection parameters set
	 */
	private static DefaultHttpClient getHttpClient() {
		if (mHttpClient == null) {
			mHttpClient = new DefaultHttpClient();
			final HttpParams params = mHttpClient.getParams();
			HttpConnectionParams.setConnectionTimeout(params, HTTP_TIMEOUT);
			HttpConnectionParams.setSoTimeout(params, HTTP_TIMEOUT);
			ConnManagerParams.setTimeout(params, HTTP_TIMEOUT);
		}

		return mHttpClient;
	}

	/**
	 * Perform a get request to the specified url
	 * 
	 * @param url
	 *            The web address to post the request to
	 * @param headers
	 * 
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static String executeHttpGet(String url)
			throws ClientProtocolException, IOException {
		BufferedReader in = null;

		HttpClient client = getHttpClient();
		HttpGet request = new HttpGet(url);

		// add headers
		request.addHeader("Content-Type", "application/json; charset=UTF-8");
		request.addHeader("Accept", "application/json");

		HttpContext context = new BasicHttpContext();
		HttpResponse response = client.execute(request, context);

		StringBuffer sb = new StringBuffer("");
		String line = "";

		in = new BufferedReader(new InputStreamReader(response.getEntity()
				.getContent()));

		while ((line = in.readLine()) != null) {
			sb.append(line);
		}
		in.close();

		String result = sb.toString();

		return result;
	}
}
