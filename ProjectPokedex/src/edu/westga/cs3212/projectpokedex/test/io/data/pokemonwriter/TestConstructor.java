package edu.westga.cs3212.projectpokedex.test.io.data.pokemonwriter;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.projectpokedex.io.data.PokemonWriter;
import edu.westga.cs3212.projectpokedex.model.pokemon.Pokemon;

class TestConstructor {

	@Test
	void testNullPokemon() {
		assertThrows(IllegalArgumentException.class, () -> new PokemonWriter(null));
	}

	@Test
	void testValidConstruction() {
		Pokemon pokemon = new Pokemon("Bidoof", 1, "normal", null, "FIRERED", "location", "description", 1.0, 1.0);
		PokemonWriter writer = new PokemonWriter(pokemon);
		
		String json = writer.getJSONString();
		
		assertEquals("{\"summary\":\"description\",\"type2\":\"NULL\",\"exclusivity\":\"FIRERED\",\"name\":\"Bidoof\",\"weight\":\"1.0\",\"location\":\"location\",\"id\":\"1\",\"type1\":\"NORMAL\",\"moveset\":\"[]\",\"height\":\"1.0\"}", json);
	}
}
