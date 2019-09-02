package edu.westga.cs3212.projectpokedex.test.model.pokedex;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.projectpokedex.model.Pokedex;
import edu.westga.cs3212.projectpokedex.model.pokemon.Pokemon;

class TestReverse {

	@Test
	void testReverse() {
		Pokedex pokedex = new Pokedex();
		
		Pokemon pokemon1 = new Pokemon("Bidoof", 1, "normal", null, "FIRERED", "location", "description", 1.0, 1.0);
		Pokemon pokemon2 = new Pokemon("Bidoof", 2, "normal", null, "FIRERED", "location", "description", 1.0, 1.0);
				
		pokedex.add(pokemon2);
		pokedex.add(pokemon1);
		
		pokedex.sort();
		pokedex.reverse();
		
		List<Pokemon> pokemon = pokedex.getPokemon();
		
		assertAll(
			() -> assertEquals(2, pokemon.get(0).getPokedexNumber()),
			() -> assertEquals(1, pokemon.get(1).getPokedexNumber())
		);
	}

}
