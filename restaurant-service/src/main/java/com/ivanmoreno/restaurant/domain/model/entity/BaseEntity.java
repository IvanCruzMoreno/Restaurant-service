package com.ivanmoreno.restaurant.domain.model.entity;

public abstract class BaseEntity<T> extends Entity<T>{

	private boolean isModified;
	
	public BaseEntity(T id, String name) {
		super.id = id;
		super.name = name;
		isModified = false;
	}
	
	public boolean isModified() {
		return isModified;
	}
}
