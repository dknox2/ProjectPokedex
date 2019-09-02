package edu.westga.cs3212.projectpokedex.test.model.pokemon;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.projectpokedex.model.pokemon.Pokemon;
import edu.westga.cs3212.projectpokedex.model.pokemon.evolution.Evolution;

class TestAddPostEvolution {

	@Test
	void testNullEvolution() {
		Pokemon pokemon = new Pokemon("Bidoof", 1, "normal", "WATER", "LEAFGREEN", "location", "description", 1.0, 1.0);
		
		assertThrows(NullPointerException.class, () -> pokemon.addPostEvolution(null));
	}
	
	@Test
	void testValidAdd() {
		Pokemon pokemon = new Pokemon("Bidoof", 1, "normal", "WATER", "LEAFGREEN", "location", "description", 1.0, 1.0);
		Evolution evolution = new Evolution(pokemon, "Uhhhh");
		
		pokemon.addPostEvolution(evolution);
		
		assertEquals(1, pokemon.getPostEvolutions().size());
	}

}
