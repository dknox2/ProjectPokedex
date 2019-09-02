package edu.westga.cs3212.projectpokedex.model.pokemon.evolution;

import edu.westga.cs3212.projectpokedex.model.pokemon.Pokemon;
import edu.westga.cs3212.projectpokedex.utils.ExceptionText;

/**
 * Represents a Pokemon evolution, with a Pokemon to be evolved to/from and a
 * method of evolution.
 * 
 * @author Kyle Riggs, Cody Graham, Dylan Knox, Joseph Fuller
 *
 */
public class Evolution {
	private Pokemon pokemon;
	private String evolutionMethod;
	
	/**
	 * Instantiates a new Evolution with the given Pokemon and evolution method.
	 * 
	 * @param pokemon
	 * 			the Pokemon to store evolution information for
	 * @param evolutionMethod
	 * 			the evolution method by which the Pokemon evolves/evolves from
	 * 
	 * @precondition pokemon != null && evolutionMethod != null
	 * @postcondition getPokemon() == pokemon && getEvolutionMethod() == evolutionMethod
	 * 
	 * @throws NullPointerException if pokemon or evolutionMethod is null
	 */
	public Evolution(Pokemon pokemon, String evolutionMethod) {
		this.errorOnInvalidPokemon(pokemon);
		this.errorOnInvalidEvolutionMethod(evolutionMethod);
		
		this.pokemon = pokemon;
		this.evolutionMethod = evolutionMethod;
	}
	
	private void errorOnInvalidPokemon(Pokemon pokemon) {
		if (pokemon == null) {
			throw new NullPointerException(ExceptionText.POKEMON_NULL);
		}
	}
	
	private void errorOnInvalidEvolutionMethod(String evolutionMethod) {
		if (evolutionMethod == null) {
			throw new NullPointerException(ExceptionText.EVOLUTION_METHOD_NULL);
		}
	}
	
	/**
	 * Gets the Pokemon asociated with the evolution.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the pokemon
	 */
	public Pokemon getPokemon() {
		return this.pokemon;
	}
	
	/**
	 * Gets the method by which the Pokemon evolves or is evolved into.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the evolution method
	 */
	public String getEvolutionMethod() {
		return this.evolutionMethod;
	}
	
	@Override
	public String toString() {
		return this.pokemon.getName() + ": " + this.evolutionMethod;
	}
}
