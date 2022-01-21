package com.ivanmoreno.restaurant.domain.valueobject;

import java.util.List;
import java.util.Optional;

import com.ivanmoreno.restaurant.domain.model.entity.Table;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class RestaurantVO {

	private String id;
	private String name;
	private String address;
	private Optional<List<Table>> tables = Optional.empty();
	
}
