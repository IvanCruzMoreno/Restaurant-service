package com.ivanmoreno.restaurant.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collection;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ivanmoreno.restaurant.domain.model.entity.Entity;
import com.ivanmoreno.restaurant.domain.model.entity.Restaurant;
import com.ivanmoreno.restaurant.domain.valueobject.RestaurantVO;

public abstract class AbstractRestaurantControllerTests {

	protected static final String RESTAURANT = "2";
	
	protected static final String RESTAURANT_NAME = "KFC";
	
	protected static final String RESTAURANT_ADDRESS = "9 place des Vosges, 75004, Paris";
	
	@Autowired
	RestaurantController restaurantController;
	
	@Test
	public void validRestaurantById() throws Exception {
		Logger.getGlobal().info("Start validRestaurantById test");
		
		ResponseEntity<Entity<String>> restaurant = restaurantController.findById(RESTAURANT);
		
		assertEquals(HttpStatus.OK, restaurant.getStatusCode());
		assertTrue(restaurant.hasBody());
		assertNotNull(restaurant.getBody());
		assertEquals(RESTAURANT, restaurant.getBody().getId());
	    assertEquals(RESTAURANT_NAME, restaurant.getBody().getName());
	    
	    Logger.getGlobal().info("End validResturantById test");
	}
	
	@Test
	public void validRestaurantByName() throws Exception {
		Logger.getGlobal().info("Start validRestaurantByName test");
		
		ResponseEntity<Collection<Restaurant>> restaurants = restaurantController.findByName(RESTAURANT_NAME);
		Logger.getGlobal().info("In validAccount test");
		
		assertEquals(HttpStatus.OK, restaurants.getStatusCode());
		assertTrue(restaurants.hasBody());
		assertNotNull(restaurants.getBody());
		assertFalse(restaurants.getBody().isEmpty());
		//(Restaurant) restaurants.getBody().toArray()[0];
		Restaurant restaurant = restaurants.getBody().iterator().next();
		
		assertEquals(RESTAURANT, restaurant.getId());
	    assertEquals(RESTAURANT_NAME, restaurant.getName());
	    
	    Logger.getGlobal().info("End validResturantByName test");
	}
	
	@Test
	public void validAdd() throws Exception {
		Logger.getGlobal().info("Start validAdd test");
		
	    RestaurantVO restaurant = new RestaurantVO();
	    restaurant.setId("999");
	    restaurant.setName("Test Restaurant");

	    ResponseEntity<Restaurant> restaurants = restaurantController.add(restaurant);
	    
	    assertEquals(HttpStatus.CREATED, restaurants.getStatusCode());
	    
	    Logger.getGlobal().info("End validAdd test");
	}
	
	
	
}
