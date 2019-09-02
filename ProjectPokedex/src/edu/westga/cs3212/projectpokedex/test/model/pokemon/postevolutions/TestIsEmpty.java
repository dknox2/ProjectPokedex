package edu.westga.cs3212.projectpokedex.test.model.pokemon.postevolutions;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.projectpokedex.model.pokemon.Pokemon;
import edu.westga.cs3212.projectpokedex.model.pokemon.evolution.Evolution;
import edu.westga.cs3212.projectpokedex.model.pokemon.evolution.PostEvolutions;

class TestIsEmpty {

	@Test
	void testEmptyCollection() {
		PostEvolutions post = new PostEvolutions();
		assertEquals(post.isEmpty(), true);
	}
	
	@Test
	void testNonEmptyCollection() {
		PostEvolutions post = new PostEvolutions();
		Evolution evolution = new Evolution(new Pokemon("name", 1, "fire", null, "both", "location", "description", 0.1, 10), "Thunderstone");
		post.add(evolution);
		assertEquals(post.isEmpty(), false);
	}

}
