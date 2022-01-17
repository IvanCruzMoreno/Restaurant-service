package com.ivanmoreno.restaurant.domain.repository;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.ivanmoreno.restaurant.domain.model.entity.Restaurant;

@Repository("restaurantRepository")
public class InMemRestaurantRepository implements RestaurantRepository<Restaurant, String>{

	private static final Map<String, Restaurant> entities;
	
	static {
	    entities = new ConcurrentHashMap<>(Map.ofEntries(
	        Map.entry("1",
	            new Restaurant("Burger King", "1", "228 rue de Rivoli, 75001, Paris", Optional.empty())),
	        Map.entry("2",
	            new Restaurant("KFC", "2", "9 place des Vosges, 75004, Paris",
	                Optional.empty())),
	        Map.entry("3",
	            new Restaurant("McDonalds", "3", "84, rue de Varenne, 75007, Paris", Optional.empty())),
	        Map.entry("4", new Restaurant("Alain Ducasse au Plaza Athénée", "4",
	            "25 avenue de Montaigne, 75008, Paris", Optional.empty())),
	        Map.entry("5",
	            new Restaurant("Wendys", "5", "1, avenue Dutuit, 75008, Paris",
	                Optional.empty())),
	        Map.entry("6",
	            new Restaurant("Pizza Hut", "6", "6, rue Balzac, 75008, Paris",
	                Optional.empty())),
	        Map.entry("7",
	            new Restaurant("Dominos Pizza", "7", "4, rue Beethoven, 75016, Paris", Optional.empty())),
	        Map.entry("8",
	            new Restaurant("Starbucks", "8", "Bois de Boulogne, 75016, Paris", Optional.empty())),
	        Map.entry("9",
	            new Restaurant("Doña Pelos", "9", "18 rue Troyon, 75017, Paris", Optional.empty())),
	        Map.entry("10", new Restaurant("Le Bristol", "10",
	            "112, rue du Faubourg St Honoré, 8th arrondissement, Paris", Optional.empty()))));
	  }
	
	@Override
	public void add(Restaurant entity) {
		entities.put(entity.getId(), entity);
	}

	@Override
	public void remove(String id) {
		if(entities.containsKey(id)) {
			entities.remove(id);
		}
	}

	@Override
	public void update(Restaurant entity) {
		if (entities.containsKey(entity.getId())) {
			entities.put(entity.getId(), entity);
		}
	}

	@Override
	public boolean contains(String id) {
		throw new UnsupportedOperationException("Not supported yet");
	}

	@Override
	public Restaurant get(String id) {
		return entities.get(id);
	}

	@Override
	public Collection<Restaurant> getAll() {
		return entities.values();
	}

	@Override
	public boolean containsName(String name) throws Exception {
		try {
			return !this.findByName(name).isEmpty();
		}catch (Exception e) {
			return false;
		}
	}

	@Override
	public Collection<Restaurant> findByName(String name) throws Exception {
		
		List<Restaurant> restaurants = entities.entrySet().stream()
		.filter(entry -> entry.getValue().getName().contains(name))
		.map(entry -> entry.getValue())
		.collect(Collectors.toList());
		
		if(restaurants != null && restaurants.isEmpty()) {
			throw new Exception("Something bad happend :(");
		}
		
		return restaurants;
		
		
	}

}
