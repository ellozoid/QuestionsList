package com.questionsList.service.repository;

import com.questionsList.service.interfaces.Repository;

public abstract class BaseRepository {
	public abstract Repository createRepository(String code);
	
	public abstract Repository createRepository(Class type);
	
	protected Repository getRepository(Class type) {
		Repository repo = new StackExchangeRepository();
		if(type == StackExchangeRepository.class) {
			repo = new StackExchangeRepository();
		}
		
		return repo;
	}
	
	protected Repository getRepository(String code) {
		Repository repo = new StackExchangeRepository();
		switch (code) {
			case "se":
				repo = new StackExchangeRepository();
		}
		
		return repo;
	}
}
