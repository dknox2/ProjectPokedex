package edu.westga.cs3212.projectpokedex.test.model.pokemon.evolution;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.projectpokedex.model.pokemon.Pokemon;
import edu.westga.cs3212.projectpokedex.model.pokemon.evolution.Evolution;

class TestConstructor {

	@Test
	void testNullPokemon() {
		assertThrows(NullPointerException.class, () -> new Evolution(null, ""));
	}

	@Test
	void testNullEvolutionMethod() {
		assertThrows(NullPointerException.class, () -> new Evolution(new Pokemon("Bidoof", 1, "NORMAL", null, "FIRERED", "location", "description", 1.0, 1.0), null));
	}
	
	@Test
	void testValidConstruction() {
		Pokemon pokemon = new Pokemon("Bidoof", 1, "NORMAL", null, "FIRERED", "location", "description", 1.0, 1.0);
		String evolutionMethod = "level 24";
		
		Evolution evolution = new Evolution(pokemon, evolutionMethod);
		
		assertAll(
			() -> assertEquals(pokemon, evolution.getPokemon()),
			() -> assertEquals(evolutionMethod, evolution.getEvolutionMethod())
		);
	}
}
