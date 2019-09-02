package edu.westga.cs3212.projectpokedex.test.model.pokemon;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.projectpokedex.model.pokemon.Pokemon;
import edu.westga.cs3212.projectpokedex.test.TestConstants;

class TestSetHeight {

	@Test
	void testHeightLessThan0() {
		Pokemon pokemon = new Pokemon("Bidoof", 1, "normal", "WATER", "LEAFGREEN", "location", "description", 1.0, 1.0);
		
		assertThrows(IllegalArgumentException.class, () -> pokemon.setHeight(-2.4));
	}
	
	@Test
	void testHeight0() {
		Pokemon pokemon = new Pokemon("Bidoof", 1, "normal", "WATER", "LEAFGREEN", "location", "description", 1.0, 1.0);
		
		assertThrows(IllegalArgumentException.class, () -> pokemon.setHeight(0.0));
	}
	
	@Test
	void testHeightGreaterThan0() {
		Pokemon pokemon = new Pokemon("Bidoof", 1, "normal", "WATER", "LEAFGREEN", "location", "description", 1.0, 1.0);
		
		pokemon.setHeight(4.6);
		assertEquals(4.6, pokemon.getHeight(), TestConstants.DELTA);
	}

}
