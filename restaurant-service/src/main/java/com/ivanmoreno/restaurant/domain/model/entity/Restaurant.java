package com.ivanmoreno.restaurant.domain.model.entity;

import java.util.List;
import java.util.Optional;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Restaurant extends BaseEntity<String>{

	private Optional<List<Table>> tables;
	private String address;
	
	  
	  public Restaurant(String name, String id, String address, Optional<List<Table>> tables) {
	    super(id, name);
	    this.address = address;
	    this.tables = tables;
	  }

	  private Restaurant(String name, String id) {
	    super(id, name);
	    this.tables = Optional.empty();
	  }

	  public static Restaurant getDummyRestaurant() {
	    return new Restaurant(null, null);
	  }
}
