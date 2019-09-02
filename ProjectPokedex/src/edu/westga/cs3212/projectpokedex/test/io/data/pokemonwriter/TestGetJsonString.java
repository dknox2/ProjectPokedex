package edu.westga.cs3212.projectpokedex.test.io.data.pokemonwriter;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.projectpokedex.io.data.PokemonWriter;
import edu.westga.cs3212.projectpokedex.model.pokemon.Pokemon;

public class TestGetJsonString {
	
	@Test
	void testPokemon1LessThanPokemon2() {
		Pokemon pokemon1 = new Pokemon("Bidoof", 1, "NORMAL", null, "LEAFGREEN", "location", "description", 1.0, 12.5);
		
		PokemonWriter writer = new PokemonWriter(pokemon1);
		
		assertEquals("{\"summary\":\"description\",\"type2\":\"NULL\",\"exclusivity\":\"LEAFGREEN\",\"name\":\"Bidoof\",\"weight\":\"12.5\",\"location\":\"location\",\"id\":\"1\",\"type1\":\"NORMAL\",\"moveset\":\"[]\",\"height\":\"1.0\"}", writer.getJSONString());
	}
	
}
