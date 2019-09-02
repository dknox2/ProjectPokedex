package edu.westga.cs3212.projectpokedex.test.viewmodel.mainpageviewmodel;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.projectpokedex.model.pokemon.Pokemon;
import edu.westga.cs3212.projectpokedex.viewmodel.MainPageViewModel;

class TestAddPokemon {

	@Test
	void testInvalidPokemon() {
		MainPageViewModel viewModel = new MainPageViewModel();
		
		boolean result = viewModel.addPokemon(null);
		assertEquals(0, viewModel.getPokedex().getPokemon().size());
		assertFalse(result);
	}
	
	@Test
	void testValidPokemon() {
		MainPageViewModel viewModel = new MainPageViewModel();
		Pokemon pokemon = new Pokemon("Bidoof", 1, "NORMAL", null, "FIRERED", "location", "description", 1.0, 1.0);
		
		boolean result = viewModel.addPokemon(pokemon);
		
		assertEquals(0, viewModel.getPokedex().getPokemon().size());
		assertFalse(result);
	}
	
}
