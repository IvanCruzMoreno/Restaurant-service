package com.ivanmoreno.restaurant.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import com.ivanmoreno.restaurant.domain.model.entity.Table;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class RestaurantControllerIntegrationTests extends AbstractRestaurantControllerTests{

	@Autowired
	private TestRestTemplate restTemplate;
	
	@LocalServerPort // shorthand for @Value("${local.server.port}")
	private int port;

	@Test
	void testGetById() {
		Map<String, Object> response = 
				restTemplate.getForObject("http://localhost:" + port + "/v1/restaurants/1", Map.class);
		
		//response.entrySet().forEach(e -> Logger.getGlobal().info(e.getKey() + " ::: " + e.getValue()));
		assertNotNull(response);
		
		//Asserting API Response
	    String id = response.get("id").toString();
	    assertNotNull(id);
	    assertEquals("1", id);
	    
	    String name = response.get("name").toString();
	    assertNotNull(name);
	    assertEquals("Burger King", name);
	    
	    Boolean isModified = (Boolean) response.get("isModified");
	    assertNull(isModified);
	    List<Table> tableList = (List<Table>) response.get("tables");
	    assertNull(tableList);
		
	}

}
