package edu.westga.cs3212.projectpokedex.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import edu.westga.cs3212.projectpokedex.model.pokedexitem.PokedexItem;
import edu.westga.cs3212.projectpokedex.model.pokemon.Pokemon;
import edu.westga.cs3212.projectpokedex.model.pokemon.evolution.Evolution;
import edu.westga.cs3212.projectpokedex.utils.ExceptionText;

/**
 * Sorts, retrieves, and manages Pokedex and Pokemon data.
 * 
 * @author Kyle Riggs, Cody Graham, Dylan Knox, Joseph Fuller
 */
public class Pokedex implements Iterable<PokedexItem> {
	private List<PokedexItem> pokemon;

	/**
	 * Creates a new, empty Pokedex.
	 * 
	 * @precondition none
	 * @postcondition getPokemon().size() == 0
	 */
	public Pokedex() {
		this.pokemon = new ArrayList<PokedexItem>();
	}

	/**
	 * Adds a Pokemon to the Pokedex.
	 * 
	 * @param pokemon the Pokemon to add
	 * 
	 * @precondition pokemon != null && pokemon.getPokedexNumber() is not already in
	 *               the Pokedex
	 * @postcondition size() == size()@prev + 1
	 * 
	 * @throws NullPointerException     if pokemon is null
	 * @throws IllegalArgumentException if pokemon.getPokedexNumber() is already in
	 *                                  the Pokedex
	 */
	public void add(Pokemon pokemon) throws IllegalArgumentException{
		if (pokemon == null) {
			throw new NullPointerException(ExceptionText.POKEMON_NULL);
		}
		if (this.containsPokedexNumber(pokemon.getPokedexNumber())) {
			throw new IllegalArgumentException(ExceptionText.DUPLICATE_POKEDEX_NUMBERS);
		}

		PokedexItem item = new PokedexItem(pokemon);
		this.pokemon.add(item);
	}
	
	/**
	 * Adds the given post evolution to the Pokemon, and sets the evolved Pokemon to evolve from
	 * the given Pokemon. 
	 * 
	 * @param pokedexNumber
	 * 			the Pokedex number to add the next evolution to
	 * @param evolution
	 * 			the evolution that the Pokemon evolves to
	 * 
	 * @precondition evolution != null
	 * @postcondition findPokemonByNumber(pokedexNumber).getPostEvolutions().size() == size@prev + 1
	 * 				  && evolution.getPokemon().getPreviousEvolution().getPokemon() == findPokemonByNumber(pokedexNumber)
	 * 
	 * @return true if the Pokemon was found and the evolution was added, false otherwise
	 */
	public boolean addPostEvolution(int pokedexNumber, Evolution evolution) {
		if (evolution == null) {
			throw new NullPointerException(ExceptionText.EVOLUTION_NULL);
		}
		
		Pokemon pokemon = this.findPokemonByNumber(pokedexNumber);
		if (pokemon != null) {
			pokemon.addPostEvolution(evolution);
			evolution.getPokemon().setPreviousEvolution(new Evolution(pokemon, evolution.getEvolutionMethod()));
			return true;
		}
		
		return false;
	}
	
	/**
	 * Finds and returns the Pokemon with the given pokedex number. If no Pokemon exist
	 * with that number, null is returned.
	 * 
	 * @param pokedexNumber
	 * 			the pokedex number to search for
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the Pokemon with the associated pokedex number, or null if none exist
	 */
	public Pokemon findPokemonByNumber(int pokedexNumber) {
		for (PokedexItem pokedexItem : this.pokemon) {
			Pokemon pokemon = pokedexItem.getPokemon();
			if (pokemon.getPokedexNumber() == pokedexNumber) {
				return pokemon;
			}
		}
		
		return null;
	}
	
	/**
	 * True if the pokedex number is contained in the Pokedex, false otherwise.
	 * 
	 * @param pokedexNumber the Pokedex number to check
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return true if the Pokemon is contained in the Pokedex, false otherwise
	 */
	public boolean containsPokedexNumber(int pokedexNumber) {
		for (PokedexItem item : this.pokemon) {
			if (item.getPokemon().getPokedexNumber() == pokedexNumber) {
				return true;
			}
		}
		
		return false;
	}

	/**
	 * Sorts the Pokedex according the natural ordering of the Pokemon class. By
	 * default, the Pokemon class is ordered by Pokedex number.
	 * 
	 * @precondition none
	 * @postcondition Pokedex sorted according to natural ordering of Pokemon class
	 */
	public void sort() {
		Collections.sort(this.pokemon);
	}

	/**
	 * Sorts the Pokedex according to the given Comparator.
	 * 
	 * @param comparator the Comparator that defines how the Pokedex will be sorted
	 * 
	 * @precondition comparator != null
	 * @postcondition Pokedex sorted according to the comparator's specifications
	 * 
	 * @throws NullPointerException if comparator == null
	 */
	public void sort(Comparator<? super PokedexItem> comparator) {
		if (comparator == null) {
			throw new NullPointerException(ExceptionText.COMPARATOR_NULL);
		}

		this.pokemon.sort(comparator);
	}

	/**
	 * Reverses the ordering of the Pokedex.
	 * 
	 * @precondition none
	 * @postcondition Pokedex in previous order, reversed.
	 */
	public void reverse() {
		Collections.reverse(this.pokemon);
	}
	
	/**
	 * Gets the Pokemon currently in the Pokedex.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the Pokemon currently in the Pokedex
	 */
	public List<Pokemon> getPokemon() {
		List<Pokemon> allPokemon = new ArrayList<Pokemon>();
		for (PokedexItem item : this.pokemon) {
			allPokemon.add(item.getPokemon());
		}
		
		return allPokemon;
	}

	/**
	 * Iterator.
	 *
	 * for traversal in for each loops over all the items in this container
	 *
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the iterator
	 */
	@Override
	public Iterator<PokedexItem> iterator() {
		return this.pokemon.iterator();
	}
}
