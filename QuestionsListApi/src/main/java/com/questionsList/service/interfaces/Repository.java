package com.questionsList.service.interfaces;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public interface Repository {
	public JSONObject getQuestions(String query, int page, int pageSize) throws JSONException, IOException, RuntimeException;
}
