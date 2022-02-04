package com.ivanmoreno.restaurant.domain.repository;

import java.util.Collection;

import com.ivanmoreno.commons.domain.repository.Repository;

public interface RestaurantRepository<R, S> extends Repository<R, S> {

	boolean containsName(String name) throws Exception;
	
	public Collection<R> findByName(String name) throws Exception;
}
