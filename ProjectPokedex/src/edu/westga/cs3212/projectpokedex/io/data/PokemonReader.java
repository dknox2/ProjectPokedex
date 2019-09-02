package edu.westga.cs3212.projectpokedex.io.data;

import java.util.ArrayList;
import java.util.Collection;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import edu.westga.cs3212.projectpokedex.io.PokemonIoConstants;
import edu.westga.cs3212.projectpokedex.io.database.LevelUpMoveReader;
import edu.westga.cs3212.projectpokedex.model.pokemon.Pokemon;
import edu.westga.cs3212.projectpokedex.model.pokemon.moves.LevelUpMove;

/**
 * Reads JSON data and converts to Pokemon objects.
 */
public class PokemonReader {

	private JSONParser parser;
	private Pokemon pokemon;
	private JSONObject parsedData;

	/**
	 * Instantiates a new pokemon reader by parsing the given JSON string.
	 *
	 * @param jsonData the json data
	 * @throws IllegalArgumentException thrown if the JSON string any errors
	 */
	public PokemonReader(String jsonData) throws IllegalArgumentException {
		this.parser = new JSONParser();
		try {
			this.parsedData = (JSONObject) this.parser.parse(jsonData);
			this.constructPokemon();
		} catch (NullPointerException e) {
			throw new IllegalArgumentException("JSON Given Was Null");
		} catch (ParseException e) {
			throw new IllegalArgumentException("Could Not Parse The Given Input. Illegal Data");
		}
	}

	/**
	 * Gets the pokemon.
	 *
	 * @return the pokemon
	 */
	public Pokemon getPokemon() {
		return this.pokemon;
	}

	private void constructPokemon() throws IllegalArgumentException {

		String name = null;
		int id = 0;
		String summary = null;
		double height = 0;
		double weight = 0;
		String type1 = null;
		String type2 = null;
		String exclusivity = null;
		String location = null;

		try {

			name = this.readRepresentation(PokemonIoConstants.Pokemon.NAME);
			id = this.readId();
			summary = this.readRepresentation(PokemonIoConstants.Pokemon.SUMMARY);
			height = this.readHeight();
			weight = this.readWeight();
			type1 = this.readType1();
			type2 = this.readType2();
			exclusivity = this.readRepresentation(PokemonIoConstants.Pokemon.EXCLUSIVITY);
			location = this.readRepresentation(PokemonIoConstants.Pokemon.LOCATION);

		} catch (NullPointerException e) {
			throw new IllegalArgumentException("Value Not In JSON Error");
		}

		Pokemon poke;

		try {
			String movesetJSON = this.readRepresentation(PokemonIoConstants.Pokemon.MOVESET);
			Collection<LevelUpMove> moveset = this.generateMovesetFromJSON((JSONArray) this.parser.parse(movesetJSON));
			poke = new Pokemon(name, id, type1, type2, exclusivity, location, summary, height, weight, moveset);
		} catch (Exception e) {
			poke = new Pokemon(name, id, type1, type2, exclusivity, location, summary, height, weight);
		}

		this.pokemon = poke;

	}

	private int readId() {
		String value = this.readRepresentation(PokemonIoConstants.Pokemon.POKEDEX_ID);
		return Integer.parseInt(value);
	}

	private double readWeight() {
		String value = this.readRepresentation(PokemonIoConstants.Pokemon.WEIGHT);
		return Double.parseDouble(value);
	}

	private double readHeight() {
		String value = this.readRepresentation(PokemonIoConstants.Pokemon.HEIGHT);
		return Double.parseDouble(value);
	}

	private String readType1() {
		String value = this.readRepresentation(PokemonIoConstants.Pokemon.TYPE1);
		return value;
	}

	private String readType2() {
		String value = this.readRepresentation(PokemonIoConstants.Pokemon.TYPE2);
		if (value.compareToIgnoreCase(PokemonIoConstants.NULL) == 0) {
			value = null;
		}
		return value;
	}

	private String readRepresentation(String key) throws IllegalArgumentException {

		Object data = this.parsedData.get(key).toString();

		if (data == null) {
			throw new IllegalArgumentException("The Given Input Does Not Have the Required Data By The Key: " + key);
		}

		return data.toString();
	}

	private Collection<LevelUpMove> generateMovesetFromJSON(JSONArray json) {
		Collection<LevelUpMove> moveset = new ArrayList<LevelUpMove>();

		for (int i = 0; i < json.size(); ++i) {
			String current = json.get(i).toString();
			LevelUpMoveReader reader = new LevelUpMoveReader(current);
			moveset.add(reader.getMove());
		}

		return moveset;
	}

}
