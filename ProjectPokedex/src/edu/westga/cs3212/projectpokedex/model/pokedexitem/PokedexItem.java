package edu.westga.cs3212.projectpokedex.model.pokedexitem;

import edu.westga.cs3212.projectpokedex.model.pokemon.Pokemon;

/**
 * Encapsulates Pokemon for internal usage in Pokedex.
 * 
 * @author Kyle Riggs, Cody Graham, Dylan Knox, Joseph Fuller 
 */
public class PokedexItem implements Comparable<PokedexItem> {

	private static int internalIdCounter = 0;

	private Pokemon storedPokemon;
	private int internalId;

	/**
	 * Instantiates a new pokedex item.
	 *
	 * @param pokemon the pokemon
	 * 
	 * @precondition pokemon != null
	 * @postcondition getPokemon() == pokemon
	 */
	public PokedexItem(Pokemon pokemon) {
		if (pokemon == null) {
			throw new NullPointerException();
		}
		
		this.storedPokemon = pokemon;
		this.internalId = PokedexItem.internalIdCounter;
		PokedexItem.internalIdCounter++;
	}

	/**
	 * Gets the stored pokemon.
	 *
	 * @precondition none
	 * @postcondition none
	 *
	 * @return the stored pokemon
	 */
	public Pokemon getPokemon() {
		return this.storedPokemon;
	}

	/**
	 * Gets the internal ID.
	 * 
	 * @precondition none
	 * @postcondition none
	 *
	 * @return the internal ID
	 */
	public int getInternalID() {
		return this.internalId;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(PokedexItem pokemon) {
		return Integer.compare(this.storedPokemon.getPokedexNumber(),pokemon.getPokemon().getPokedexNumber());
	}

}
