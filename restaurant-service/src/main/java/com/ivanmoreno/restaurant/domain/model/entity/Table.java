package com.ivanmoreno.restaurant.domain.model.entity;

import java.math.BigInteger;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ivanmoreno.commons.domain.model.entity.BaseEntity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Table extends BaseEntity<BigInteger> {

	private int capacity;

	public Table(@JsonProperty("name") String name, @JsonProperty("id") BigInteger id,
			@JsonProperty("capacity") int capacity) {

		super(id, name);
		this.capacity = capacity;
	}

}
