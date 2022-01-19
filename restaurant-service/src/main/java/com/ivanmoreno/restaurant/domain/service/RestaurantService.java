package com.ivanmoreno.restaurant.domain.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.ivanmoreno.restaurant.domain.model.entity.Entity;
import com.ivanmoreno.restaurant.domain.model.entity.Restaurant;

public interface RestaurantService {

	public void add(Restaurant restaurant) throws Exception;
	
	public void update(Restaurant restaurant) throws Exception;
	
	public void delete(String id) throws Exception;
	
	public Entity<String> findById(String restaurantId) throws Exception;
	
	public Collection<Restaurant> findByName(String name) throws Exception;
	
	public Collection<Restaurant> findByCriteria(Map<String, List<String>> name) throws Exception;
}
