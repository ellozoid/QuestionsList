package com.questionsList.service.helpers;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import java.util.zip.GZIPInputStream;

import org.json.JSONException;
import org.json.JSONObject;

public class HttpRequestService {
	public JSONObject makeGetRequest(String urlString) throws IOException, RuntimeException, JSONException {
		HttpURLConnection connection = getConnection(urlString);
		int responseCode = connection.getResponseCode();
		if (responseCode != 200) {
			throw new RuntimeException("HttpResponseCode: " + responseCode);
		} else {
			String jsonString = getJsonString(connection);
			return new JSONObject(jsonString);
		}
	}

	private String getJsonString(HttpURLConnection connection) throws IOException {
		InputStream inStream = connection.getInputStream();
		GZIPInputStream content = new GZIPInputStream(inStream);

		return new Scanner(content, "UTF-8").useDelimiter("\\Z").next();
	}

	private HttpURLConnection getConnection(String urlString) throws IOException {
		URL url = new URL(urlString);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setDoOutput(true);
		connection.setInstanceFollowRedirects(false);
		connection.setRequestMethod("GET");
		connection.setRequestProperty("Content-Type", "application/json");
		connection.setRequestProperty("charset", "utf-8");
		connection.connect();

		return connection;
	}
}
