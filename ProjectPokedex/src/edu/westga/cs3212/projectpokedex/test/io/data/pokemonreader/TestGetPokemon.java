package edu.westga.cs3212.projectpokedex.test.io.data.pokemonreader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.projectpokedex.io.data.PokemonReader;
import edu.westga.cs3212.projectpokedex.model.pokemon.Pokemon;

public class TestGetPokemon {

	@Test
	void testPokemonDataNullType2() {
		
		PokemonReader reader = new PokemonReader("{\"type1\": \"psychic\", \"type2\": \"NULL\", \"weight\": \"19.5\", \"height\": \"0.9\", \"name\": \"abra\", \"location\": \"Route 24,25,Celadon City\", \"summary\": \"It sleeps for 18 hours a day. It uses a variety of extrasensory powers even while asleep.\", \"exclusivity\": \"BOTH\", \"id\": \"63\"}");
		
		Pokemon poke = reader.getPokemon();
		
		assertEquals("PSYCHIC",poke.getPrimaryType());
		assertEquals(null,poke.getSecondaryType());
		assertEquals("BOTH",poke.getExclusivity());
		assertEquals(0.9,poke.getHeight(),0.1);
		assertEquals(19.5,poke.getWeight(),0.1);
		assertEquals("abra",poke.getName());
		assertEquals("Route 24,25,Celadon City",poke.getLocation());
		assertEquals("It sleeps for 18 hours a day. It uses a variety of extrasensory powers even while asleep.",poke.getDescription());
		assertEquals(63,poke.getPokedexNumber());
		
	}
	
	@Test
	void testPokemonDataNotNullType2() {
		
		PokemonReader reader = new PokemonReader("{\"type1\": \"psychic\", \"type2\": \"grass\", \"weight\": \"19.5\", \"height\": \"0.9\", \"name\": \"abra\", \"location\": \"Route 24,25,Celadon City\", \"summary\": \"It sleeps for 18 hours a day. It uses a variety of extrasensory powers even while asleep.\", \"exclusivity\": \"BOTH\", \"id\": \"63\"}");
		
		Pokemon poke = reader.getPokemon();
		
		assertEquals("PSYCHIC",poke.getPrimaryType());
		assertEquals("GRASS",poke.getSecondaryType());
		assertEquals("BOTH",poke.getExclusivity());
		assertEquals(0.9,poke.getHeight(),0.1);
		assertEquals(19.5,poke.getWeight(),0.1);
		assertEquals("abra",poke.getName());
		assertEquals("Route 24,25,Celadon City",poke.getLocation());
		assertEquals("It sleeps for 18 hours a day. It uses a variety of extrasensory powers even while asleep.",poke.getDescription());
		assertEquals(63,poke.getPokedexNumber());
		
	}
}
