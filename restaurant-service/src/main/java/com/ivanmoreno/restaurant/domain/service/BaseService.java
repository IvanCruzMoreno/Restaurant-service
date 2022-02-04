package com.ivanmoreno.restaurant.domain.service;

import java.util.Collection;

import com.ivanmoreno.commons.domain.repository.Repository;

public abstract class BaseService<TE, T> extends ReadOnlyBaseService<TE, T>{

	private Repository<TE, T> repositoryV2;
	
	public BaseService(Repository<TE, T> repo) {
		super(repo);
		repositoryV2 = repo;
	}
	
	public void add(TE entity) throws Exception {
		repositoryV2.add(entity);
	}
	
	public Collection<TE> getAll() throws Exception {
		return repositoryV2.getAll();
	}

}
