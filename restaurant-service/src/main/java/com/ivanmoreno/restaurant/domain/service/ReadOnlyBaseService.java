package com.ivanmoreno.restaurant.domain.service;

import com.ivanmoreno.commons.domain.repository.ReadOnlyRepository;

public abstract class ReadOnlyBaseService<TE, T> {

	private ReadOnlyRepository<TE, T> repository;
	
	public ReadOnlyBaseService(ReadOnlyRepository<TE, T>  repo) {
		this.repository = repo;
	}
}
