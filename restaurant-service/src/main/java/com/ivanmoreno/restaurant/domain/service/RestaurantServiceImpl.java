package com.ivanmoreno.restaurant.domain.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ivanmoreno.restaurant.common.DuplicateRestaurantException;
import com.ivanmoreno.restaurant.common.InvalidRestaurantException;
import com.ivanmoreno.restaurant.domain.model.entity.Entity;
import com.ivanmoreno.restaurant.domain.model.entity.Restaurant;
import com.ivanmoreno.restaurant.domain.repository.RestaurantRepository;

@Service("restaurantService")
public class RestaurantServiceImpl extends BaseService<Restaurant, String> implements RestaurantService {

	private RestaurantRepository<Restaurant, String> restaurantRepo;
	
	public RestaurantServiceImpl(RestaurantRepository<Restaurant, String> repo) {
		super(repo);
		restaurantRepo = repo;
	}

	
	@Override
	public void add(Restaurant restaurant) throws Exception{
		if(restaurant.getName() == null || restaurant.getName().equals("")) {
			Object[] args = {"Restaurant with null or empty name"};
			throw new InvalidRestaurantException("invalidRestaurant", args);
		}
		
		if(restaurantRepo.containsName(restaurant.getName())) {
			Object[] args = {restaurant.getName()};
			throw new DuplicateRestaurantException("duplicateRestaurant", args);
		}
		
		super.add(restaurant);
	}


	@Override
	public void update(Restaurant restaurant) {
		restaurantRepo.update(restaurant);
	}

	@Override
	public void delete(String id) {
		restaurantRepo.remove(id);
	}

	@Override
	public Entity<String> findById(String restaurantId) {
		return restaurantRepo.get(restaurantId);
	}

	@Override
	public Collection<Restaurant> findByName(String name) throws Exception {
		return restaurantRepo.findByName(name);
	}

	@Override
	public Collection<Restaurant> findByCriteria(Map<String, List<String>> name) {
		throw new UnsupportedOperationException("Not supported yet");
	}

}
