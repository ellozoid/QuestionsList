package com.questionsList.service.repository;

import java.io.IOException;
import org.json.JSONException;
import org.json.JSONObject;

import com.questionsList.models.QuestionResponseModel;
import com.questionsList.service.helpers.HttpRequestService;
import com.questionsList.service.helpers.StackExchangeJsonHelper;
import com.questionsList.service.interfaces.Repository;

public class StackExchangeRepository implements Repository {

	@Override
	public JSONObject getQuestions(String query, int page, int pageSize) throws JSONException, IOException, RuntimeException {
		if(query.isEmpty()) {
			throw new IllegalArgumentException("Query can't be empty.");
		}
		HttpRequestService requestService = new HttpRequestService();
		final String url = String.format(
				"https://api.stackexchange.com/2.2/search?page=%d&pagesize=%d&order=desc&sort=activity&intitle=%s&site=meta", 
				page, 
				pageSize, 
				query);
		JSONObject jsonObject = requestService.makeGetRequest(url);
		StackExchangeJsonHelper helper = new StackExchangeJsonHelper();

		QuestionResponseModel model = helper.getQuestionsModels(jsonObject);
		JSONObject jo = new JSONObject();
        jo.put("hasMore", model.hasMore);
        jo.put("questions", model.questions);
        
        return jo;
	}
	
}
