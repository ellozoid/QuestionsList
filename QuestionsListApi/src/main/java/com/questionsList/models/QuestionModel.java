package com.questionsList.models;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class QuestionModel {
	@JsonProperty("title")
	public String title;
	
	@JsonProperty("date")
	public Date date;
	
	@JsonProperty("author")
	public String author;
	
	@JsonProperty("link")
	public String link;
	
	@JsonProperty("isAnswered")
	public Boolean isAnswered;
	
	public QuestionModel(String title, Date date, String author, String link, Boolean isAnswered) {
		this.title = title;
		this.date = date;
		this.author = author;
		this.link = link;
		this.isAnswered = isAnswered;
	}
}
