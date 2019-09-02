package edu.westga.cs3212.projectpokedex.test.model.pokemon;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.projectpokedex.model.pokemon.Pokemon;

class TestSetSecondaryType {

	@Test
	void testNullSecondaryType() {
		Pokemon pokemon = new Pokemon("Bidoof", 1, "normal", "WATER", "LEAFGREEN", "location", "description", 1.0, 1.0);
		
		pokemon.setSecondaryType(null);
		assertNull(pokemon.getSecondaryType());
	}
	
	@Test
	void testEmptySecondaryType() {
		Pokemon pokemon = new Pokemon("Bidoof", 1, "normal", "WATER", "LEAFGREEN", "location", "description", 1.0, 1.0);
		
		assertThrows(IllegalArgumentException.class, () -> pokemon.setSecondaryType(""));
	}
	
	@Test
	void testValidNonNullSecondaryType() {
		Pokemon pokemon = new Pokemon("Bidoof", 1, "normal", "WATER", "LEAFGREEN", "location", "description", 1.0, 1.0);
		
		pokemon.setSecondaryType("dragon");
		assertEquals("DRAGON", pokemon.getSecondaryType());
	}
}
