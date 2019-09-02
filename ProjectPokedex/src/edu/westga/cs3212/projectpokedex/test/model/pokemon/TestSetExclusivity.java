package edu.westga.cs3212.projectpokedex.test.model.pokemon;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.projectpokedex.model.pokemon.Pokemon;

class TestSetExclusivity {

	@Test
	void testNullExclusivity() {
		Pokemon pokemon = new Pokemon("Bidoof", 1, "normal", "WATER", "LEAFGREEN", "location", "description", 1.0, 1.0);
		
		assertThrows(NullPointerException.class, () -> pokemon.setExclusivity(null));
	}

	@Test
	void testEmptyExclusivity() {
		Pokemon pokemon = new Pokemon("Bidoof", 1, "normal", "WATER", "LEAFGREEN", "location", "description", 1.0, 1.0);
		
		assertThrows(IllegalArgumentException.class, () -> pokemon.setExclusivity(""));
	}
	
	@Test
	void testNotInExclusivityEnum() {
		Pokemon pokemon = new Pokemon("Bidoof", 1, "normal", "WATER", "LEAFGREEN", "location", "description", 1.0, 1.0);
		
		assertThrows(IllegalArgumentException.class, () -> pokemon.setExclusivity("purple"));
	}
	
	@Test
	void testValidExclusivity() {
		Pokemon pokemon = new Pokemon("Bidoof", 1, "normal", "WATER", "LEAFGREEN", "location", "description", 1.0, 1.0);
		
		pokemon.setExclusivity("FIRERED");
		assertEquals("FIRERED", pokemon.getExclusivity());
	}
}
