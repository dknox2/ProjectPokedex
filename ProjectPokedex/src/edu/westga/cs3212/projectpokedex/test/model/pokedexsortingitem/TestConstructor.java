package edu.westga.cs3212.projectpokedex.test.model.pokedexsortingitem;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.projectpokedex.model.pokedexitem.PokedexItemComparators;
import edu.westga.cs3212.projectpokedex.view.PokedexSortingItem;

class TestConstructor {

	@Test
	void testValidConstructor() {
		PokedexSortingItem sortingItem = new PokedexSortingItem("Test", PokedexItemComparators.COMPARE_BY_POKEDEX_NUMBER);
		
		assertEquals("Test", sortingItem.getDisplayName());
		assertEquals(PokedexItemComparators.COMPARE_BY_POKEDEX_NUMBER, sortingItem.getComparator());
	}

}
