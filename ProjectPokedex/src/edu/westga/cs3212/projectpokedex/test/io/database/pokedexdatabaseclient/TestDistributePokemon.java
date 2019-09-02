package edu.westga.cs3212.projectpokedex.test.io.database.pokedexdatabaseclient;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.projectpokedex.io.database.PokemonDatabase;
import edu.westga.cs3212.projectpokedex.model.pokemon.Pokemon;

public class TestDistributePokemon {

	@Test
	void testDistributeValid() {
		
		PokemonDatabase db = new PokemonDatabase();
		Pokemon pokemon1 = new Pokemon("Bidoof", 1, "NORMAL", null, "LEAFGREEN", "location", "description", 1.0, 12.5);
		assertFalse(db.distributeNewPokemon(pokemon1));
		
	}
	
	@Test
	void testDistributeInvalid() {
		
		PokemonDatabase db = new PokemonDatabase();
		assertFalse(db.distributeNewPokemon(null));
		
	}
	
}
