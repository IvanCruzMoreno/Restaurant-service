package com.ivanmoreno.restaurant.common;

import lombok.Data;

@Data
public class RestaurantNotFoundException extends Exception{

	private String message;
	private Object[] args;
	
	public RestaurantNotFoundException(String name) {
		this.message = "Restaurant [" + name + "] is not found";
	}
	
	public RestaurantNotFoundException(Object[] args) {
		this.args = args;
	}
	
	public RestaurantNotFoundException(String message, Object[] args) {
		this.message = message;
		this.args = args;
	}
	
	private static final long serialVersionUID = 1L;
}
