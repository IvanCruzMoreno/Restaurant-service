package com.ivanmoreno.restaurant.controller;

import java.util.Collection;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ivanmoreno.commons.domain.model.entity.Entity;
import com.ivanmoreno.restaurant.common.DuplicateRestaurantException;
import com.ivanmoreno.restaurant.common.InvalidRestaurantException;
import com.ivanmoreno.restaurant.common.RestaurantNotFoundException;
import com.ivanmoreno.restaurant.domain.model.entity.Restaurant;
import com.ivanmoreno.restaurant.domain.service.RestaurantService;
import com.ivanmoreno.restaurant.domain.valueobject.RestaurantVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/v1/restaurants")
public class RestaurantController {

	private RestaurantService restaurantService;

	@Autowired
	public RestaurantController(RestaurantService restaurantService) {
		this.restaurantService = restaurantService;
	}

	@GetMapping
	public ResponseEntity<Collection<Restaurant>> findByName(@RequestParam String name) throws Exception {

		log.info(String.format("restaurant-service findByName() invoked:{ %s } for { %s } ",
				restaurantService.getClass().getName(), name));

		name = name.trim().toLowerCase();
		Collection<Restaurant> restaurants;

		try {
			restaurants = restaurantService.findByName(name);
		} catch (RestaurantNotFoundException ex) {
			log.error("Exception raised findByName REST Call", ex);
			throw ex;
		} catch (Exception ex) {
			log.error("Exception raised findByName REST Call", ex);
			throw ex;
		}
		
		return restaurants == null ? ResponseEntity.noContent().build() : ResponseEntity.ok(restaurants);
	}

	@GetMapping("/{restaurant_id}")
	public ResponseEntity<Entity<String>> findById(@PathVariable(name = "restaurant_id") String id) throws Exception {
		
		log.info(String.format("restaurant-service findById() invoked:{ %s } for { %s } ",
				restaurantService.getClass().getName(), id));
		
		id = id.trim();
		Entity<String> restaurant;
		
		try {
			restaurant = restaurantService.findById(id);
		} catch (Exception e) {
			log.error("Exception raised findById REST Call", e);
			throw e;
		}
		return restaurant != null ? ResponseEntity.ok(restaurant) : ResponseEntity.noContent().build();
	}
	
	@PostMapping()
	public ResponseEntity<Restaurant> add(@RequestBody RestaurantVO restaurantVO) throws Exception {
		
		log.info(String.format("restaurant-service add() invoked:{ %s } for { %s } ",
				restaurantService.getClass().getName(), restaurantVO.getName()));
		
		System.out.println(restaurantVO);
		
		Restaurant restaurant = Restaurant.getDummyRestaurant();
		BeanUtils.copyProperties(restaurantVO, restaurant);
		
		try {
			restaurantService.add(restaurant);
		} catch (DuplicateRestaurantException | InvalidRestaurantException e) {
			log.error("Exception raised add REST Call", e);
			throw e;
		} catch(Exception e) {
			log.error("Exception raised add REST Call", e);
			throw e;
		}
		
		return ResponseEntity.status(HttpStatus.CREATED).body(restaurant);
		
	}
}
