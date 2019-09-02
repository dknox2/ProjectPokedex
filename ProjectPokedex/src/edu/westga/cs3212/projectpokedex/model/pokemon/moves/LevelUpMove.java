package edu.westga.cs3212.projectpokedex.model.pokemon.moves;

import edu.westga.cs3212.projectpokedex.model.pokemon.Type;
import edu.westga.cs3212.projectpokedex.utils.ExceptionText;

/**
 * Represents a move learned by a Pokemon on level up.
 */
public class LevelUpMove {

	private int level;
	private String name;
	private Type type;
	private Category category;
	private int power;
	private int accuracy;
	private int pp;
	
	/**
	 * Instantiates a new level up move that is either physical or special in category.
	 *
	 * @param level 
	 * 			the level the move is learned
	 * @param name
	 * 			the name of the move
	 * @param type
	 * 			the type of the move
	 * @param category
	 * 			the category of the move
	 * @param power
	 * 			the power of the move
	 * @param accuracy
	 * 			the accuracy of the move
	 * @param pp
	 * 			the PP of the move
	 * 
	 * @precondition  level >= 1
	 * 				  && level <= 100
	 * 				  && name != null
	 * 				  && power >= 0
	 * 				  && accuracy >= 1
	 * 				  && accuracy <= 100
	 * 				  && PP >= 1
	 * @postcondition this.getLevel() == level
	 * 				  && this.getName() == name
	 * 				  && this.getType() == type
	 * 				  && this.getCategory() == category
	 * 				  && this.getPower() == power
	 * 				  && this.getAccuracy() == accuracy
	 * 				  && this.getPP() == PP
	 */
	public LevelUpMove (int level, String name, Type type, Category category, int power, int accuracy, int pp) {
		this.errorOnInvalidLevel(level);
		this.errorOnInvalidName(name);
		this.errorOnInvalidPower(power, category);
		this.errorOnInvalidAccuracy(accuracy);
		this.errorOnInvalidPP(pp);
		
		this.level = level;
		this.name = name;
		this.type = type;
		this.category = category;
		this.power = power;
		this.accuracy = accuracy;
		this.pp = pp;
	}

	private void errorOnInvalidLevel(int level) {		
		if (level < 1) {
			throw new IllegalArgumentException(ExceptionText.LEVEL_BELOW_1);
		}
		if (level > 100) {
			throw new IllegalArgumentException(ExceptionText.LEVEL_ABOVE_100);
		}
	}
	
	private void errorOnInvalidName(String name) {
		if (name == null) {
			throw new IllegalArgumentException(ExceptionText.NAME_NULL);
		}
	}
	
	private void errorOnInvalidPower(int power, Category category) {
		if (power < 0) {
			throw new IllegalArgumentException(ExceptionText.POWER_BELOW_0);
		}
		if (category == Category.STATUS && power != 0) {
			throw new IllegalArgumentException(ExceptionText.POWER_STATUS_MISMATCH);
		}
	}
	
	private void errorOnInvalidAccuracy(int accuracy) {
		if (accuracy < 1) {
			throw new IllegalArgumentException(ExceptionText.ACCURACY_BELOW_1);
		}
		
		if (accuracy > 100) {
			throw new IllegalArgumentException(ExceptionText.ACCURACY_ABOVE_100);
		}
	}
	
	private void errorOnInvalidPP(int PP) {
		if (PP < 1) {
			throw new IllegalArgumentException(ExceptionText.PP_INVALID);
		}
	}

	/**
	 * Gets the level.
	 * 
	 * @precondition none
	 * @postcondition none
	 *
	 * @return the level
	 */
	public int getLevel() {
		return this.level;
	}

	/**
	 * Gets the name.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Gets the type.
	 *
	 * @precondition none
	 * @postcondition none
	 *
	 * @return the type
	 */
	public Type getType() {
		return this.type;
	}

	/**
	 * Gets the category.
	 *
	 * @precondition none
	 * @postcondition none
	 *
	 * @return the category
	 */
	public Category getCategory() {
		return this.category;
	}

	/**
	 * Gets the power.
	 * 
	 * @precondition none
	 * @postcondition none
	 *
	 * @return the power
	 */
	public int getPower() {
		return this.power;
	}

	/**
	 * Gets the accuracy.
	 * 
	 * @precondition none
	 * @postcondition none
	 *
	 * @return the accuracy
	 */
	public int getAccuracy() {
		return this.accuracy;
	}

	/**
	 * Gets the pp.
	 * 
	 * @precondition none
	 * @postcondition none
	 *
	 * @return the pp
	 */
	public int getPP() {
		return this.pp;
	}

	@Override
	public String toString() {
		return "lvl. " + this.level + ": " + this.name + "    " + this.type + "    " + this.pp + " PP";
	}
}
