package edu.westga.cs3212.projectpokedex.test.io.database.pokedexdatabaseclient;

import java.util.LinkedList;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.projectpokedex.io.database.PokedexDatabaseClient;
import edu.westga.cs3212.projectpokedex.model.pokemon.Pokemon;

public class TestConstructor {

	@Test
	void testValidConstructorSingle() {
		
		PokedexDatabaseClient cli = new PokedexDatabaseClient(new LinkedList<Pokemon>());
		
	}
	
	
}
