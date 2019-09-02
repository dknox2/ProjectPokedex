package edu.westga.cs3212.projectpokedex.test.model.pokemon.postevolutions;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.projectpokedex.model.pokemon.Pokemon;
import edu.westga.cs3212.projectpokedex.model.pokemon.evolution.Evolution;
import edu.westga.cs3212.projectpokedex.model.pokemon.evolution.PostEvolutions;

class TestAddAll {

	@Test
	void testAddNull() {
		PostEvolutions post = new PostEvolutions();
		assertThrows(NullPointerException.class, () -> post.addAll(null));
	}
	
	@Test
	void testAddCollection() {
		PostEvolutions post = new PostEvolutions();
		Collection<Evolution> collection = new ArrayList<Evolution>();
		Evolution evolution = new Evolution(new Pokemon("name", 1, "fire", null, "both", "location", "description", 0.1, 10), "Thunderstone");
		collection.add(evolution);
		post.add(evolution);
		assertEquals(post.addAll(collection), true);
	}
	
	@Test
	void testAddEmptyCollection() {
		PostEvolutions post = new PostEvolutions();
		Collection<Evolution> collection = new PostEvolutions();
		assertEquals(post.addAll(collection), false);
	}

}
