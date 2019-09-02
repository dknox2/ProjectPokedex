package edu.westga.cs3212.projectpokedex.test.model.pokemon.postevolutions;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.projectpokedex.model.pokemon.Pokemon;
import edu.westga.cs3212.projectpokedex.model.pokemon.evolution.Evolution;
import edu.westga.cs3212.projectpokedex.model.pokemon.evolution.PostEvolutions;

class TestToString {

	@Test
	void testEmptyToString() {
		PostEvolutions post = new PostEvolutions();
		assertEquals(post.toString(), "");
	}
	
	@Test
	void testOneObjectToString() {
		PostEvolutions post = new PostEvolutions();
		Evolution evolution = new Evolution(new Pokemon("name", 1, "fire", null, "both", "location", "description", 0.1, 10), "Thunderstone");
		post.add(evolution);
		assertEquals(post.toString(), evolution.getPokemon().getName() + ": " + evolution.getEvolutionMethod() + System.lineSeparator());
	}
	
	@Test
	void testThreeObjectToString() {
		PostEvolutions post = new PostEvolutions();
		Evolution evolution1 = new Evolution(new Pokemon("name", 1, "fire", null, "both", "location", "description", 0.1, 10), "Thunderstone");
		Evolution evolution2 = new Evolution(new Pokemon("nameTest", 1, "fire", null, "both", "location", "description", 0.1, 10), "water stone");
		Evolution evolution3 = new Evolution(new Pokemon("Testname", 1, "fire", null, "both", "location", "description", 0.1, 10), "fire stone");
		post.add(evolution1);
		post.add(evolution2);
		post.add(evolution3);
		assertEquals(post.toString(), evolution1.getPokemon().getName() + ": " + evolution1.getEvolutionMethod() + System.lineSeparator() +
										evolution2.getPokemon().getName() + ": " + evolution2.getEvolutionMethod() + System.lineSeparator() +
										evolution3.getPokemon().getName() + ": " + evolution3.getEvolutionMethod() + System.lineSeparator());
	}

}
