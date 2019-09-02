package edu.westga.cs3212.projectpokedex.test.model.pokedex;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.projectpokedex.model.Pokedex;

public class TestGetters {

	@Test
	void testcannotAddNullPokemon() {
		Pokedex pokedex = new Pokedex();
		
		assertNotEquals(null, pokedex.iterator());
	}
	
	
}
