package edu.westga.cs3212.projectpokedex.test.model.pokedex;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.projectpokedex.model.Pokedex;
import edu.westga.cs3212.projectpokedex.model.pokemon.Pokemon;

class TestFindPokemonByNumber {

	@Test
	void testNoPokemon() {
		Pokedex pokedex = new Pokedex();
		assertNull(pokedex.findPokemonByNumber(1));
	}
	
	@Test
	void testOnePokemonFound() {
		Pokedex pokedex = new Pokedex();
		Pokemon pokemon = new Pokemon("Bidoof", 1, "NORMAL", null, "FIRERED", "location", "description", 1.0, 1.0);
		pokedex.add(pokemon);
		
		assertEquals(pokemon, pokedex.findPokemonByNumber(1));
	}
	
	@Test
	void testOnePokemonNotFound() {
		Pokedex pokedex = new Pokedex();
		Pokemon pokemon = new Pokemon("Bidoof", 1, "NORMAL", null, "FIRERED", "location", "description", 1.0, 1.0);
		pokedex.add(pokemon);
		
		assertNull(pokedex.findPokemonByNumber(2));
	}
	
	@Test
	void testManyPokemonNotFound() {
		Pokedex pokedex = new Pokedex();
		
		pokedex.add(new Pokemon("Bidoof", 1, "NORMAL", null, "FIRERED", "location", "description", 1.0, 1.0));
		pokedex.add(new Pokemon("Jolteon", 2, "ELECTRIC", null, "FIRERED", "location", "description", 1.0, 1.0));
		pokedex.add(new Pokemon("Absol", 3, "DARK", null, "FIRERED", "location", "description", 1.0, 1.0));
		pokedex.add(new Pokemon("Pikipek", 4, "FLYING", null, "FIRERED", "location", "description", 1.0, 1.0));
		
		assertNull(pokedex.findPokemonByNumber(5));
	}
	
	@Test
	void testManyPokemonFound() {
		Pokedex pokedex = new Pokedex();
		Pokemon toFind = new Pokemon("Absol", 3, "DARK", null, "FIRERED", "location", "description", 1.0, 1.0);
		
		pokedex.add(new Pokemon("Bidoof", 1, "NORMAL", null, "FIRERED", "location", "description", 1.0, 1.0));
		pokedex.add(new Pokemon("Jolteon", 2, "ELECTRIC", null, "FIRERED", "location", "description", 1.0, 1.0));
		pokedex.add(toFind);
		pokedex.add(new Pokemon("Pikipek", 4, "FLYING", null, "FIRERED", "location", "description", 1.0, 1.0));
		
		assertEquals(toFind, pokedex.findPokemonByNumber(3));
	}

}
