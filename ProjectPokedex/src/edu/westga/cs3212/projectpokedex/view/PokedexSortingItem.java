package edu.westga.cs3212.projectpokedex.view;

import java.util.Comparator;

import edu.westga.cs3212.projectpokedex.model.pokedexitem.PokedexItem;

/**
 * Encapsulates a display name with a PokedexItemComparator for display in JavaFX.
 * 
 * @author Kyle Riggs, Cody Graham, Dylan Knox, Joseph Fuller 
 */
public class PokedexSortingItem {
	
	private Comparator<PokedexItem> comparator;
	private String displayName;
	
	/**
	 * Instantiates a new pokemon sorting item.
	 *
	 * @param displayName the display name
	 * @param comparator the comparator
	 */
	public PokedexSortingItem(String displayName, Comparator<PokedexItem> comparator){
		if(displayName==null) {
			this.displayName = "";
		}else {
			this.displayName = displayName;
		}
		this.comparator = comparator;
	}

	/**
	 * Gets the comparator.
	 *
	 * @return the comparator
	 */
	public Comparator<PokedexItem> getComparator() {
		return this.comparator;
	}
	
	/**
	 * Gets the display name.
	 *
	 * @return the display name
	 */
	public String getDisplayName() {
		return this.displayName;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.displayName != null? this.displayName : super.toString();
	}

}
