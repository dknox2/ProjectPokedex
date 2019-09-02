package edu.westga.cs3212.projectpokedex.test.io.database.pokedexdatabase;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.projectpokedex.io.data.PokemonReader;
import edu.westga.cs3212.projectpokedex.io.database.PokemonDatabase;

public class TestConstructor {

	@Test
	void testValidConstructor() {
		
		PokemonDatabase db = new PokemonDatabase();
	}
	
}
