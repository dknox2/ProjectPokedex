package edu.westga.cs3212.projectpokedex.test.model.pokedexitemcomparators;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Comparator;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.projectpokedex.model.pokedexitem.PokedexItem;
import edu.westga.cs3212.projectpokedex.model.pokedexitem.PokedexItemComparators;
import edu.westga.cs3212.projectpokedex.model.pokemon.Pokemon;

class TestCompareByPrimaryType {

	@Test
	void testPokemon1LessThanPokemon2() {
		Pokemon pokemon1 = new Pokemon("Absol", 1, "BUG", null, "FIRERED", "location", "description", 1.0, 1.0);
		PokedexItem pokedexItem1 = new PokedexItem(pokemon1);
		
		Pokemon pokemon2 = new Pokemon("Bidoof", 1, "STEEL", null, "LEAFGREEN", "location", "description", 1.0, 1.0);
		PokedexItem pokedexItem2 = new PokedexItem(pokemon2);
		
		Comparator<PokedexItem> comparator = PokedexItemComparators.COMPARE_BY_NAME;
		assertEquals(-1, comparator.compare(pokedexItem1, pokedexItem2));
	}
	
	@Test
	void testPokemon1GreaterThanPokemmon2() {
		Pokemon pokemon1 = new Pokemon("Bidoof", 1, "WATER", null, "FIRERED", "location", "description", 1.0, 1.0);
		PokedexItem pokedexItem1 = new PokedexItem(pokemon1);
		
		Pokemon pokemon2 = new Pokemon("Absol", 1, "NORMAL", null, "LEAFGREEN", "location", "description", 1.0, 1.0);
		PokedexItem pokedexItem2 = new PokedexItem(pokemon2);
		
		Comparator<PokedexItem> comparator = PokedexItemComparators.COMPARE_BY_PRIMARY_TYPE;
		assertEquals(9, comparator.compare(pokedexItem1, pokedexItem2));
	}
	
	@Test
	void testPokemonEqual() {
		Pokemon pokemon1 = new Pokemon("Bidoof", 1, "NORMAL", null, "FIRERED", "location", "description", 1.0, 1.0);
		PokedexItem pokedexItem1 = new PokedexItem(pokemon1);
		
		Pokemon pokemon2 = new Pokemon("Bidoof", 1, "NORMAL", null, "LEAFGREEN", "location", "description", 1.0, 1.0);
		PokedexItem pokedexItem2 = new PokedexItem(pokemon2);
		
		Comparator<PokedexItem> comparator = PokedexItemComparators.COMPARE_BY_PRIMARY_TYPE;
		assertEquals(0, comparator.compare(pokedexItem1, pokedexItem2));
	}
}
