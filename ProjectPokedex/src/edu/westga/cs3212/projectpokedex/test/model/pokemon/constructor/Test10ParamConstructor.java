package edu.westga.cs3212.projectpokedex.test.model.pokemon.constructor;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.projectpokedex.model.pokemon.Pokemon;
import edu.westga.cs3212.projectpokedex.model.pokemon.moves.LevelUpMove;
import edu.westga.cs3212.projectpokedex.test.TestConstants;

class Test10ParamConstructor {

	@Test
	void testNullMoveset() {
		assertThrows(NullPointerException.class, () -> {new Pokemon("Bidoof", 1, "normal", null, "FIRERED", "location", "description", 1.0, 1.0, null);});
	}
	
	@Test
	void testValidConstruction() {
		ArrayList<LevelUpMove> moveset = new ArrayList<LevelUpMove>();
		Pokemon pokemon = new Pokemon("Bidoof", 1, "normal", "WATER", "LEAFGREEN", "location", "description", 1.0, 1.0, moveset);
		assertAll(() -> assertEquals("Bidoof", pokemon.getName()),
				  () -> assertEquals(1, pokemon.getPokedexNumber()),
				  () -> assertEquals("NORMAL", pokemon.getPrimaryType()),
				  () -> assertEquals("WATER", pokemon.getSecondaryType()),
				  () -> assertEquals("LEAFGREEN", pokemon.getExclusivity()),
				  () -> assertEquals("location", pokemon.getLocation()),
				  () -> assertEquals("description", pokemon.getDescription()),
				  () -> assertEquals(1.0, pokemon.getHeight(), TestConstants.DELTA),
				  () -> assertEquals(1.0, pokemon.getWeight(), TestConstants.DELTA),
				  () -> assertFalse(pokemon.isCaught()),
				  () -> assertFalse(pokemon.isSeen()),
				  () -> assertEquals(0, pokemon.getLevelUpMoveset().size()),
				  () -> assertNull(pokemon.getPreviousEvolution()),
				  () -> assertEquals(0, pokemon.getPostEvolutions().size()),
				  () -> assertEquals(moveset, pokemon.getLevelUpMoveset())
		);
	}

}
