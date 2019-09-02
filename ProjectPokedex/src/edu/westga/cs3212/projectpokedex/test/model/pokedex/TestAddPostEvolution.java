package edu.westga.cs3212.projectpokedex.test.model.pokedex;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.projectpokedex.model.Pokedex;
import edu.westga.cs3212.projectpokedex.model.pokemon.Pokemon;
import edu.westga.cs3212.projectpokedex.model.pokemon.evolution.Evolution;

class TestAddPostEvolution {

	@Test
	void testEvolutionNull() {
		Pokedex pokedex = new Pokedex();
		
		assertThrows(NullPointerException.class, () -> {pokedex.addPostEvolution(1, null); });
	}

	@Test
	void testPokedexNumberNotFound() {
		Pokedex pokedex = new Pokedex();
		
		assertFalse(pokedex.addPostEvolution(1, new Evolution( new Pokemon("Bidoof", 1, "NORMAL", null, "FIRERED", "location", "description", 1.0, 1.0), "Level 10")));
	}
	
	@Test
	void testValidAddPostEvolution() {
		Pokedex pokedex = new Pokedex();
		Pokemon preEvolution = new Pokemon("Bidoof", 1, "NORMAL", null, "FIRERED", "location", "description", 1.0, 1.0);
		Pokemon postEvolution = new Pokemon("Bibarrel", 2, "NORMAL", "WATER", "FIRERED", "location", "description", 1.0, 1.0);
		
		pokedex.add(preEvolution);
		pokedex.add(postEvolution);
		
		Evolution evolution = new Evolution(postEvolution, "Level 24");
		pokedex.addPostEvolution(1, evolution);
		
		assertEquals(1, preEvolution.getPostEvolutions().size());
		assertEquals(postEvolution.getPreviousEvolution().getPokemon(), preEvolution);
	}
}
