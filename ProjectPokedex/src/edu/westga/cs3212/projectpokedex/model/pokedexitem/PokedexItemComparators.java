package edu.westga.cs3212.projectpokedex.model.pokedexitem;

import java.util.Comparator;

/**
 * Contains constant comparators for sorting Pokemon data.
 * 
 * @author Kyle Riggs, Cody Graham, Dylan Knox, Joseph Fuller 
 */
public class PokedexItemComparators {

	/** Comparator to compare by internal id. */
	public static final Comparator<PokedexItem> COMPARE_BY_INTERNAL_ID = (pokemon1, pokemon2) -> {
		int value0 = pokemon1.getInternalID();
		int value1 = pokemon2.getInternalID();
		return Integer.compare(value0, value1);
	};
	
	/** Comparator to compare by name, lexicographically. */
	public static final Comparator<PokedexItem> COMPARE_BY_NAME = (pokemon1, pokemon2) -> {
		String value0 = pokemon1.getPokemon().getName();
		String value1 = pokemon2.getPokemon().getName();
		return value0.compareTo(value1);
	};
	
	/** Comparator to compare by weight, ascending order. */
	public static final Comparator<PokedexItem> COMPARE_BY_WEIGHT_ASCENDING = (pokemon1, pokemon2) -> {
		double value0 = pokemon1.getPokemon().getWeight();
		double value1 = pokemon2.getPokemon().getWeight();
		
		int value = Double.compare(value0, value1);
		value = additiveTwoLevelSort(pokemon1,pokemon2,value);
		return value;
	};

	/** Comparator to compare by height, ascending order. */
	public static final Comparator<PokedexItem> COMPARE_BY_HEIGHT_ASCENDING = (pokemon1, pokemon2) -> {
		double value0 = pokemon1.getPokemon().getHeight();
		double value1 = pokemon2.getPokemon().getHeight();

		int value = Double.compare(value0, value1);
		value = additiveTwoLevelSort(pokemon1,pokemon2,value);
		return value;
	};
	
	/** Comparator to compare by location. */
	public static final Comparator<PokedexItem> COMPARE_BY_LOCATION = (pokemon1, pokemon2) -> {
		String value0 = pokemon1.getPokemon().getLocation();
		String value1 = pokemon2.getPokemon().getLocation();
		return value0.compareTo(value1);
	};
	
	/** Comparator to compare by the id number. */
	public static final Comparator<PokedexItem> COMPARE_BY_POKEDEX_NUMBER= (pokemon1, pokemon2) -> {
		int value0 = pokemon1.getPokemon().getPokedexNumber();
		int value1 = pokemon2.getPokemon().getPokedexNumber();
		return Integer.compare(value0, value1);
	};
	
	/** Comparator to compare by the first pokemon type. */
	public static final Comparator<PokedexItem> COMPARE_BY_PRIMARY_TYPE = (pokemon1, pokemon2) -> {
		String value0 = pokemon1.getPokemon().getPrimaryType();
		String value1 = pokemon2.getPokemon().getPrimaryType();
		
		int value = value0.compareTo(value1);
		value = additiveTwoLevelSort(pokemon1,pokemon2,value);
		
		return value;
	};
	
	/** Comparator to compare by the second pokemon type. */
	public static final Comparator<PokedexItem> COMPARE_BY_SECONDARY_TYPE = (pokemon1, pokemon2) -> {
		String value0 = pokemon1.getPokemon().getSecondaryType();
		String value1 = pokemon2.getPokemon().getSecondaryType();
		
		if (value0 == null) {
			if (value1 == null) {
				return 0;
			}
			return Integer.MIN_VALUE;
		}
		
		if (value1 == null) {
			return Integer.MAX_VALUE;
		}
		
		int value = value0.compareTo(value1);
		value = additiveTwoLevelSort(pokemon1,pokemon2,value);
		
		return value;
	};
	
	private static int additiveTwoLevelSort(PokedexItem a, PokedexItem b, int value) {
		if(value == 0) {
			value = COMPARE_BY_POKEDEX_NUMBER.compare(a, b);
		}
		return value;
	}

}
