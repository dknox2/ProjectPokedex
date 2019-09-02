package edu.westga.cs3212.projectpokedex.view.embed;

import java.util.ArrayList;
import java.util.List;

import edu.westga.cs3212.projectpokedex.model.Pokedex;
import edu.westga.cs3212.projectpokedex.model.pokedexitem.PokedexItem;
import edu.westga.cs3212.projectpokedex.model.pokemon.Pokemon;
import edu.westga.cs3212.projectpokedex.view.CaughtSeenSelection;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Responsible for sorting PokemonEmbeds to be displayed in the MainPage
 * according to the state of the Pokedex in the MainPage.
 * 
 * @author Kyle Riggs, Cody Graham, Dylan Knox, Joseph Fuller
 */
public class PokemonEmbedHandler {

	private final Pokedex pokedex;

	private List<Integer> sortedPokemonInternalIds;

	private ObjectProperty<CaughtSeenSelection> selectedCaughtSeenSelectionProperty;

	private List<PokemonEmbed> allPokemonEmbeds;
	private ObservableList<PokemonEmbed> displayedPokemonEmbeds;
	private ListProperty<PokemonEmbed> displayedPokemonEmbedsProperty;

	/**
	 * Instantiates a new pokedex embed manager.
	 * 
	 * @param pokedex the pokedex to sort embeds for
	 */
	public PokemonEmbedHandler(Pokedex pokedex) {
		this.pokedex = pokedex;
		this.sortedPokemonInternalIds = new ArrayList<Integer>();

		this.allPokemonEmbeds = new ArrayList<PokemonEmbed>();
		this.displayedPokemonEmbeds = FXCollections.observableArrayList();

		this.displayedPokemonEmbedsProperty = new SimpleListProperty<PokemonEmbed>();

		this.selectedCaughtSeenSelectionProperty = new SimpleObjectProperty<CaughtSeenSelection>();

		this.createAllDefaultEmbeds();
	}

	/**
	 * Sorts the Pokedex embeds according to the current status of the Pokedex and
	 * updates corresponding properties.
	 */
	public void updatePokemonEmbeds() {
		this.updateSortedIndicies();
		this.updateDisplayedPokemonEmbeds();

		this.displayedPokemonEmbedsProperty.set(this.displayedPokemonEmbeds);
	}
	
	/**
	 * Flushes any data not created in the embeds currently
	 */
	public void flushInternalEmbeds() {
		for (PokedexItem item : this.pokedex) {
			this.sortedPokemonInternalIds.add(item.getInternalID());
			if(item.getInternalID() >= this.allPokemonEmbeds.size()) {
				this.addPokemonEmbed(item);
			}
		}
	}

	/**
	 * Gets the displayed Pokemon embeds for property binding
	 * 
	 * @return the displayed Pokemon embeds
	 */
	public ObservableList<PokemonEmbed> getDisplayedPokemonEmbeds() {
		return this.displayedPokemonEmbeds;
	}

	/**
	 * Gets the selected CaughtSeenSelection property for filtering the displayed
	 * Pokemon embeds via property binding.
	 * 
	 * @return the selected CaughtSeenSelection property
	 */
	public ObjectProperty<CaughtSeenSelection> getSelectedCaughtSeenSelectionProperty() {
		return this.selectedCaughtSeenSelectionProperty;
	}

	/**
	 * Gets the displayed Pokemon embeds list property for binding
	 * 
	 * @return the displayed Pokemon embeds property
	 */
	public ListProperty<PokemonEmbed> getDisplayedPokemonEmbedsProperty() {
		return this.displayedPokemonEmbedsProperty;
	}

	/**
	 * Adds a PokedexItem to the managed embeds and resorts.
	 * 
	 * @param pokedexItem the pokedexItem to add to the embeds
	 */
	public void addPokemonEmbed(PokedexItem pokedexItem) {
		this.allPokemonEmbeds.add(this.createPokemonEmbed(pokedexItem.getPokemon()));
	}

	private PokemonEmbed createPokemonEmbed(Pokemon pokemon) {
		PokemonEmbed embed;
		if (pokemon.getPostEvolutions() != null) {
			if (pokemon.getPreviousEvolution() != null) {
				embed = new PokemonEmbed(pokemon.getName(), String.valueOf(pokemon.getPokedexNumber()),
						pokemon.getPrimaryType(), pokemon.getSecondaryType(), pokemon.getExclusivity(), pokemon.getLocation(),
						String.valueOf(pokemon.getWeight()), String.valueOf(pokemon.getHeight()), pokemon.getDescription(),
						pokemon.getLevelUpMoveset(), pokemon.getPreviousEvolution().toString(),
						pokemon.getPostEvolutions().toString());
			} else {
				embed = new PokemonEmbed(pokemon.getName(), String.valueOf(pokemon.getPokedexNumber()),
						pokemon.getPrimaryType(), pokemon.getSecondaryType(), pokemon.getExclusivity(), pokemon.getLocation(),
						String.valueOf(pokemon.getWeight()), String.valueOf(pokemon.getHeight()), pokemon.getDescription(),
						pokemon.getLevelUpMoveset(), "",
						pokemon.getPostEvolutions().toString());
			}
		} else {
			if (pokemon.getPreviousEvolution() != null) {
				embed = new PokemonEmbed(pokemon.getName(), String.valueOf(pokemon.getPokedexNumber()),
						pokemon.getPrimaryType(), pokemon.getSecondaryType(), pokemon.getExclusivity(), pokemon.getLocation(),
						String.valueOf(pokemon.getWeight()), String.valueOf(pokemon.getHeight()), pokemon.getDescription(),
						pokemon.getLevelUpMoveset(), pokemon.getPreviousEvolution().toString(),
						"");
			} else {
				embed = new PokemonEmbed(pokemon.getName(), String.valueOf(pokemon.getPokedexNumber()),
						pokemon.getPrimaryType(), pokemon.getSecondaryType(), pokemon.getExclusivity(), pokemon.getLocation(),
						String.valueOf(pokemon.getWeight()), String.valueOf(pokemon.getHeight()), pokemon.getDescription(),
						pokemon.getLevelUpMoveset(), "", "");
			}
		}


		return embed;
	}

	private void createAllDefaultEmbeds() {
		for (PokedexItem item : this.pokedex) {
			PokemonEmbed embed = this.createPokemonEmbed(item.getPokemon());
			this.allPokemonEmbeds.add(embed);
		}

		this.displayedPokemonEmbedsProperty.set(this.displayedPokemonEmbeds);
	}

	private void updateSortedIndicies() {
		this.sortedPokemonInternalIds.clear();

		this.flushInternalEmbeds();
	}

	private void updateDisplayedPokemonEmbeds() {
		this.displayedPokemonEmbeds.clear();
		
		for (int internalId : this.sortedPokemonInternalIds) {
			PokemonEmbed embed = this.allPokemonEmbeds.get(internalId);
			CaughtSeenSelection selectedCaughtSeenSelection = this.selectedCaughtSeenSelectionProperty.get();
			if (embed.getCaughtSeenSelection() == selectedCaughtSeenSelection
					|| selectedCaughtSeenSelection == CaughtSeenSelection.ALL) {
				this.displayedPokemonEmbeds.add(embed);
			}
		}
	}

}
