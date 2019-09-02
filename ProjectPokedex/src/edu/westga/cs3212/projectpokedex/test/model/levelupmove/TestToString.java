package edu.westga.cs3212.projectpokedex.test.model.levelupmove;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.projectpokedex.model.pokemon.Type;
import edu.westga.cs3212.projectpokedex.model.pokemon.moves.Category;
import edu.westga.cs3212.projectpokedex.model.pokemon.moves.LevelUpMove;

class TestToString {

	@Test
	void testToString() {
		LevelUpMove move = new LevelUpMove(10, "Sunlight Yellow Overdrive", Type.NORMAL, Category.STATUS, 0, 100, 40);
		
		assertEquals("lvl. 10: Sunlight Yellow Overdrive    NORMAL    40 PP", move.toString());
	}

}
