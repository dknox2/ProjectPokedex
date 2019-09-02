package edu.westga.cs3212.projectpokedex.test.io.data.pokemonreader;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.projectpokedex.io.data.PokemonReader;
import edu.westga.cs3212.projectpokedex.model.pokemon.Pokemon;

class TestConstructor {

	@Test
	void testNullJSON() {
		assertThrows(IllegalArgumentException.class, () -> new PokemonReader(null));
	}

	@Test
	void testInvalidJSON() {
		assertThrows(IllegalArgumentException.class, () -> new PokemonReader("invalid"));
	}

	@Test
	void testValidJSON() {
		PokemonReader reader = new PokemonReader(
				"{\"type1\": \"grass\", \"type2\": \"poison\", \"weight\": \"4.0\", \"height\": \"0.7\", \"name\": \"bellsprout\", \"location\": \"Route 5,6,7,12,13,14,15,24,25,Berry Forest,Bond Bridge,Cape Brink,Water Path,Trade/migrate from another game\", \"summary\": \"Its bud looks like a human face. Because of the bud, it is rumored to be a type of legendary mandrake plant.\", \"exclusivity\": \"LEAFGREEN\", \"id\": \"69\"}");
		
		Pokemon pokemon = reader.getPokemon();
		
		assertEquals("bellsprout", pokemon.getName());
	}
	
	@Test
	void testNullError() {
		
		assertThrows(IllegalArgumentException.class,()->{ new PokemonReader(null);});
		
	}
	
	@Test
	void testMalformattedJson() {
		
		assertThrows(IllegalArgumentException.class,()->{ new PokemonReader("{\"data\":\"KFC}");});
		
	}
	
	@Test
	void testErrorMissingPokemonData() {
		
		assertThrows(IllegalArgumentException.class,()->{ new PokemonReader("{\"type1\": \"psychic\", \"type2\": \"NULL\", \"weight\": \"19.5\", \"height\": \"0.9\", \"name\": \"abra\", \"location\": \"Route 24,25,Celadon City\", \"summary\": \"It sleeps for 18 hours a day. It uses a variety of extrasensory powers even while asleep.\"}");});
		
	}
	
	@Test
	void testValidPokemonDataNullType2() {
		
		PokemonReader reader = new PokemonReader("{\"type1\": \"psychic\", \"type2\": \"NULL\", \"weight\": \"19.5\", \"height\": \"0.9\", \"name\": \"abra\", \"location\": \"Route 24,25,Celadon City\", \"summary\": \"It sleeps for 18 hours a day. It uses a variety of extrasensory powers even while asleep.\", \"exclusivity\": \"BOTH\", \"id\": \"63\"}");
		
		assertNotNull(reader.getPokemon());
		
	}
	
	
	@Test
	void testValidPokemonDataNonNullType2() {
		
		PokemonReader reader = new PokemonReader("{\"type1\": \"psychic\", \"type2\": \"grass\", \"weight\": \"19.5\", \"height\": \"0.9\", \"name\": \"abra\", \"location\": \"Route 24,25,Celadon City\", \"summary\": \"It sleeps for 18 hours a day. It uses a variety of extrasensory powers even while asleep.\", \"exclusivity\": \"BOTH\", \"id\": \"63\"}");
		
		assertNotNull(reader.getPokemon());
		
	}
	
	
	@Test
	void testValidPokemonWithMoveset() {
		
		PokemonReader reader = new PokemonReader("{\"type1\": \"psychic\", \"type2\": \"NULL\", \"weight\": \"19.5\", \"height\": \"0.9\", \"name\": \"abra\", \"location\": \"Route 24,25,Celadon City\", \"summary\": \"It sleeps for 18 hours a day. It uses a variety of extrasensory powers even while asleep.\", \"exclusivity\": \"BOTH\", \"id\": \"63\", \"moveset\":[{\"level\": 1, \"name\": \"Tackle\", \"type\": \"NORMAL\", \"category\": \"PHYSICAL\", \"power\": 40, \"accuracy\": 100, \"pp\": 35}, {\"level\": 3, \"name\": \"Growl\", \"type\": \"NORMAL\", \"category\": \"STATUS\", \"power\": 0, \"accuracy\": 100, \"pp\": 40}, {\"level\": 7, \"name\": \"Leech Seed\", \"type\": \"GRASS\", \"category\": \"STATUS\", \"power\": 0, \"accuracy\": 90, \"pp\": 10}, {\"level\": 9, \"name\": \"Vine Whip\", \"type\": \"GRASS\", \"category\": \"PHYSICAL\", \"power\": 45, \"accuracy\": 100, \"pp\": 25}, {\"level\": 13, \"name\": \"Poison Powder\", \"type\": \"POISON\", \"category\": \"STATUS\", \"power\": 0, \"accuracy\": 75, \"pp\": 35}, {\"level\": 13, \"name\": \"Sleep Powder\", \"type\": \"GRASS\", \"category\": \"STATUS\", \"power\": 0, \"accuracy\": 75, \"pp\": 15}, {\"level\": 15, \"name\": \"Take Down\", \"type\": \"NORMAL\", \"category\": \"PHYSICAL\", \"power\": 90, \"accuracy\": 85, \"pp\": 20}, {\"level\": 19, \"name\": \"Razor Leaf\", \"type\": \"GRASS\", \"category\": \"PHYSICAL\", \"power\": 55, \"accuracy\": 95, \"pp\": 25}, {\"level\": 21, \"name\": \"Sweet Scent\", \"type\": \"NORMAL\", \"category\": \"STATUS\", \"power\": 0, \"accuracy\": 100, \"pp\": 20}, {\"level\": 25, \"name\": \"Growth\", \"type\": \"NORMAL\", \"category\": \"STATUS\", \"power\": 0, \"accuracy\": 100, \"pp\": 20}, {\"level\": 27, \"name\": \"Double-Edge\", \"type\": \"NORMAL\", \"category\": \"PHYSICAL\", \"power\": 120, \"accuracy\": 100, \"pp\": 15}, {\"level\": 31, \"name\": \"Worry Seed\", \"type\": \"GRASS\", \"category\": \"STATUS\", \"power\": 0, \"accuracy\": 100, \"pp\": 10}, {\"level\": 33, \"name\": \"Synthesis\", \"type\": \"GRASS\", \"category\": \"STATUS\", \"power\": 0, \"accuracy\": 100, \"pp\": 5}, {\"level\": 37, \"name\": \"Seed Bomb\", \"type\": \"GRASS\", \"category\": \"PHYSICAL\", \"power\": 80, \"accuracy\": 100, \"pp\": 15}]}");
		
		assertNotNull(reader.getPokemon());
		
	}
	
}
