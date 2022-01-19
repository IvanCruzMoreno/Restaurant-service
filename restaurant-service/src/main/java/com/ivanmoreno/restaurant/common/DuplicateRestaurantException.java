package com.ivanmoreno.restaurant.common;

import lombok.Data;

@Data
public class DuplicateRestaurantException extends Exception{

	private String message;
	private Object[] args;
	
	public DuplicateRestaurantException(String name) {
		this.message = "There is already a restaurant with the name - " + name;
	}
	
	public DuplicateRestaurantException(Object[] args) {
		this.args = args;
	}
	
	public DuplicateRestaurantException(String message, Object[] args) {
		this.message = message;
		this.args = args;
	}
	
	
	private static final long serialVersionUID = 1L;
}
