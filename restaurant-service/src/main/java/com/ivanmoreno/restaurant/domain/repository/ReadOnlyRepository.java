package com.ivanmoreno.restaurant.domain.repository;

import java.util.Collection;

/*
 * It is useful if we only want to expose a read-only abstraction of the repository
 * */
public interface ReadOnlyRepository<TE, T> {

	boolean contains(T id);
	
	TE get(T id);
	
	Collection<TE> getAll();
}
