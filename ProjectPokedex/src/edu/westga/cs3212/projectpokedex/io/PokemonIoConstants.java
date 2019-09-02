package edu.westga.cs3212.projectpokedex.io;

import org.json.simple.JSONObject;

/**
 * Constants for database IO.
 */
public class PokemonIoConstants {

	public class Pokemon {
		public static final String NAME = "name";
		
		public static final String POKEDEX_ID = "id";
		
		public static final String WEIGHT = "weight";
		
		public static final String HEIGHT = "height";
		
		public static final String LOCATION = "location";
		
		public static final String SUMMARY = "summary";
		
		public static final String TYPE1 = "type1";
		
		public static final String TYPE2 = "type2";
		
		public static final String EXCLUSIVITY = "exclusivity";
		
		public static final String MOVESET = "moveset";
	}
	
	public class Move {
		public static final String LEVEL = "level";
		
		public static final String NAME = "name";
		
		public static final String TYPE = "type";
		
		public static final String CATEGORY = "category";
		
		public static final String POWER = "power";
		
		public static final String ACCURACY = "accuracy";
		
		public static final String PP = "pp";
	}
	
	public static final String NULL = "NULL";
	
	public static final String DEFAULT_DATABASE_DIR = "database/";
	
	public static final String DEFAULT_DATA_IMAGE_URL = DEFAULT_DATABASE_DIR+"/images/0pokeball.png";
	
	public static final String DEFAULT_DATA_LIST_LOCATION = DEFAULT_DATABASE_DIR+"!listOfPokemon.txt";

	public static final String SERVER_REQUEST_BOOT = "boot";
	
	public static final String SERVER_REQUEST_CREATE = "create";
	
	public static final String SERVER_REQUEST_DATA = "data";
	
	public static final String SERVER_REQUEST_SUCCESS = "success";
	
	public static final String JSON_TRUE = "TRUE";
	
	public static final String JSON_FALSE = "FALSE";
	
	public static String readRepresentation(JSONObject dictionary, String key) throws IllegalArgumentException {

		Object data = dictionary.get(key).toString();

		if (data == null) {
			throw new IllegalArgumentException("The Given Input Does Not Have the Required Data By The Key: " + key);
		}

		return data.toString();
	}
	
}
