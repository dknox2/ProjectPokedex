package edu.westga.cs3212.projectpokedex.test.model.pokedexitemcomparators;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.projectpokedex.model.pokedexitem.PokedexItemComparators;
class TestConstructor {
	
	@SuppressWarnings("static-access")
	@Test
	void testConstructor() {
		PokedexItemComparators comparators = new PokedexItemComparators();
		
		assertNotNull(comparators.COMPARE_BY_INTERNAL_ID);
	}
	
}
