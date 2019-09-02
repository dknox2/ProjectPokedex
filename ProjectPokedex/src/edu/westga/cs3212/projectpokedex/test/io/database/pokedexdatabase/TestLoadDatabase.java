package edu.westga.cs3212.projectpokedex.test.io.database.pokedexdatabase;

import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.projectpokedex.io.database.PokemonDatabase;

public class TestLoadDatabase {

	@Test
	void testValidConstructor() {
		
		PokemonDatabase db = new PokemonDatabase();
		assertNotNull(db.loadDatabase());
	}
	
}
