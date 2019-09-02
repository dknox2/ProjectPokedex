package edu.westga.cs3212.projectpokedex.test.model.pokemon.postevolutions;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.projectpokedex.model.pokemon.Pokemon;
import edu.westga.cs3212.projectpokedex.model.pokemon.evolution.Evolution;
import edu.westga.cs3212.projectpokedex.model.pokemon.evolution.PostEvolutions;

class TestContainsAll {
	
	@Test
	void testContainsNoCollection() {
		PostEvolutions post = new PostEvolutions();
		Collection<Evolution> collection = new ArrayList<Evolution>();
		Evolution evolution = new Evolution(new Pokemon("name", 1, "fire", null, "both", "location", "description", 0.1, 10), "Thunderstone");
		collection.add(evolution);
		assertEquals(post.containsAll(collection), false);
	}

	@Test
	void testContainsACollection() {
		PostEvolutions post = new PostEvolutions();
		Collection<Evolution> collection = new ArrayList<Evolution>();
		Evolution evolution = new Evolution(new Pokemon("name", 1, "fire", null, "both", "location", "description", 0.1, 10), "Thunderstone");
		collection.add(evolution);
		post.add(evolution);
		assertEquals(post.containsAll(collection), true);
	}

}
