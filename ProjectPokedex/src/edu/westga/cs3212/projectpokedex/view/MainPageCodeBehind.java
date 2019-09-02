package edu.westga.cs3212.projectpokedex.view;

import edu.westga.cs3212.projectpokedex.logger.LoggerSingleton;
import edu.westga.cs3212.projectpokedex.model.pokedexitem.PokedexItem;
import edu.westga.cs3212.projectpokedex.model.pokedexitem.PokedexItemComparators;
import edu.westga.cs3212.projectpokedex.model.pokemon.Pokemon;
import edu.westga.cs3212.projectpokedex.view.embed.PokemonEmbed;
import edu.westga.cs3212.projectpokedex.view.embed.PokemonEmbedHandler;
import edu.westga.cs3212.projectpokedex.viewmodel.MainPageViewModel;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Code-behind file for the main page for Project Pokedex.
 * 
 * @author Kyle Riggs, Cody Graham, Dylan Knox, Joseph Fuller
 */
public class MainPageCodeBehind {

	private static final String ADD_POKEMON_GUI = "AddPokemonGui.fxml";

	@FXML
	private ComboBox<PokedexSortingItem> sortComboBox;

	@FXML
	private ComboBox<CaughtSeenSelection> displayComboBox;

	@FXML
	private ListView<PokemonEmbed> pokedexListView;

	@FXML
	private Button addPokemonButton;

	@FXML
	private CheckBox sortDescendingCheckBox;

	private MainPageViewModel viewModel;
	private PokemonEmbedHandler embedHandler;

	/**
	 * Initializer for the fxml data
	 */
	@FXML
	public void initialize() {
		this.embedHandler.getSelectedCaughtSeenSelectionProperty()
				.bind(this.displayComboBox.getSelectionModel().selectedItemProperty());

		this.pokedexListView.itemsProperty().bindBidirectional(this.embedHandler.getDisplayedPokemonEmbedsProperty());

		this.sortDescendingCheckBox.selectedProperty().bindBidirectional(this.viewModel.getSortDescendingProperty());

		this.initializeSortComboBox();
		this.initializeDisplayComboBox();

		this.addListeners();

		this.handleUpdatePokedexListView();
	}

	/**
	 * Instantiates a new MainPageCodeBehind.
	 */
	public MainPageCodeBehind() {
		this.viewModel = new MainPageViewModel();
		this.viewModel.loadDataFromDatabase();
		this.viewModel.addSampleEvolutions();
		this.embedHandler = new PokemonEmbedHandler(this.viewModel.getPokedex());
	}

	/**
	 * Sorts the Pokedex and updates the embeds to the newly sorted Pokedex with the
	 * selected display option.
	 */
	public void handleUpdatePokedexListView() {
		this.viewModel.sortPokedex();
		this.embedHandler.updatePokemonEmbeds();
	}

	/**
	 * Opens the Add Pokemon dialog, and adds the Pokemon and sorts if a Pokemon was
	 * added successfully.
	 */
	public void handleOpenAddPokemonView() {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(ADD_POKEMON_GUI));
			Parent root1 = (Parent) fxmlLoader.load();
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setTitle("Add Pokemon");
			stage.setScene(new Scene(root1));

			AddPokemonCodeBehind codeBehind = (AddPokemonCodeBehind) fxmlLoader.getController();
			codeBehind.setPokedex(viewModel.getPokedex());
			stage.show();

			stage.setOnHiding((winEvent) -> {
				if (codeBehind.isConfirmButtonPressed()) {
					Pokemon addedPokemon = codeBehind.getAddedPokemon();
					if (addedPokemon != null) {
						boolean added = viewModel.addPokemon(addedPokemon);
						if (added) {
							this.addStatusAlert("Pokemon successfully distributed", AlertType.INFORMATION);
						} else {
							this.addStatusAlert("Pokemon failed to be successfully distributed", AlertType.ERROR);
						}
						embedHandler.addPokemonEmbed(new PokedexItem(addedPokemon));
						handleUpdatePokedexListView();
						LoggerSingleton.getInstance().log(System.currentTimeMillis() + ": Pokemon added");
					}
				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void initializeSortComboBox() {
		ObservableList<PokedexSortingItem> comboBoxItems = this.sortComboBox.getItems();

		PokedexSortingItem sortingItem = new PokedexSortingItem("By Pokedex Number",
				PokedexItemComparators.COMPARE_BY_POKEDEX_NUMBER);
		comboBoxItems.add(sortingItem);

		sortingItem = new PokedexSortingItem("By Name", PokedexItemComparators.COMPARE_BY_NAME);
		comboBoxItems.add(sortingItem);

		sortingItem = new PokedexSortingItem("By Primary Type", PokedexItemComparators.COMPARE_BY_PRIMARY_TYPE);
		comboBoxItems.add(sortingItem);

		sortingItem = new PokedexSortingItem("By Secondary Type", PokedexItemComparators.COMPARE_BY_SECONDARY_TYPE);
		comboBoxItems.add(sortingItem);

		sortingItem = new PokedexSortingItem("By Height", PokedexItemComparators.COMPARE_BY_HEIGHT_ASCENDING);
		comboBoxItems.add(sortingItem);

		sortingItem = new PokedexSortingItem("By Weight", PokedexItemComparators.COMPARE_BY_WEIGHT_ASCENDING);
		comboBoxItems.add(sortingItem);

		sortingItem = new PokedexSortingItem("By Location", PokedexItemComparators.COMPARE_BY_LOCATION);
		comboBoxItems.add(sortingItem);

		this.sortComboBox.getSelectionModel().selectFirst();
		this.viewModel.getSelectedSortingEntryProperty()
				.bind(this.sortComboBox.getSelectionModel().selectedItemProperty());
	}

	private void initializeDisplayComboBox() {
		ObservableList<CaughtSeenSelection> comboBoxItems = this.displayComboBox.getItems();
		comboBoxItems.addAll(CaughtSeenSelection.values());
		this.displayComboBox.getSelectionModel().selectFirst();
	}

	private void addListeners() {
		this.pokedexListView.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> {
					if (oldValue != newValue && oldValue != null) {
						oldValue.hidePane();
					}
					if (newValue != null) {
						newValue.showPane();
					}
				});
	}

	private void addStatusAlert(String message, AlertType icon) {
		Alert alert = new Alert(icon);
		alert.setTitle("Add Pokemon Status");
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}


}
