package edu.westga.cs3212.projectpokedex.test.model.pokedexitem;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.projectpokedex.model.pokedexitem.PokedexItem;
import edu.westga.cs3212.projectpokedex.model.pokemon.Pokemon;

public class TestGetters {

	@Test
	public void getValidInternalId() {
		Pokemon pokemon = new Pokemon("Bidoof", 1, "normal", "WATER", "LEAFGREEN", "location", "description", 1.0, 1.0);
		PokedexItem pokedexItem = new PokedexItem(pokemon);
		
		assertNotEquals(-1,pokedexItem.getInternalID());
		
		
		
	}
	
	@Test
	public void getValidMultipleInternalId() {
		Pokemon pokemon = new Pokemon("Bidoof", 1, "normal", "WATER", "LEAFGREEN", "location", "description", 1.0, 1.0);
		PokedexItem pokedexItem = new PokedexItem(pokemon);
		
		assertNotEquals(0,pokedexItem.getInternalID());
		
		int last = pokedexItem.getInternalID();
		
		pokedexItem = new PokedexItem(pokemon);
		assertNotEquals(last,pokedexItem.getInternalID());
		last = pokedexItem.getInternalID();
		
		pokedexItem = new PokedexItem(pokemon);
		assertNotEquals(last,pokedexItem.getInternalID());
		last = pokedexItem.getInternalID();
		
		pokedexItem = new PokedexItem(pokemon);
		assertNotEquals(last,pokedexItem.getInternalID());
		last = pokedexItem.getInternalID();
		
		pokedexItem = new PokedexItem(pokemon);
		assertNotEquals(last,pokedexItem.getInternalID());
		last = pokedexItem.getInternalID();
		
		pokedexItem = new PokedexItem(pokemon);
		assertNotEquals(last,pokedexItem.getInternalID());
		last = pokedexItem.getInternalID();
		
	}
	
}
