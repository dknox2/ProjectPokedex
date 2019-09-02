package edu.westga.cs3212.projectpokedex.test.io.data.pokemonreader;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.projectpokedex.io.data.PokemonReader;
import edu.westga.cs3212.projectpokedex.model.pokemon.Pokemon;

class TestConstructPokemon {

	@Test
	void testValueNotInJSON() {
		assertThrows(IllegalArgumentException.class, () ->new PokemonReader("{\"type2\": \"poison\", \"weight\": \"4.0\", \"height\": \"0.7\", \"name\": \"bellsprout\", \"location\": \"Route 5,6,7,12,13,14,15,24,25,Berry Forest,Bond Bridge,Cape Brink,Water Path,Trade/migrate from another game\", \"summary\": \"Its bud looks like a human face. Because of the bud, it is rumored to be a type of legendary mandrake plant.\", \"exclusivity\": \"LEAFGREEN\", \"id\": \"69\"}"));
	}

	@Test
	void testJSONWithMoveset() {
		PokemonReader reader = new PokemonReader("{\"type1\": \"psychic\", \"type2\": \"NULL\", \"weight\": \"4.0\", \"height\": \"0.4\", \"name\": \"mew\", \"location\": \"Trade/migrate from another game\", \"summary\": \"A POK\\u00e9MON of South America that was thought to have been extinct. It is very intelligent and learns any move.\", \"exclusivity\": \"BOTH\", \"id\": \"151\", \"moveset\": [{\"level\": 1, \"name\": \"Pound\", \"type\": \"NORMAL\", \"category\": \"PHYSICAL\", \"power\": 40, \"accuracy\": 100, \"pp\": 35}, {\"level\": 1, \"name\": \"Reflect Type\", \"type\": \"NORMAL\", \"category\": \"STATUS\", \"power\": 0, \"accuracy\": 100, \"pp\": 15}, {\"level\": 1, \"name\": \"Transform\", \"type\": \"NORMAL\", \"category\": \"STATUS\", \"power\": 0, \"accuracy\": 100, \"pp\": 10}, {\"level\": 10, \"name\": \"Mega Punch\", \"type\": \"NORMAL\", \"category\": \"PHYSICAL\", \"power\": 80, \"accuracy\": 85, \"pp\": 20}, {\"level\": 20, \"name\": \"Metronome\", \"type\": \"NORMAL\", \"category\": \"STATUS\", \"power\": 0, \"accuracy\": 100, \"pp\": 10}, {\"level\": 30, \"name\": \"Psychic\", \"type\": \"PSYCHIC\", \"category\": \"SPECIAL\", \"power\": 90, \"accuracy\": 100, \"pp\": 10}, {\"level\": 40, \"name\": \"Barrier\", \"type\": \"PSYCHIC\", \"category\": \"STATUS\", \"power\": 0, \"accuracy\": 100, \"pp\": 20}, {\"level\": 50, \"name\": \"Ancient Power\", \"type\": \"ROCK\", \"category\": \"SPECIAL\", \"power\": 60, \"accuracy\": 100, \"pp\": 5}, {\"level\": 60, \"name\": \"Amnesia\", \"type\": \"PSYCHIC\", \"category\": \"STATUS\", \"power\": 0, \"accuracy\": 100, \"pp\": 20}, {\"level\": 70, \"name\": \"Me First\", \"type\": \"NORMAL\", \"category\": \"STATUS\", \"power\": 0, \"accuracy\": 100, \"pp\": 20}, {\"level\": 80, \"name\": \"Baton Pass\", \"type\": \"NORMAL\", \"category\": \"STATUS\", \"power\": 0, \"accuracy\": 100, \"pp\": 40}, {\"level\": 90, \"name\": \"Nasty Plot\", \"type\": \"DARK\", \"category\": \"STATUS\", \"power\": 0, \"accuracy\": 100, \"pp\": 20}, {\"level\": 100, \"name\": \"Aura Sphere\", \"type\": \"FIGHTING\", \"category\": \"SPECIAL\", \"power\": 80, \"accuracy\": 100, \"pp\": 20}]}");
		
		Pokemon pokemon = reader.getPokemon();
		
		assertAll(
			() -> assertEquals("mew", pokemon.getName()),
			() -> assertEquals(13, pokemon.getLevelUpMoveset().size())
		);
	}
}
