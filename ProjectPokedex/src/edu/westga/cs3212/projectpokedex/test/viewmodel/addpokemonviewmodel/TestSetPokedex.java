package edu.westga.cs3212.projectpokedex.test.viewmodel.addpokemonviewmodel;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.projectpokedex.model.Pokedex;
import edu.westga.cs3212.projectpokedex.viewmodel.AddPokemonViewModel;

public class TestSetPokedex {
	
	@Test
	public void testSetNullPokedexValid() {
		AddPokemonViewModel adder = new AddPokemonViewModel();
		
		adder.setPokedex(null);
		
	}
	
	@Test
	public void testSetPokedexNotNull() {
		AddPokemonViewModel adder = new AddPokemonViewModel();
		
		Pokedex pokedex = new Pokedex();
		
		adder.setPokedex(pokedex);
		
		assertEquals(pokedex,adder.getPokedex());
		
	}

}
