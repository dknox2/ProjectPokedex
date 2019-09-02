package edu.westga.cs3212.projectpokedex.test.model.pokemon.postevolutions;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.projectpokedex.model.pokemon.evolution.PostEvolutions;

class TestConstructor {

	@Test
	void testValidConstruction() {
		assertNotNull(new PostEvolutions());
	}

}
