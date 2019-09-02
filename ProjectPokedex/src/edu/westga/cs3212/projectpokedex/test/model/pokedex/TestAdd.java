package edu.westga.cs3212.projectpokedex.test.model.pokedex;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.projectpokedex.model.Pokedex;
import edu.westga.cs3212.projectpokedex.model.pokemon.Pokemon;

class TestAdd {

	@Test
	void testcannotAddNullPokemon() {
		Pokedex pokedex = new Pokedex();
		
		assertThrows(NullPointerException.class, () -> pokedex.add(null));
	}
	
	@Test
	void testPokedexNumberAlreadyExists() {
		Pokedex pokedex = new Pokedex();
		pokedex.add(new Pokemon("Bidoof", 1, "NORMAL", null, "FIRERED", "location", "description", 1.0, 1.0));
		
		assertThrows(IllegalArgumentException.class, () -> pokedex.add(new Pokemon("Jolteon", 1, "WATER", "FIRE", "BOTH", "location", "description", 0.4, 1000.3)));
	}
	
	@Test
	void testAddPokemonOfDifferentNumbers() {
		Pokedex pokedex = new Pokedex();
		pokedex.add(new Pokemon("Bidoof", 1, "NORMAL", null, "FIRERED", "location", "description", 1.0, 1.0));
		pokedex.add(new Pokemon("Jolteon", 2, "ELECTRIC", null, "FIRERED", "location", "description", 1.0, 1.0));
		pokedex.add(new Pokemon("Absol", 3, "DARK", null, "FIRERED", "location", "description", 1.0, 1.0));
		pokedex.add(new Pokemon("Pikipek", 4, "FLYING", null, "FIRERED", "location", "description", 1.0, 1.0));
		
		assertEquals(4, pokedex.getPokemon().size());
	}

}
