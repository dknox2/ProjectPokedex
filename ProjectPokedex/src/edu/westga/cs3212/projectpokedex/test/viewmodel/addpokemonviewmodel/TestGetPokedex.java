package edu.westga.cs3212.projectpokedex.test.viewmodel.addpokemonviewmodel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.projectpokedex.model.Pokedex;
import edu.westga.cs3212.projectpokedex.viewmodel.AddPokemonViewModel;

public class TestGetPokedex {

	@Test
	public void getPokedexNull() {
		AddPokemonViewModel adder = new AddPokemonViewModel();
		
		adder.setPokedex(null);
		
		assertEquals(null,adder.getPokedex());
	}
	
	@Test
	public void getPokedexNotNull() {
		AddPokemonViewModel adder = new AddPokemonViewModel();
		
		assertNotEquals(null,adder.getPokedex());
	}
	
	@Test
	public void getPokedexCustom() {
		AddPokemonViewModel adder = new AddPokemonViewModel();
		
		Pokedex pokedex = new Pokedex();
		adder.setPokedex(pokedex);
		
		assertEquals(pokedex,adder.getPokedex());
	}
	
}
