package com.ivanmoreno.restaurant.domain.repository;

import java.util.Collection;

public interface RestaurantRepository<R, S> extends Repository<R, S> {

	boolean containsName(String name) throws Exception;
	
	public Collection<R> findByName(String name) throws Exception;
}
