package edu.westga.cs3212.projectpokedex.viewmodel;

import edu.westga.cs3212.projectpokedex.io.database.PokemonDatabase;
import edu.westga.cs3212.projectpokedex.logger.LoggerSingleton;
import edu.westga.cs3212.projectpokedex.model.Pokedex;
import edu.westga.cs3212.projectpokedex.model.pokemon.Pokemon;
import edu.westga.cs3212.projectpokedex.model.pokemon.evolution.Evolution;
import edu.westga.cs3212.projectpokedex.view.PokedexSortingItem;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * View-model for the MainPageCodeBehind.
 *
 * @author Kyle Riggs, Cody Graham, Dylan Knox, Joseph Fuller
 */
public class MainPageViewModel {

	private SimpleObjectProperty<PokedexSortingItem> selectedSortingEntryProperty;
	private BooleanProperty sortDescendingProperty;

	private Pokedex pokedex;
	private PokemonDatabase database;

	/**
	 * Instantiates a new MainPageViewModel
	 * 
	 * @precondition none
	 * @postcondition getPokedex() != null && getSelectedSortingEntryProperty() !=
	 *                null
	 */
	public MainPageViewModel() {
		this.selectedSortingEntryProperty = new SimpleObjectProperty<PokedexSortingItem>();
		this.sortDescendingProperty = new SimpleBooleanProperty();

		this.pokedex = new Pokedex();
		this.database = new PokemonDatabase();

		this.startPolling();
	}


	/**
	 * Loads Pokemon from the database into the Pokedex.
	 * 
	 * @precondition none
	 * @postcondition none
	 */
	public void loadDataFromDatabase() {
		for (Pokemon pokemon : this.database.loadDatabase()) {
			try {
				this.pokedex.add(pokemon);
			} catch (Exception e) {
				LoggerSingleton.getInstance().log("Exception thrown in MainPageViewModel::loadDataFromDatabase()");
			}
		}
	}

	/**
	 * Adds a Pokemon to the managed Pokedex and to the local database.
	 *
	 * @param pokemon the pokemon
	 */
	public boolean addPokemon(Pokemon pokemon) {
		boolean success = false;
		try {
			success = this.database.distributeNewPokemon(pokemon);
		} catch (Exception e) {
		}
		
		return success;
	}

	/**
	 * Sorts the Pokedex according to the currently bound sorting entry. Defaults to
	 * comparing by Pokedex number.
	 * 
	 * @precondition none
	 * @postcondition none
	 */
	public void sortPokedex() {
		PokedexSortingItem sortingItem = this.selectedSortingEntryProperty.get();

		if (sortingItem != null) {
			this.pokedex.sort(sortingItem.getComparator());
		} else {
			this.pokedex.sort();
		}

		if (this.sortDescendingProperty.get()) {
			this.pokedex.reverse();
		}
	}

	/**
	 * Gets the Pokedex from this view-model.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the pokedex
	 */
	public Pokedex getPokedex() {
		return this.pokedex;
	}

	/**
	 * Gets the selected sorting entry property.
	 * 
	 * @precondition none
	 * @postcondition none
	 *
	 * @return the selected sorting entry property
	 */
	public SimpleObjectProperty<PokedexSortingItem> getSelectedSortingEntryProperty() {
		return this.selectedSortingEntryProperty;
	}

	/**
	 * Gets the sort descending property.
	 * 
	 * @precondition none
	 * @postcondition none
	 *
	 * @return the sort descending property
	 */
	public BooleanProperty getSortDescendingProperty() {
		return this.sortDescendingProperty;
	}

	/**
	 * Method used to add evolution data to demo evolutions feature.
	 * 
	 * This method can be removed when better evolution data is web scraped.
	 */
	public void addSampleEvolutions() {
		try {
			Pokemon ivysaur = this.pokedex.findPokemonByNumber(2);
			this.pokedex.addPostEvolution(1, new Evolution(ivysaur, "Level 16"));

			Pokemon venusaur = this.pokedex.findPokemonByNumber(3);
			this.pokedex.addPostEvolution(2, new Evolution(venusaur, "Level 32"));

			Pokemon charmeleon = this.pokedex.findPokemonByNumber(5);
			this.pokedex.addPostEvolution(4, new Evolution(charmeleon, "Level 16"));

			Pokemon charizard = this.pokedex.findPokemonByNumber(6);
			this.pokedex.addPostEvolution(5, new Evolution(charizard, "Level 36"));

			Pokemon wartortle = this.pokedex.findPokemonByNumber(8);
			this.pokedex.addPostEvolution(7, new Evolution(wartortle, "Level 16"));

			Pokemon blastoise = this.pokedex.findPokemonByNumber(9);
			this.pokedex.addPostEvolution(8, new Evolution(blastoise, "Level 36"));

			Pokemon gloom = this.pokedex.findPokemonByNumber(44);
			this.pokedex.addPostEvolution(43, new Evolution(gloom, "Level 21"));

			Pokemon vileplume = this.pokedex.findPokemonByNumber(45);
			this.pokedex.addPostEvolution(44, new Evolution(vileplume, "Leaf Stone"));

			Pokemon vaporeon = this.pokedex.findPokemonByNumber(134);
			this.pokedex.addPostEvolution(133, new Evolution(vaporeon, "Water Stone"));

			Pokemon jolteon = this.pokedex.findPokemonByNumber(135);
			this.pokedex.addPostEvolution(133, new Evolution(jolteon, "Thunderstone"));

			Pokemon flareon = this.pokedex.findPokemonByNumber(136);
			this.pokedex.addPostEvolution(133, new Evolution(flareon, "Fire Stone"));

			Pokemon sandslash = this.pokedex.findPokemonByNumber(28);
			this.pokedex.addPostEvolution(27, new Evolution(sandslash, "Level 22"));

			Pokemon haunter = this.pokedex.findPokemonByNumber(93);
			this.pokedex.addPostEvolution(92, new Evolution(haunter, "Level 25"));

			Pokemon gengar = this.pokedex.findPokemonByNumber(94);
			this.pokedex.addPostEvolution(93, new Evolution(gengar, "Trade"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void startPolling() {
		Thread poll = new Thread(() -> {
			int lastAdded = 0;
			while (true) {
				try {

					while (lastAdded != this.database.getCreatedPokemon().size()) {
						try {
							this.addPokemon(this.database.getCreatedPokemon().get(lastAdded));
						} catch (Exception e) {
							e.printStackTrace();
						}
						lastAdded++;
					}

					Thread.sleep(500);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		poll.setDaemon(true);
		poll.start();
	}
}
