package edu.westga.cs3212.projectpokedex.test.model.pokemon;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.projectpokedex.model.pokemon.Pokemon;

class TestCompareTo {

	@Test
	void testPokemonLessThan() {
		Pokemon pokemon1 = new Pokemon("Bidoof", 1, "normal", "WATER", "LEAFGREEN", "location", "description", 1.0, 1.0);
		Pokemon pokemon2 = new Pokemon("Bidoof", 2, "normal", "WATER", "LEAFGREEN", "location", "description", 1.0, 1.0);
		
		assertEquals(-1, pokemon1.compareTo(pokemon2));
	}
	
	@Test
	void testPokemonGreaterThan() {
		Pokemon pokemon1 = new Pokemon("Bidoof", 2, "normal", "WATER", "LEAFGREEN", "location", "description", 1.0, 1.0);
		Pokemon pokemon2 = new Pokemon("Bidoof", 1, "normal", "WATER", "LEAFGREEN", "location", "description", 1.0, 1.0);
		
		assertEquals(1, pokemon1.compareTo(pokemon2));
	}
	
	@Test
	void testPokemonEqual() {
		Pokemon pokemon1 = new Pokemon("Bidoof", 2, "normal", "WATER", "LEAFGREEN", "location", "description", 1.0, 1.0);
		Pokemon pokemon2 = new Pokemon("Bidoof", 2, "normal", "WATER", "LEAFGREEN", "location", "description", 1.0, 1.0);
		
		assertEquals(0, pokemon1.compareTo(pokemon2));	
	}
}
