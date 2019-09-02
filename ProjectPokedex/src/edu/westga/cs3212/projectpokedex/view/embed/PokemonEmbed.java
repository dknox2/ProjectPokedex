package edu.westga.cs3212.projectpokedex.view.embed;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Collection;

import edu.westga.cs3212.projectpokedex.io.PokemonIoConstants;
import edu.westga.cs3212.projectpokedex.model.pokemon.moves.LevelUpMove;
import edu.westga.cs3212.projectpokedex.view.CaughtSeenSelection;
import edu.westga.cs3212.projectpokedex.view.utils.FXMLContainer;
import edu.westga.cs3212.projectpokedex.viewmodel.EmbedDataViewModel;
import edu.westga.cs3212.projectpokedex.viewmodel.EmbedHeaderViewModel;
import javafx.collections.FXCollections;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

/**
 * A PokedexItem that contains data and is displayable in a component.
 * 
 * @author Kyle Riggs, Cody Graham, Dylan Knox, Joseph Fuller
 */
public class PokemonEmbed extends Pane {

	private static Image DEFAULT_DATA_IMAGE = null;

	private static final String HEADER_FILE = "embed/EmbedHeaderGui";
	private static final String DATA_BODY_FILE = "embed/EmbedDataGui";

	private boolean showDataPane;

	private FXMLContainer<EmbedHeaderCodeBehind> header;
	private FXMLContainer<EmbedDataCodeBehind> dataBody;

	/**
	 * Instantiates a new pokedex embed.
	 *
	 * @param pokemonName  the pokemon name
	 * @param numberId     the number id
	 * @param type         the type
	 * @param exclusivity  the exclusivity
	 * @param locations    the locations
	 * @param dataSummary  the data summary
	 * @param pokedexEntry the pokedex entry
	 */
	public PokemonEmbed(String pokemonName, String numberId, String type1, String type2, String exclusivity,
			String locations, String weight, String height, String pokedexEntry) {
		this.createPokedexHeaderPane(pokemonName, numberId, type1, type2, weight, height, exclusivity);
		this.createPokedexDataPane(pokemonName, numberId, type1, type2, weight, height, exclusivity, pokedexEntry,
				locations, null);

		this.getChildren().add(this.header.getPane());
		this.showDataPane = false;
	}
	
	/**
	 * Instantiates a new pokedex embed.
	 *
	 * @param pokemonName  the pokemon name
	 * @param numberId     the number id
	 * @param type         the type
	 * @param exclusivity  the exclusivity
	 * @param locations    the locations
	 * @param dataSummary  the data summary
	 * @param pokedexEntry the pokedex entry
	 * @param moveset	   the pokemon moveset
	 */
	public PokemonEmbed(String pokemonName, String numberId, String type1, String type2, String exclusivity,
			String locations, String weight, String height, String pokedexEntry, Collection<LevelUpMove> moveset) {
		this.createPokedexHeaderPane(pokemonName, numberId, type1, type2, weight, height, exclusivity);
		this.createPokedexDataPane(pokemonName, numberId, type1, type2, weight, height, exclusivity, pokedexEntry,
				locations, moveset);

		this.getChildren().add(this.header.getPane());
		this.showDataPane = false;
	}

	/**
	 * Instantiates a new pokedex embed.
	 *
	 * @param pokemonName  the pokemon name
	 * @param numberId     the number id
	 * @param type         the type
	 * @param exclusivity  the exclusivity
	 * @param locations    the locations
	 * @param dataSummary  the data summary
	 * @param pokedexEntry the pokedex entry
	 * @param moveset	   the pokemon moveset
	 * @param evolvesFrom  the pre-evolution data
	 * @param evolvesTo    the post-evolution data
	 */
	public PokemonEmbed(String pokemonName, String numberId, String type1, String type2, String exclusivity,
			String locations, String weight, String height, String pokedexEntry, Collection<LevelUpMove> moveset,
			String evolvesFrom, String evolvesTo) {
		this.createPokedexHeaderPane(pokemonName, numberId, type1, type2, weight, height, exclusivity);
		this.createPokedexDataPane(pokemonName, numberId, type1, type2, weight, height, exclusivity, pokedexEntry,
				locations, moveset, evolvesFrom, evolvesTo);

		this.getChildren().add(this.header.getPane());
		this.showDataPane = false;
	}

	private void createPokedexDataPane(String pokemonName, String numberId, String type1, String type2, String weight,
			String height, String exclusivity, String pokedexEntry, String locations, Collection<LevelUpMove> moveset,
			String evolvesFrom, String evolvesTo) {

		this.dataBody = new FXMLContainer<EmbedDataCodeBehind>(DATA_BODY_FILE);
		this.assignBodyData(pokemonName, numberId, type1, type2, weight, height, exclusivity, pokedexEntry, locations,
				moveset, evolvesFrom, evolvesTo);
	}

	/**
	 * Gets the caught seen selection.
	 *
	 * @return the caught seen selection
	 */
	public CaughtSeenSelection getCaughtSeenSelection() {
		return this.dataBody.getController().getSelectedCaughtSeenSelection();
	}

	/**
	 * Shows the data panel for this entry.
	 */
	public void showPane() {
		this.showDataPane = true;
		this.getChildren().clear();
		this.getChildren().add(this.dataBody.getPane());
	}

	/**
	 * Shows the data panel for this entry.
	 */
	public void hidePane() {
		this.showDataPane = false;
		this.getChildren().clear();
		this.getChildren().add(this.header.getPane());
	}

	/**
	 * Determine if the data panel for this entry is showing currently.
	 * 
	 * @return is showing boolean
	 */
	public boolean isShowingDataPane() {
		return this.showDataPane;
	}

	private void createPokedexHeaderPane(String pokemonName, String numberId, String type1, String type2, String weight,
			String height, String exclusivity) {
		this.header = new FXMLContainer<EmbedHeaderCodeBehind>(HEADER_FILE);
		this.assignHeaderData(pokemonName, numberId, type1, type2, weight, height, exclusivity);
	}

	private void assignHeaderData(String pokemonName, String numberId, String type1, String type2, String weight,
			String height, String exclusivity) {
		EmbedHeaderViewModel viewModel = this.header.getController().getViewModel();
		viewModel.getNameLabelProperty().setValue(pokemonName);
		viewModel.getIdLabelProperty().setValue(numberId);
		viewModel.getType1LabelProperty().setValue(type1);
		viewModel.getType2LabelProperty().setValue(type2);
		viewModel.getWeightLabelProperty().setValue(weight);
		viewModel.getHeightLabelProperty().setValue(height);
		viewModel.getExclusivityLabelProperty().setValue(exclusivity);
	}

	private void createPokedexDataPane(String pokemonName, String numberId, String type1, String type2, String weight,
			String height, String exclusivity, String pokedexEntry, String locations, Collection<LevelUpMove> moveset) {
		this.dataBody = new FXMLContainer<EmbedDataCodeBehind>(DATA_BODY_FILE);
		this.assignBodyData(pokemonName, numberId, type1, type2, weight, height, exclusivity, pokedexEntry, locations,
				moveset);
	}

	private void assignBodyData(String pokemonName, String numberId, String type1, String type2, String weight,
			String height, String exclusivity, String pokedexEntry, String locations, Collection<LevelUpMove> moveset) {
		EmbedDataViewModel viewModel = this.dataBody.getController().getViewModel();

		viewModel.getIdLabelProperty().setValue(numberId);
		viewModel.getNameLabelProperty().setValue(pokemonName);
		viewModel.getWeightLabelProperty().setValue(weight);
		viewModel.getHeightLabelProperty().setValue(height);
		viewModel.getType1LabelProperty().setValue(type1);
		viewModel.getType2LabelProperty().setValue(type2);
		viewModel.getSummaryTextAreaProperty().setValue(pokedexEntry);
		viewModel.getExclusivityProperty().setValue(exclusivity);
		viewModel.getLocationLabelProperty().setValue(locations);

		if (moveset != null) {
			viewModel.getLevelUpMovesetProperty().setValue(FXCollections.observableArrayList(moveset));
		}

		Image val = null;

		try {
			val = new Image(new FileInputStream(PokemonIoConstants.DEFAULT_DATABASE_DIR + "images/" + pokemonName + ".png"));
		} catch (Exception e) {
			if (DEFAULT_DATA_IMAGE == null) {
				try {
					DEFAULT_DATA_IMAGE = new Image(new FileInputStream(PokemonIoConstants.DEFAULT_DATA_IMAGE_URL));
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
			}
			val = DEFAULT_DATA_IMAGE;
		}

		viewModel.getDisplayImageProperty().setValue(val);
		viewModel.getCloseActionProperty().setValue((event) -> {
			this.hidePane();
		});

	}

	private void assignBodyData(String pokemonName, String numberId, String type1, String type2, String weight,
			String height, String exclusivity, String pokedexEntry, String locations, Collection<LevelUpMove> moveset,
			String evolvesFrom, String evolvesTo) {
		this.assignBodyData(pokemonName, numberId, type1, type2, weight, height, exclusivity, pokedexEntry, locations,
				moveset);

		EmbedDataViewModel viewModel = this.dataBody.getController().getViewModel();

		viewModel.getEvolvesFromProperty().setValue(evolvesFrom);
		viewModel.getEvolvesToProperty().setValue(evolvesTo);
	}

}
