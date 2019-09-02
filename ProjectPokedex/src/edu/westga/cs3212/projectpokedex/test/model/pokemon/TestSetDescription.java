package edu.westga.cs3212.projectpokedex.test.model.pokemon;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.projectpokedex.model.pokemon.Pokemon;

class TestSetDescription {

	@Test
	void testNullDescription() {
		Pokemon pokemon = new Pokemon("Bidoof", 1, "normal", "WATER", "LEAFGREEN", "location", "description", 1.0, 1.0);
		
		assertThrows(NullPointerException.class, () -> pokemon.setDescription(null));
	}
	
	@Test
	void testValidDescription() {
		Pokemon pokemon = new Pokemon("Bidoof", 1, "normal", "WATER", "LEAFGREEN", "location", "description", 1.0, 1.0);
		
		pokemon.setDescription("pokeman");
		
		assertEquals("pokeman", pokemon.getDescription());
	}

}
