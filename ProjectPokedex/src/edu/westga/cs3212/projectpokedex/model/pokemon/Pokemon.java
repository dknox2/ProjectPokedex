package edu.westga.cs3212.projectpokedex.model.pokemon;

import java.lang.String;
import java.util.ArrayList;
import java.util.Collection;

import edu.westga.cs3212.projectpokedex.model.pokemon.evolution.Evolution;
import edu.westga.cs3212.projectpokedex.model.pokemon.evolution.PostEvolutions;
import edu.westga.cs3212.projectpokedex.model.pokemon.moves.LevelUpMove;
import edu.westga.cs3212.projectpokedex.utils.ExceptionText;

/**
 * Stores and handles basic Pokemon data including name, number and type
 * information.
 * 
 * @author Kyle Riggs, Cody Graham, Dylan Knox, Joseph Fuller
 */
public class Pokemon implements Comparable<Pokemon> {

	private String name;

	private int pokedexNumber;

	private String primaryType;
	private String secondaryType;

	private String exclusivity;
	private String location;
	private String description;

	private double height;
	private double weight;

	private boolean caught;
	private boolean seen;

	private Collection<LevelUpMove> levelUpMoveset;

	private Evolution previousEvolution;
	private Collection<Evolution> postEvolutions;

	/**
	 * Creates a new Pokemon object with the given types and information.
	 * 
	 * @param name          The name of the Pokemon
	 * @param pokedexNumber The Pokedex number of the Pokemon
	 * @param primaryType   The primary type of the Pokemon
	 * @param secondaryType The secondary type of the Pokemon
	 * @param exclusivity   The version exclusivity of the Pokemon
	 * @param location      The location of the Pokemon
	 * @param description   The Pokedex description of the Pokemon
	 * @param height        The height of the Pokemon
	 * @param weight        The weight of the Pokemon
	 * 
	 * @precondition name != null && pokedexNumber >= 1 && primaryType exists in
	 *               Type enum && secondaryType is in the Type enum if secondaryType
	 *               != null && exclusivity exists in Exclusivity enum && location
	 *               != null && description != null && height < 0.0 && weight < 0.0
	 * @postcondition getName() == name && getPokedexNumber() == pokedexNumber &&
	 *                getPrimaryType() == primaryType.toUpperCase() &&
	 *                getSecondaryType() == secondaryType.toUpperCase() || null if
	 *                secondaryType was null (meaning no secondary type) &&
	 *                getExclusivity() == exclusivity && getLocation() == location
	 *                && getDescription() == description && getHeight() == height &&
	 *                getWeight() == weight && isCaught() == false && isSeen() ==
	 *                false && getLevelUpMoveset().size() == 0 &&
	 *                getPreviousEvolution() == null && getNextEvolutions().size()
	 *                == 0
	 * 
	 * @throws NullPointerException     if name, primaryType, location, or
	 *                                  description are null
	 * @throws IllegalArgumentException if primaryType, secondaryType, exclusivity,
	 *                                  height, or weight are invalid
	 */
	public Pokemon(String name, int pokedexNumber, String primaryType, String secondaryType, String exclusivity,
			String location, String description, double height, double weight) {
		this.validateArgs(name, pokedexNumber, primaryType, secondaryType, exclusivity, location, description, height,
				weight);

		this.name = name;
		this.pokedexNumber = pokedexNumber;
		this.primaryType = primaryType.toUpperCase();

		if (secondaryType != null) {
			this.secondaryType = secondaryType.toUpperCase();
		} else {
			this.secondaryType = null;
		}

		this.exclusivity = exclusivity.toUpperCase();
		this.location = location;
		this.description = description;

		this.height = height;
		this.weight = weight;

		this.caught = false;
		this.seen = false;

		this.levelUpMoveset = new ArrayList<LevelUpMove>();

		this.previousEvolution = null;
		this.postEvolutions = new PostEvolutions();
	}

	/**
	 * Creates a new Pokemon object with the given types and information.
	 * 
	 * @param name           The name of the Pokemon
	 * @param pokedexNumber  The Pokedex number of the Pokemon
	 * @param primaryType    The primary type of the Pokemon
	 * @param secondaryType  The secondary type of the Pokemon
	 * @param exclusivity    The version exclusivity of the Pokemon
	 * @param location       The location of the Pokemon
	 * @param description    The Pokedex description of the Pokemon
	 * @param height         The height of the Pokemon
	 * @param weight         The weight of the Pokemon
	 * @param levelUpMoveset The level up moveset of the Pokeon
	 * 
	 * @precondition name != null && pokedexNumber >= 1 && primaryType exists in
	 *               Type enum && secondaryType is in the Type enum if secondaryType
	 *               != null && exclusivity exists in Exclusivity enum && location
	 *               != null && description != null && height < 0.0 && weight < 0.0
	 * @postcondition getName() == name && getPokedexNumber() == pokedexNumber &&
	 *                getPrimaryType() == primaryType.toUpperCase() &&
	 *                getSecondaryType() == secondaryType.toUpperCase() || null if
	 *                secondaryType was null (meaning no secondary type) &&
	 *                getExclusivity() == exclusivity && getLocation() == location
	 *                && getDescription() == description && getHeight() == height &&
	 *                getWeight() == weight && isCaught() == false && isSeen() ==
	 *                false && getLevelUpMoveset() == levelUpMoveset &&
	 *                getPreviousEvolution() == null && getNextEvolutions().size()
	 *                == 0
	 * 
	 * @throws NullPointerException     if name, primaryType, location, description,
	 *                                  or moveset are null
	 * @throws IllegalArgumentException if primaryType, secondaryType, exclusivity,
	 *                                  height, or weight are invalid
	 */
	public Pokemon(String name, int pokedexNumber, String primaryType, String secondaryType, String exclusivity,
			String location, String description, double height, double weight, Collection<LevelUpMove> levelUpMoveset) {
		this(name, pokedexNumber, primaryType, secondaryType, exclusivity, location, description, height, weight);

		this.errorOnInvalidLevelUpMoveset(levelUpMoveset);
		this.levelUpMoveset = levelUpMoveset;
	}

	private void validateArgs(String name, int pokedexNumber, String primaryType, String secondaryType,
			String exclusivity, String location, String description, double height, double weight) {
		this.errorOnInvalidName(name);
		this.errorOnInvalidPokedexNumber(pokedexNumber);
		this.errorOnInvalidType(primaryType);

		if (secondaryType != null) {
			this.errorOnInvalidType(secondaryType);
		}

		this.errorOnInvalidExclusivity(exclusivity);
		this.errorOnInvalidLocation(location);
		this.errorOnInvalidDescription(description);
		this.errorOnInvalidHeight(height);
		this.errorOnInvalidWeight(weight);

	}

	private void errorOnInvalidName(String name) {
		if (name == null) {
			throw new NullPointerException(ExceptionText.NAME_NULL);
		}
	}

	private void errorOnInvalidPokedexNumber(int pokedexNumber) {
		if (pokedexNumber < 1) {
			throw new IllegalArgumentException(ExceptionText.NUMBER_LESS_THAN_ONE);
		}
	}

	private void errorOnInvalidType(String type) {
		Type.valueOf(type.toUpperCase());
	}

	private void errorOnInvalidExclusivity(String exclusivity) {
		Exclusivity.valueOf(exclusivity.toUpperCase());
	}

	private void errorOnInvalidLocation(String location) {
		if (location == null) {
			throw new NullPointerException(ExceptionText.LOCATION_NULL);
		}
	}

	private void errorOnInvalidDescription(String description) {
		if (description == null) {
			throw new NullPointerException(ExceptionText.DESCRIPTION_NULL);
		}
	}

	private void errorOnInvalidHeight(double height) {
		if (height <= 0.0) {
			throw new IllegalArgumentException(ExceptionText.HEIGHT_INVALID);
		}
	}

	private void errorOnInvalidWeight(double weight) {
		if (weight <= 0.0) {
			throw new IllegalArgumentException(ExceptionText.WEIGHT_INVALID);
		}
	}

	private void errorOnInvalidLevelUpMoveset(Collection<LevelUpMove> moveset) {
		if (moveset == null) {
			throw new NullPointerException(ExceptionText.LEVEL_UP_MOVESET_NULL);
		}
	}

	/**
	 * Gets the name of the Pokemon.
	 *
	 * @precondition none
	 * @postcondition none
	 *
	 * @return the name of the Pokemon
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Sets the name of the Pokemon.
	 *
	 * @precondition name != null
	 * @postcondition getName() == name
	 *
	 * @param name the new name of the Pokemon
	 */
	public void setName(String name) {
		errorOnInvalidName(name);
		this.name = name;
	}

	/**
	 * Gets the pokedex number of the Pokemon.
	 *
	 * @precondition none
	 * @postcondition none
	 *
	 * @return the pokedex number of the Pokemon
	 */
	public int getPokedexNumber() {
		return this.pokedexNumber;
	}

	/**
	 * Sets the pokedex number of the Pokemon.
	 *
	 * @param pokedexNumber the new pokedex number of the Pokemon
	 * 
	 * @precondition pokedexNumber >= 1
	 * @postcondition getPokedexNumber() == pokedexNumber
	 */
	public void setPokedexNumber(int pokedexNumber) {
		errorOnInvalidPokedexNumber(pokedexNumber);
		this.pokedexNumber = pokedexNumber;
	}

	/**
	 * Gets the primary type of the Pokemon
	 *
	 * @precondition none
	 * @postcondition none
	 *
	 * @return the primary type of the Pokemon
	 */
	public String getPrimaryType() {
		return this.primaryType;
	}

	/**
	 * Sets the primary type of the Pokemon.
	 * 
	 * @param primaryType the new primary type of the Pokemon
	 * 
	 * @precondition primaryType != null && primaryType exists in the Type enum
	 * @postcondition getPrimaryType() == primaryType
	 */
	public void setPrimaryType(String primaryType) {
		errorOnInvalidType(primaryType);
		this.primaryType = primaryType;
	}

	/**
	 * Gets the secondary type of the Pokemon.
	 *
	 * @precondition none
	 * @postcondition none
	 *
	 * @return the secondary type of the Pokemon
	 */
	public String getSecondaryType() {
		return this.secondaryType;
	}

	/**
	 * Sets the secondary type of the Pokemon.
	 * 
	 * @param secondaryType the new secondary type of the Pokemon
	 *
	 * @precondition secondaryType is in the Type enum if secondaryType != null
	 * @postcondition getSecondaryType() == secondaryType.toUpperCase() || null if
	 *                secondaryType was null (meaning the Pokemon only has one type)
	 */
	public void setSecondaryType(String secondaryType) {
		if (secondaryType != null) {
			this.errorOnInvalidType(secondaryType);
			this.secondaryType = secondaryType.toUpperCase();
		} else {
			this.secondaryType = secondaryType;
		}
	}

	/**
	 * Checks if the Pokemon is caught.
	 * 
	 * @precondition none
	 * @postcondition none
	 *
	 * @return true, if the Pokemon is caught
	 */
	public boolean isCaught() {
		return this.caught;
	}

	/**
	 * Sets the caught status of the Pokemon.
	 * 
	 * @param caught the new caught status of the Pokemon
	 * 
	 * @precondition none
	 * @postcondition isCaught() == caught
	 */
	public void setCaught(boolean caught) {
		this.caught = caught;
	}

	/**
	 * Checks if the Pokemon is seen.
	 * 
	 * @precondition none
	 * @postcondition none
	 *
	 * @return true, if the Pokemon is seen
	 */
	public boolean isSeen() {
		return this.seen;
	}

	/**
	 * Sets the seen status of the Pokemon.
	 *
	 * @param seen the new seen status of the Pokemon
	 *
	 * @precondition none
	 * @postcondition isSeen() == seen
	 */
	public void setSeen(boolean seen) {
		this.seen = seen;
	}

	/**
	 * Gets the version-exclusivity of the Pokemon
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the version-exclusivity of the Pokemon
	 */
	public String getExclusivity() {
		return this.exclusivity;
	}

	/**
	 * Sets the exclusivity.
	 * 
	 * @param exclusivity the version-exclusivity of the Pokemon
	 * 
	 * @precondition exclusivity != null
	 * @postcondition getExclusivity() == exclusivity
	 */
	public void setExclusivity(String exclusivity) {
		this.errorOnInvalidExclusivity(exclusivity);
		this.exclusivity = exclusivity;
	}

	/**
	 * Gets the location of the Pokemon.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the location of the Pokemon.
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * Sets the location of the Pokemon.
	 * 
	 * @param location the location of the Pokemon
	 * 
	 * @precondition location != null
	 * @postcondition getLocation() == location
	 */
	public void setLocation(String location) {
		this.errorOnInvalidLocation(location);
		this.location = location;
	}

	/**
	 * Gets the description of the Pokemon.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the description of the Pokemon
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description of the Pokemon.
	 *
	 * @param description the new description for the Pokemon
	 * 
	 * @precondition description != null
	 * @postcondition getDescription() == description
	 */
	public void setDescription(String description) {
		this.errorOnInvalidDescription(description);
		this.description = description;
	}

	/**
	 * Gets the height of the Pokemon.
	 *
	 * @precondition none
	 * @postcondition none
	 *
	 * @return the height of the Pokemon
	 */
	public double getHeight() {
		return height;
	}

	/**
	 * Sets the height of the Pokemon.
	 *
	 * @param height the new height of the Pokemon
	 * 
	 * @precondition height > 0
	 * @postcondition getHeight() == height
	 */
	public void setHeight(double height) {
		this.errorOnInvalidHeight(height);
		this.height = height;
	}

	/**
	 * Gets the weight of the Pokemon.
	 * 
	 * @precondition none
	 * @postcondition none
	 *
	 * @return the weight of the Pokemon
	 */
	public double getWeight() {
		return weight;
	}

	/**
	 * Sets the weight of the Pokemon.
	 *
	 * @param weight the new weight for the Pokemon
	 * 
	 * @precondition weight > 0.0
	 * @postcondition getWeight() == weight
	 */
	public void setWeight(double weight) {
		this.errorOnInvalidWeight(weight);
		this.weight = weight;
	}

	/**
	 * Gets the level up moveset for the Pokemon.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the level up moveset for the Pokemon
	 */
	public Collection<LevelUpMove> getLevelUpMoveset() {
		return this.levelUpMoveset;
	}

	/**
	 * Gets the previous evolution for this Pokemon.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the previous evolution for the Pokemon, or null if there is none
	 */
	public Evolution getPreviousEvolution() {
		return previousEvolution;
	}

	/**
	 * Sets the previous evolution of the Pokemon to the given value.
	 * 
	 * @param previousEvolution the previous evolution to set
	 * 
	 * @precondition none
	 * @postcondition getPreviousEvolution() == previousEvolution
	 */
	public void setPreviousEvolution(Evolution previousEvolution) {
		this.previousEvolution = previousEvolution;
	}

	/**
	 * Gets the post evolutions for this Pokemon.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the post evolutions for this Pokemon
	 */
	public Collection<Evolution> getPostEvolutions() {
		return postEvolutions;
	}

	/**
	 * Adds a post evolution to the Pokemon. Meaning, this Pokemon can now evolve
	 * into the given evolution item.
	 * 
	 * @param postEvolution the post evolution the pokemon can evolve to
	 * 
	 * @precondition postEvolution != null
	 * @postcondition getPostEvolutions().size == getPostEvolutions().size()prev + 1
	 * 
	 * @throws NullpointerException if postEvolution is null
	 */
	public void addPostEvolution(Evolution postEvolution) {
		if (postEvolution == null) {
			throw new NullPointerException(ExceptionText.EVOLUTION_NULL);
		}

		this.postEvolutions.add(postEvolution);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Pokemon pokemon) {
		return this.getPokedexNumber() - pokemon.getPokedexNumber();

	}
}
