package edu.westga.cs3212.projectpokedex.test.model.pokedex;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.projectpokedex.model.Pokedex;
import edu.westga.cs3212.projectpokedex.model.pokedexitem.PokedexItemComparators;
import edu.westga.cs3212.projectpokedex.model.pokemon.Pokemon;

class TestSort {

	@Test
	void testNullComparator() {
		Pokedex pokedex = new Pokedex();
		assertThrows(NullPointerException.class, () -> pokedex.sort(null));
	}

	@Test
	void testValidComparator() {
		Pokedex pokedex = new Pokedex();
		
		pokedex.add(new Pokemon("Caterpie", 3, "BUG", null, "FIRERED", "location", "description", 1.0, 1.0));
		pokedex.add(new Pokemon("Bidoof", 2, "NORMAL", null, "FIRERED", "location", "description", 1.0, 1.0));
		pokedex.add(new Pokemon("Absol", 1, "DARK", null, "FIRERED", "location", "description", 1.0, 1.0));
		
		pokedex.sort(PokedexItemComparators.COMPARE_BY_NAME);
		
		List<Pokemon> pokemon = pokedex.getPokemon();
		
		assertAll(() -> assertEquals("Absol", pokemon.get(0).getName()),
				  () -> assertEquals("Bidoof", pokemon.get(1).getName()),
				  () -> assertEquals("Caterpie", pokemon.get(2).getName()));
	}
	
	@Test
	void testDefaultSort() {
		Pokedex pokedex = new Pokedex();
		pokedex.add(new Pokemon("Bidoof", 2, "NORMAL", null, "FIRERED", "location", "description", 1.0, 1.0));
		pokedex.add(new Pokemon("Caterpie", 3, "BUG", null, "FIRERED", "location", "description", 1.0, 1.0));
		pokedex.add(new Pokemon("Absol", 1, "DARK", null, "FIRERED", "location", "description", 1.0, 1.0));
		
		pokedex.sort();
		
		List<Pokemon> pokemon = pokedex.getPokemon();
		
		assertAll(() -> assertEquals(1, pokemon.get(0).getPokedexNumber()),
				  () -> assertEquals(2, pokemon.get(1).getPokedexNumber()),
				  () -> assertEquals(3, pokemon.get(2).getPokedexNumber()));
	}
}
