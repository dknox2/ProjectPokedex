package edu.westga.cs3212.projectpokedex.test.model.pokemon.constructor;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.projectpokedex.model.pokemon.Pokemon;
import edu.westga.cs3212.projectpokedex.test.TestConstants;

class Test9ParamConstructor {

	@Test
	void testNameNull() {
		assertThrows(NullPointerException.class, () -> new Pokemon(null, 1, "NORMAL", "FLYING", "FR", "location", "description", 1.0, 1.0));
	}
	
	@Test
	void testPokedexNumberNegative() {
		assertThrows(IllegalArgumentException.class, () -> new Pokemon("Bidoof", -1, "NORMAL", "WATER", "FR", "location", "description", 1.0, 1.0));
	}
	
	@Test
	void testPokedexNumber0() {
		assertThrows(IllegalArgumentException.class, () -> new Pokemon("Bidoof", 0, "NORMAL", "WATER", "FR","location", "description", 1.0, 1.0));
	}
	
	@Test
	void testPrimaryTypeNull() {
		assertThrows(NullPointerException.class, () -> new Pokemon("Bidoof", 1, null, "WATER", "LG", "location", "description", 1.0, 1.0));
	}
	
	@Test
	void testPrimaryTypeNotInTypes() {
		assertThrows(IllegalArgumentException.class, () -> new Pokemon("Bidoof", 1, "blue", "normal", "FR", "location", "description", 1.0, 1.0));
	}
	
	@Test
	void testSecondaryTypeNotInTypes() {
		assertThrows(IllegalArgumentException.class, () -> new Pokemon("Bidoof", 1, "normal", "orange", "FR", "location", "description", 1.0, 1.0));
	}
	
	@Test
	void testLocationNull() {
		assertThrows(NullPointerException.class, () -> new Pokemon("Bidoof", 1, "normal", "water", "BOTH", null, "description", 1.0, 1.0));
	}
	
	@Test
	void testDescriptionNull() {
		assertThrows(NullPointerException.class, () -> new Pokemon("Bidoof", 1, "normal", "water", "FIRERED", "location", null, 1.0, 1.0));	
	}
	
	@Test
	void testExclusivityNull() {
		assertThrows(NullPointerException.class, () -> new Pokemon("Bidoof", 1, "normal", "water", null, "location", "description", 1.0, 1.0));	
	}
	
	@Test
	void testHeight0() {
		assertThrows(IllegalArgumentException.class, () -> new Pokemon("Bidoof", 1, "normal", "water", "LG", "location", "description", 0.0, 1.0));	
	}
	
	@Test
	void testHeightLessThan0() {
		assertThrows(IllegalArgumentException.class, () -> new Pokemon("Bidoof", 1, "normal", "water", "LG", "location", "description", -1.0, 1.0));	
	}
	
	@Test
	void testWeight0() {
		assertThrows(IllegalArgumentException.class, () -> new Pokemon("Bidoof", 1, "normal", "water", "FR", "location", "description", 1.0, 0.0));	
	}
	
	@Test
	void testWeightLessThan0() {
		assertThrows(IllegalArgumentException.class, () -> new Pokemon("Bidoof", 1, "normal", "water", "FR", "location", "description", 1.0, -1.0));	
	}
	
	@Test
	void testValidConstructorOneType() {
		Pokemon pokemon = new Pokemon("Bidoof", 1, "normal", null, "FIRERED", "location", "description", 1.0, 1.0);
		assertAll(() -> assertEquals("Bidoof", pokemon.getName()),
				  () -> assertEquals(1, pokemon.getPokedexNumber()),
				  () -> assertEquals("NORMAL", pokemon.getPrimaryType()),
				  () -> assertEquals(null, pokemon.getSecondaryType()),
				  () -> assertEquals("FIRERED", pokemon.getExclusivity()),
				  () -> assertEquals("location", pokemon.getLocation()),
				  () -> assertEquals("description", pokemon.getDescription()),
				  () -> assertEquals(1.0, pokemon.getHeight(), TestConstants.DELTA),
				  () -> assertEquals(1.0, pokemon.getWeight(), TestConstants.DELTA),
				  () -> assertFalse(pokemon.isCaught()),
				  () -> assertFalse(pokemon.isSeen()),
				  () -> assertEquals(0, pokemon.getLevelUpMoveset().size()),
				  () -> assertNull(pokemon.getPreviousEvolution()),
				  () -> assertEquals(0, pokemon.getPostEvolutions().size())
		);
	}
	
	@Test
	void testValidConstructorTwoTypes() {
		Pokemon pokemon = new Pokemon("Bidoof", 1, "normal", "WATER", "LEAFGREEN", "location", "description", 1.0, 1.0);
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
				  () -> assertEquals(0, pokemon.getPostEvolutions().size())
		);
	}
}
