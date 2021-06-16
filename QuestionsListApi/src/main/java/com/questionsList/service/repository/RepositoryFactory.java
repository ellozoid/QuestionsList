package com.questionsList.service.repository;

import com.questionsList.service.interfaces.Repository;

public class RepositoryFactory extends BaseRepository {

	@Override
	public Repository createRepository(String code) throws IllegalArgumentException {
		if(code == null || code.isEmpty()) {
			throw new IllegalArgumentException("Wrong repository code" + code);
		}
		return getRepository(code);
	}

	@Override
	public Repository createRepository(Class type) {
		if(type == null) {
			throw new IllegalArgumentException("Wrong repository type.");
		}
		return getRepository(type);
	}
	
}
