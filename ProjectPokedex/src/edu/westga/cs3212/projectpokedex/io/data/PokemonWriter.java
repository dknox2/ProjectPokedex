package edu.westga.cs3212.projectpokedex.io.data;

import org.json.simple.JSONObject;

import edu.westga.cs3212.projectpokedex.io.PokemonIoConstants;
import edu.westga.cs3212.projectpokedex.model.pokemon.Pokemon;
import edu.westga.cs3212.projectpokedex.utils.ExceptionText;

/**
 * Reads Pokemon data and converts to JSON strings.
 */
public class PokemonWriter {
	
	private JSONObject representation;
	
	private Pokemon storedPokemon;
	
	/**
	 * Instantiates a new pokemon writer.
	 *
	 * @param pokemon the pokemon
	 * 
	 * @precondition pokemon != null
	 * @postcondition getJSONString() == JSON representation of the Pokemon
	 * 
	 * @throws IllegalArgumentException if pokemon is null
	 */
	public PokemonWriter(Pokemon pokemon) {
		if (pokemon == null) {
			throw new IllegalArgumentException(ExceptionText.POKEMON_NULL);
		}
		
		this.representation = new JSONObject();
		this.storedPokemon = pokemon;
		this.toJSON();
		
	}
	
	/**
	 * Gets the JSON string.
	 *
	 * @return the JSON string
	 */
	public String getJSONString() {
		return this.representation.toJSONString();
	}

	private void toJSON() {
		this.addToRepresentation(PokemonIoConstants.Pokemon.NAME,this.storedPokemon.getName());
		this.addToRepresentation(PokemonIoConstants.Pokemon.POKEDEX_ID,this.storedPokemon.getPokedexNumber());
		this.addToRepresentation(PokemonIoConstants.Pokemon.SUMMARY,this.storedPokemon.getDescription());
		this.addToRepresentation(PokemonIoConstants.Pokemon.HEIGHT,this.storedPokemon.getHeight());
		this.addToRepresentation(PokemonIoConstants.Pokemon.WEIGHT,this.storedPokemon.getWeight());
		this.addToRepresentation(PokemonIoConstants.Pokemon.TYPE1,this.storedPokemon.getPrimaryType());
		this.addToRepresentation(PokemonIoConstants.Pokemon.TYPE2,this.storedPokemon.getSecondaryType());
		this.addToRepresentation(PokemonIoConstants.Pokemon.EXCLUSIVITY,this.storedPokemon.getExclusivity());
		this.addToRepresentation(PokemonIoConstants.Pokemon.LOCATION, this.storedPokemon.getLocation());
		this.addToRepresentation(PokemonIoConstants.Pokemon.MOVESET, this.storedPokemon.getLevelUpMoveset());
	}
	
	@SuppressWarnings("unchecked")
	private void addToRepresentation(String key, Object toAdd) {
		if(toAdd == null) {
			toAdd = "NULL";
		}
		this.representation.put(key,toAdd.toString());
	}

	
}
