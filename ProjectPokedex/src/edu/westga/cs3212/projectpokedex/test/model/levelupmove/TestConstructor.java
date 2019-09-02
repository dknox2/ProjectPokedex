package edu.westga.cs3212.projectpokedex.test.model.levelupmove;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.projectpokedex.model.pokemon.Type;
import edu.westga.cs3212.projectpokedex.model.pokemon.moves.Category;
import edu.westga.cs3212.projectpokedex.model.pokemon.moves.LevelUpMove;

class TestConstructor {

	@Test
	void testLevelLessThan1() {
		assertThrows(IllegalArgumentException.class, () -> new LevelUpMove(0, "Sunlight Yellow Overdrive", Type.NORMAL, Category.SPECIAL, 60, 60, 1));
	}

	@Test
	void testLevelGreaterThan100() {
		assertThrows(IllegalArgumentException.class, () -> new LevelUpMove(200, "Sunlight Yellow Overdrive", Type.NORMAL, Category.SPECIAL, 60, 60, 1));
	}
	
	@Test
	void testNullName() {
		assertThrows(IllegalArgumentException.class, () -> new LevelUpMove(10, null, Type.NORMAL, Category.SPECIAL, 60, 60, 1));
	}
	
	@Test
	void testPowerLessThan0() {
		assertThrows(IllegalArgumentException.class, () -> new LevelUpMove(10, "Sunlight Yellow Overdrive", Type.NORMAL, Category.SPECIAL, -5, 60, 1));
	}
	
	@Test
	void testCategoryStatusPowerGreaterThan0() {
		assertThrows(IllegalArgumentException.class, () -> new LevelUpMove(10, "Sunlight Yellow Overdrive", Type.NORMAL, Category.STATUS, 50, 60, 1));
	}
	
	@Test
	void testAccuracyLessThan0() {
		assertThrows(IllegalArgumentException.class, () -> new LevelUpMove(10, "Sunlight Yellow Overdrive", Type.NORMAL, Category.SPECIAL, 60, -5, 1));
	}
	
	@Test
	void testAccuracyGreaterThan100() {
		assertThrows(IllegalArgumentException.class, () -> new LevelUpMove(10, "Sunlight Yellow Overdrive", Type.NORMAL, Category.SPECIAL, 60, 200, 1));
	}
	
	@Test
	void testPPLessThan1() { 
		assertThrows(IllegalArgumentException.class, () -> new LevelUpMove(10, "Sunlight Yellow Overdrive", Type.NORMAL, Category.SPECIAL, 60, 60, -600));
	}
	
	@Test
	void testValidConstruction() {
		LevelUpMove move = new LevelUpMove(10, "Sunlight Yellow Overdrive", Type.NORMAL, Category.STATUS, 0, 100, 40);
		
		assertAll(
			() -> assertEquals(10, move.getLevel()),
			() -> assertEquals("Sunlight Yellow Overdrive", move.getName()),
			() -> assertEquals(Type.NORMAL, move.getType()),
			() -> assertEquals(Category.STATUS, move.getCategory()),
			() -> assertEquals(0, move.getPower()),
			() -> assertEquals(100, move.getAccuracy()),
			() -> assertEquals(40, move.getPP())
		);
	}
}
