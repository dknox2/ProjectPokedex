package edu.westga.cs3212.projectpokedex.test.model.pokemon;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.projectpokedex.model.pokemon.Pokemon;

class TestSetPokedexNumber {

	@Test
	void testPokedexNumberNegative() {
		Pokemon pokemon = new Pokemon("Bidoof", 1, "normal", "WATER", "LEAFGREEN", "location", "description", 1.0, 1.0);
		
		assertThrows(IllegalArgumentException.class, () -> pokemon.setPokedexNumber(-1));
	}

	@Test
	void testPokedexNumber0() {
		Pokemon pokemon = new Pokemon("Bidoof", 1, "normal", "WATER", "LEAFGREEN", "location", "description", 1.0, 1.0);
		
		assertThrows(IllegalArgumentException.class, () -> pokemon.setPokedexNumber(0));
	}
	
	@Test
	void testPokedexNumber1() {
		Pokemon pokemon = new Pokemon("Bidoof", 5, "normal", "WATER", "LEAFGREEN", "location", "description", 1.0, 1.0);
		
		pokemon.setPokedexNumber(1);
		assertEquals(1, pokemon.getPokedexNumber());
	}
	
	@Test
	void testPokedexNumberGreaterThan1() {
		Pokemon pokemon = new Pokemon("Bidoof", 1, "normal", "WATER", "LEAFGREEN", "location", "description", 1.0, 1.0);
		
		pokemon.setPokedexNumber(152);
		assertEquals(152, pokemon.getPokedexNumber());
	}
}
