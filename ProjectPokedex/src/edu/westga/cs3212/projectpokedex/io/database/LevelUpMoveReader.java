package edu.westga.cs3212.projectpokedex.io.database;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import edu.westga.cs3212.projectpokedex.io.PokemonIoConstants;
import edu.westga.cs3212.projectpokedex.model.pokemon.Type;
import edu.westga.cs3212.projectpokedex.model.pokemon.moves.Category;
import edu.westga.cs3212.projectpokedex.model.pokemon.moves.LevelUpMove;

/**
 * Converts JSON strings to LevelUpMoves.
 * 
 * @author Kyle Riggs, Cody Graham, Dylan Knox, Joseph Fuller
 */
public class LevelUpMoveReader {
	private JSONParser parser;
	private LevelUpMove move;
	private JSONObject parsedData;
	
	/**
	 * Instantiates a new LevelUpMoveReader for the given json string.
	 * 
	 * @param jsonData
	 * 		the JSON data to parse into a LevelUpMove
	 * 
	 * @precondition none
	 * @postcondition getMove() == a valid LevelUpMove, or null if json was invalid
	 */
	public LevelUpMoveReader(String jsonData) {
		this.parser = new JSONParser();
		try {
			this.parsedData = (JSONObject)this.parser.parse(jsonData);
			this.constructMove();
		} catch (ParseException e) {
			e.printStackTrace();
			throw new IllegalArgumentException("Could Not Parse The Given Input. Illegal Data");
		}
	}
	
	private void constructMove() {
		int level = Integer.parseInt(this.parsedData.get(PokemonIoConstants.Move.LEVEL).toString());
		String name = this.parsedData.get(PokemonIoConstants.Move.NAME).toString();
		Type type = Type.valueOf(this.parsedData.get(PokemonIoConstants.Move.TYPE).toString());
		Category category = Category.valueOf(this.parsedData.get(PokemonIoConstants.Move.CATEGORY).toString());
		int power = Integer.parseInt(this.parsedData.get(PokemonIoConstants.Move.POWER).toString());
		int accuracy = Integer.parseInt(this.parsedData.get(PokemonIoConstants.Move.ACCURACY).toString());
		int pp = Integer.parseInt(this.parsedData.get(PokemonIoConstants.Move.PP).toString());
		
		this.move = new LevelUpMove(level, name, type, category, power, accuracy, pp);
	}
	
	/**
	 * Gets the level up move, or null if the json was invalid.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the valid level up move, or null if the json was invalid
	 */
	public LevelUpMove getMove() {
		return this.move;
	}
}
