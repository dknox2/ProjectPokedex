package edu.westga.cs3212.projectpokedex.test.model.pokedexsortingitem;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.projectpokedex.model.pokedexitem.PokedexItemComparators;
import edu.westga.cs3212.projectpokedex.view.PokedexSortingItem;

class TestToString {

	@Test
	void testToStringNullDisplayName() {
		PokedexSortingItem sortingItem = new PokedexSortingItem(null, PokedexItemComparators.COMPARE_BY_POKEDEX_NUMBER);
		
		assertEquals("", sortingItem.toString());
	}
	
	@Test
	void testToStringNonNullDisplayName() {
		PokedexSortingItem sortingItem = new PokedexSortingItem("Test", PokedexItemComparators.COMPARE_BY_POKEDEX_NUMBER);
		
		assertEquals("Test", sortingItem.toString());
	}

}
