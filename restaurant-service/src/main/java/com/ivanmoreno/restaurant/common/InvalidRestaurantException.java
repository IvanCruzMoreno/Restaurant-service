package com.ivanmoreno.restaurant.common;

import lombok.Data;

@Data
public class InvalidRestaurantException extends Exception{

	private String message;
	private Object[] args;
	
	public InvalidRestaurantException(String name) {
		this.message = name + " is an invalid restaurant";
	}
	
	public InvalidRestaurantException(Object[] args) {
		this.args = args;
	}
	
	public InvalidRestaurantException(String message, Object[] args) {
		this.message = message;
		this.args = args;
	}
	
	private static final long serialVersionUID = 1L;
}
