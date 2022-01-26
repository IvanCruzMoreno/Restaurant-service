package com.ivanmoreno.restaurant.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ivanmoreno.restaurant.domain.model.entity.Table;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class RestaurantControllerIntegrationTests extends AbstractRestaurantControllerTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort // shorthand for @Value("${local.server.port}")
	private int port;

	@Test
	void testGetById() {
		Map<String, Object> response = restTemplate.getForObject("http://localhost:" + port + "/v1/restaurants/1",
				Map.class);

		// response.entrySet().forEach(e -> Logger.getGlobal().info(e.getKey() + " ::: "
		// + e.getValue()));
		assertNotNull(response);

		// Asserting API Response
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

	@Test
	public void testGetById_NoContent() {

		ResponseEntity<Map> responseE = restTemplate.getForEntity("http://localhost:" + port + "/v1/restaurants/99",
				Map.class);

		assertNotNull(responseE);

		// Should return no content as there is no restaurant with id 99
		assertEquals(HttpStatus.NO_CONTENT, responseE.getStatusCode());
	}
	
	@Test
	public void testGetByName() {
		
		Map<String, Object> pathVariables = new HashMap<>();
		pathVariables.put("name", "KFC");
		
		ResponseEntity<List> responseEntity = restTemplate
				.exchange("http://localhost:" + port + "/v1/restaurants?name={name}", HttpMethod.GET, null, List.class, pathVariables);
		
		//responseEntity.getBody().forEach(e -> Logger.getGlobal().info(e.toString()));
		assertNotNull(responseEntity);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		List<Map<String, Object>> responses = responseEntity.getBody();
		assertNotNull(responses);
		// Assumed only single instance exist for restaurant name contains word "KFC"
		assertTrue(responses.size() == 1);
		
		
		Map<String, Object> response = responses.get(0);
		
		String id = response.get("id").toString();
		assertNotNull(id);
		assertEquals("2", id);
		
		String name = response.get("name").toString();
		assertNotNull(name);
		assertEquals("KFC", name);
		
		Boolean isModified = (Boolean) response.get("isModified");
		assertNull(isModified);
		
		List<Table> tableList = (List<Table>) response.get("tables");
		assertNull(tableList);
	}
	
	@Test
	  public void testAdd() {

	    Map<String, Object> requestBody = new HashMap<>();
	    requestBody.put("name", "La Plaza Restaurant");
	    requestBody.put("id", "11");
	    requestBody.put("address", "address of La Plaza Restaurant");
	    
	    Map<String, Object> table1 = new HashMap<>();
	    table1.put("name", "Table 1");
	    table1.put("id", BigInteger.ONE);
	    table1.put("capacity", Integer.valueOf(6));
	    Map<String, Object> table2 = new HashMap<>();
	    table2.put("name", "Table 2");
	    table2.put("id", BigInteger.valueOf(2));
	    table2.put("capacity", Integer.valueOf(4));
	    Map<String, Object> table3 = new HashMap<>();
	    table3.put("name", "Table 3");
	    table3.put("id", BigInteger.valueOf(3));
	    table3.put("capacity", Integer.valueOf(2));
	    
	    List<Map<String, Object>> tableList = new ArrayList();
	    tableList.add(table1);
	    tableList.add(table2);
	    tableList.add(table3);
	    
	    requestBody.put("tables", tableList);
	    
//	    HttpHeaders headers = new HttpHeaders();
//	    headers.setContentType(MediaType.APPLICATION_JSON);
//	    HttpEntity<String> entity = new HttpEntity<>(objectMapper.writeValueAsString(requestBody),
//	        headers);
//
//	    ResponseEntity<Map> responseE = restTemplate
//	        .exchange("http://localhost:" + port + "/v1/restaurants", HttpMethod.POST, entity,
//	            Map.class, Collections.EMPTY_MAP);

	    ResponseEntity<Map> responseE = restTemplate
	        .postForEntity("http://localhost:" + port + "/v1/restaurants", requestBody, Map.class);

	    assertNotNull(responseE);

	    // Should return created (status code 201)
	    assertEquals(HttpStatus.CREATED, responseE.getStatusCode());

	    
	    //validating the newly created restaurant using API call
	    Map<String, Object> response
	        = restTemplate.getForObject("http://localhost:" + port + "/v1/restaurants/11", Map.class);

	    assertNotNull(response);

	    //Asserting API Response
	    String id = response.get("id").toString();
	    assertNotNull(id);
	    assertEquals("11", id);
	    
	    String name = response.get("name").toString();
	    assertNotNull(name);
	    assertEquals("La Plaza Restaurant", name);
	    
	    String address = response.get("address").toString();
	    assertNotNull(address);
	    assertEquals("address of La Plaza Restaurant", address);
	    
	    Boolean isModified = (Boolean) response.get("isModified");
		assertNull(isModified);
		
	    List<Map<String, Object>> tableList2 = (List<Map<String, Object>>) response.get("tables");
	    assertNotNull(tableList2);
	    assertEquals(tableList2.size(), 3);
	    tableList2.stream().forEach((table) -> {
	      assertNotNull(table);
	      assertNotNull(table.get("name"));
	      assertNotNull(table.get("id"));
	      assertTrue((Integer) table.get("capacity") > 0);
	    });
	    
	  }

}
