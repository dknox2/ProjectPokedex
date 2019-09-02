package edu.westga.cs3212.projectpokedex.test.model.pokedexitem;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.projectpokedex.model.pokedexitem.PokedexItem;
import edu.westga.cs3212.projectpokedex.model.pokemon.Pokemon;

class TestConstructor {

	@Test
	void testNullPokemon() {
		assertThrows(NullPointerException.class, () -> new PokedexItem(null));
	}

	@Test
	void testvalidConstructor() {
		Pokemon pokemon = new Pokemon("Bidoof", 1, "normal", "WATER", "LEAFGREEN", "location", "description", 1.0, 1.0);
		PokedexItem pokedexItem = new PokedexItem(pokemon);
		
		assertEquals(pokemon, pokedexItem.getPokemon());
	}
}
