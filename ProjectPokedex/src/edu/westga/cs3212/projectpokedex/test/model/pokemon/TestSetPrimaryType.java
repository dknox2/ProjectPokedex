package edu.westga.cs3212.projectpokedex.test.model.pokemon;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.projectpokedex.model.pokemon.Pokemon;

class TestSetPrimaryType {

	@Test
	void testNullPrimaryType() {
		Pokemon pokemon = new Pokemon("Bidoof", 1, "normal", "WATER", "LEAFGREEN", "location", "description", 1.0, 1.0);
		
		assertThrows(NullPointerException.class, () -> pokemon.setPrimaryType(null));
	}

	@Test
	void testEmptyStringPrimaryType() {
		Pokemon pokemon = new Pokemon("Bidoof", 1, "normal", "WATER", "LEAFGREEN", "location", "description", 1.0, 1.0);
		
		assertThrows(IllegalArgumentException.class, () -> pokemon.setPrimaryType(""));
	}
	
	@Test
	void testTypeNotInTypeEnum() {
		Pokemon pokemon = new Pokemon("Bidoof", 1, "normal", "WATER", "LEAFGREEN", "location", "description", 1.0, 1.0);
		
		assertThrows(IllegalArgumentException.class, () -> pokemon.setPrimaryType("burple"));
	}
	
	@Test
	void testValidPrimaryType() {
		Pokemon pokemon = new Pokemon("Bidoof", 1, "normal", "WATER", "LEAFGREEN", "location", "description", 1.0, 1.0);
		
		pokemon.setPrimaryType("FIRE");
		assertEquals("FIRE", pokemon.getPrimaryType());
	}
}
