package com.questionsList.service.helpers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import com.questionsList.models.QuestionModel;
import com.questionsList.models.QuestionResponseModel;

public class StackExchangeJsonHelper {
	public QuestionResponseModel getQuestionsModels(JSONObject jsonObject) {
		JSONArray array = jsonObject.getJSONArray("items");
		ArrayList<QuestionModel> responseList = new ArrayList<QuestionModel>(array.length());
		for (int i = 0; i < array.length(); i++) {
			JSONObject currentObject = array.getJSONObject(i);
			responseList.add(new QuestionModel(
					currentObject.getString("title"),
					getDate(currentObject),
					currentObject.getJSONObject("owner").getString("display_name"),
					currentObject.getString("link"),
					Boolean.valueOf(currentObject.getBoolean("is_answered"))
					));
		}
		QuestionResponseModel response = new QuestionResponseModel();
		ArrayList<HashMap<String,String>> list = new ArrayList<>();
		for (QuestionModel item : responseList) {
			HashMap<String, String> responseMap = new HashMap<String, String>();

			responseMap.put("author", item.author);
			responseMap.put("link", item.link);
			responseMap.put("title", item.title);
			
			String pattern = "MM/dd/yyyy";
			DateFormat df = new SimpleDateFormat(pattern);
			responseMap.put("date", df.format(item.date));
			
			responseMap.put("isAnswered", item.isAnswered.toString());
			
			list.add(responseMap);
		}
		response.questions = new JSONArray(list);
		response.hasMore = jsonObject.getBoolean("has_more");
		return response;
	}
	
	private Date getDate(JSONObject object) {
		long epoch = object.getLong("creation_date");
		return new Date(epoch * 1000);
	}
	
}
