package edu.westga.cs3212.projectpokedex.test.model.pokedexitemcomparators;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Comparator;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.projectpokedex.model.pokedexitem.PokedexItem;
import edu.westga.cs3212.projectpokedex.model.pokedexitem.PokedexItemComparators;
import edu.westga.cs3212.projectpokedex.model.pokemon.Pokemon;

class TestCompareByPokedexNumber {

	@Test
	void testPokemon1LessThanPokemon2() {
		Pokemon pokemon1 = new Pokemon("Absol", 1, "NORMAL", null, "FIRERED", "location", "description", 1.0, 1.0);
		PokedexItem pokedexItem1 = new PokedexItem(pokemon1);
		
		Pokemon pokemon2 = new Pokemon("Bidoof", 30, "NORMAL", null, "LEAFGREEN", "location", "description", 13.66, 1.0);
		PokedexItem pokedexItem2 = new PokedexItem(pokemon2);
		
		Comparator<PokedexItem> comparator = PokedexItemComparators.COMPARE_BY_POKEDEX_NUMBER;
		assertEquals(-1, comparator.compare(pokedexItem1, pokedexItem2));
	}
	
	@Test
	void testPokemon1GreaterThanPokemmon2() {
		Pokemon pokemon1 = new Pokemon("Bidoof", 91, "NORMAL", null, "FIRERED", "location", "description", 123.5, 1.0);
		PokedexItem pokedexItem1 = new PokedexItem(pokemon1);
		
		Pokemon pokemon2 = new Pokemon("Absol", 12, "NORMAL", null, "LEAFGREEN", "location", "description", 50, 1.0);
		PokedexItem pokedexItem2 = new PokedexItem(pokemon2);
		
		Comparator<PokedexItem> comparator = PokedexItemComparators.COMPARE_BY_POKEDEX_NUMBER;
		assertEquals(1, comparator.compare(pokedexItem1, pokedexItem2));
	}
	
	@Test
	void testPokemonEqual() {
		Pokemon pokemon1 = new Pokemon("Bidoof", 151, "NORMAL", null, "FIRERED", "location", "description", 80.511, 1.0);
		PokedexItem pokedexItem1 = new PokedexItem(pokemon1);
		
		Pokemon pokemon2 = new Pokemon("Bidoof", 151, "NORMAL", null, "LEAFGREEN", "location", "description", 80.511, 1.0);
		PokedexItem pokedexItem2 = new PokedexItem(pokemon2);
		
		Comparator<PokedexItem> comparator = PokedexItemComparators.COMPARE_BY_POKEDEX_NUMBER;
		assertEquals(0, comparator.compare(pokedexItem1, pokedexItem2));
	}
}
