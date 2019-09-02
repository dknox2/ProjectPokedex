package edu.westga.cs3212.projectpokedex.test.model.pokedexitemcomparators;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Comparator;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.projectpokedex.model.pokedexitem.PokedexItem;
import edu.westga.cs3212.projectpokedex.model.pokedexitem.PokedexItemComparators;
import edu.westga.cs3212.projectpokedex.model.pokemon.Pokemon;

class TestCompareBySecondaryType {

	@Test
	void testPokemon1LessThanPokemon2() {
		Pokemon pokemon1 = new Pokemon("Absol", 1, "NORMAL", "DARK", "FIRERED", "location", "description", 1.0, 1.0);
		PokedexItem pokedexItem1 = new PokedexItem(pokemon1);
		
		Pokemon pokemon2 = new Pokemon("Bidoof", 1, "NORMAL", "ELECTRIC", "LEAFGREEN", "location", "description", 1.0, 1.0);
		PokedexItem pokedexItem2 = new PokedexItem(pokemon2);
		
		Comparator<PokedexItem> comparator = PokedexItemComparators.COMPARE_BY_SECONDARY_TYPE;
		assertEquals(-1, comparator.compare(pokedexItem1, pokedexItem2));
	}
	
	@Test
	void testtPokemon1SecondaryTypeNull() {
		Pokemon pokemon1 = new Pokemon("Bidoof", 1, "NORMAL", null, "FIRERED", "location", "description", 1.0, 1.0);
		PokedexItem pokedexItem1 = new PokedexItem(pokemon1);
		
		Pokemon pokemon2 = new Pokemon("Absol", 1, "NORMAL", "FIRE", "LEAFGREEN", "location", "description", 1.0, 1.0);
		PokedexItem pokedexItem2 = new PokedexItem(pokemon2);
		
		Comparator<PokedexItem> comparator = PokedexItemComparators.COMPARE_BY_SECONDARY_TYPE;
		assertEquals(Integer.MIN_VALUE, comparator.compare(pokedexItem1, pokedexItem2));
	}
	
	@Test
	void testPokemon2SecondaryTypeNull() {
		Pokemon pokemon1 = new Pokemon("Bidoof", 1, "NORMAL", "WATER", "FIRERED", "location", "description", 1.0, 1.0);
		PokedexItem pokedexItem1 = new PokedexItem(pokemon1);
		
		Pokemon pokemon2 = new Pokemon("Absol", 1, "NORMAL", null, "LEAFGREEN", "location", "description", 1.0, 1.0);
		PokedexItem pokedexItem2 = new PokedexItem(pokemon2);
		
		Comparator<PokedexItem> comparator = PokedexItemComparators.COMPARE_BY_SECONDARY_TYPE;
		assertEquals(Integer.MAX_VALUE, comparator.compare(pokedexItem1, pokedexItem2));
	}
	
	@Test
	void testPokemonSecondaryTypeBothNull() {
		Pokemon pokemon1 = new Pokemon("Bidoof", 1, "NORMAL", null, "FIRERED", "location", "description", 1.0, 1.0);
		PokedexItem pokedexItem1 = new PokedexItem(pokemon1);
		
		Pokemon pokemon2 = new Pokemon("Bidoof", 1, "NORMAL", null, "LEAFGREEN", "location", "description", 1.0, 1.0);
		PokedexItem pokedexItem2 = new PokedexItem(pokemon2);
		
		Comparator<PokedexItem> comparator = PokedexItemComparators.COMPARE_BY_SECONDARY_TYPE;
		assertEquals(0, comparator.compare(pokedexItem1, pokedexItem2));
	}

}
