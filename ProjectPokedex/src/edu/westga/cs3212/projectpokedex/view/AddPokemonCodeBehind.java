/*
 * 
 */
package edu.westga.cs3212.projectpokedex.view;

import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

import edu.westga.cs3212.projectpokedex.model.Pokedex;
import edu.westga.cs3212.projectpokedex.model.pokemon.Exclusivity;
import edu.westga.cs3212.projectpokedex.model.pokemon.Pokemon;
import edu.westga.cs3212.projectpokedex.model.pokemon.Type;
import edu.westga.cs3212.projectpokedex.utils.ExceptionText;
import edu.westga.cs3212.projectpokedex.viewmodel.AddPokemonViewModel;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextFormatter.Change;
import javafx.stage.Stage;

/**
 * Code-behind file for the AddPokemon window.
 * 
 * @author Kyle Riggs, Cody Graham, Dylan Knox, Joseph Fuller
 */
public class AddPokemonCodeBehind {

	@FXML
	private TextField nameTextField;

	@FXML
	private TextField idNumberTextField;
	
	@FXML
	private ComboBox<Type> primaryTypeComboBox;
	
	@FXML
	private ComboBox<Type> secondaryTypeComboBox;
	
	@FXML
	private TextArea descriptionTextArea;
	
	@FXML
	private TextField heightTextField;
	
	@FXML
	private TextField weightTextField;
	
	@FXML
	private TextField locationTextField;
	
	@FXML
	private ComboBox<Exclusivity> exclusivityComboBox;
	
	@FXML
	private Button confirmButton;
	
	@FXML
	private Button cancelButton;
	
	private AddPokemonViewModel viewModel;
	
	private boolean confirmButtonPressed;
	
	private UnaryOperator<Change> filterIntegers = change -> {
	    Pattern pattern = Pattern.compile("\\d*");
	    return pattern.matcher(change.getControlNewText()).matches() ? change : null;
	};
	
	private UnaryOperator<Change> filterDecimals = change -> {
	    Pattern pattern = Pattern.compile("\\d*|\\d+\\.\\d*");
	    return pattern.matcher(change.getControlNewText()).matches() ? change : null;
	};

	/**
	 * Instantiates a new adds the pokemon code behind.
	 */
	public AddPokemonCodeBehind() {
		this.viewModel = new AddPokemonViewModel();
		
		this.confirmButtonPressed = false;
	}
	
	/** Gets the added Pokemon.
	 * 
	 * @return the added Pokemon, or null if the Pokemon is invalid or not chosen.
	 */
	public Pokemon getAddedPokemon() {
		return this.viewModel.getPokemon();
	}
	
	/**
	 * Initializer for the fxml data
	 */
	@FXML
	public void initialize() {
		this.bindProperties();
		this.initializeTextFieldFormatters();
		this.initializeComboBoxes();
	}
	
	/**
	 * Sets the pokedex.
	 *
	 * @param pokedex the new pokedex
	 */
	public void setPokedex(Pokedex pokedex) {
		this.viewModel.setPokedex(pokedex);
	}
	
	/**
	 * Close window.
	 */
	public void closeWindow() {
		Stage stage = (Stage) this.cancelButton.getScene().getWindow();
	    stage.close();
	}
	
	/**
	 * Confirm and close window.
	 */
	public void confirmAndCloseWindow() {
		try {
			Pokemon pokemon = this.viewModel.getPokemon();
			if(pokemon == null) {
				return;
			}
			if (this.viewModel.getPokedex().containsPokedexNumber(pokemon.getPokedexNumber())) {
				this.confirmButtonPressed = false;
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Could not add Pokemon");
				alert.setContentText(ExceptionText.DUPLICATE_POKEDEX_NUMBERS);
				alert.showAndWait();
			} else {
				this.confirmButtonPressed = true;
				this.closeWindow();
			}
		}catch(Exception e) {
			e.printStackTrace();
			System.err.println("Critical Error Ignored");
		}
	}
	
	public boolean isConfirmButtonPressed() {
		return this.confirmButtonPressed;
	}
	
	private void bindProperties() {
		this.viewModel.getNameProperty().bindBidirectional(this.nameTextField.textProperty());
		this.viewModel.getIdNumberProperty().bindBidirectional(this.idNumberTextField.textProperty());
		
		this.viewModel.getPrimaryTypeProperty().bind(this.primaryTypeComboBox.getSelectionModel().selectedItemProperty());
		this.viewModel.getSecondaryTypeProperty().bind(this.secondaryTypeComboBox.getSelectionModel().selectedItemProperty());
		
		this.viewModel.getDescriptionProperty().bindBidirectional(this.descriptionTextArea.textProperty());
		
		this.viewModel.getHeightProperty().bindBidirectional(this.heightTextField.textProperty());
		this.viewModel.getWeightProperty().bindBidirectional(this.weightTextField.textProperty());
		
		this.viewModel.getLocationProperty().bindBidirectional(this.locationTextField.textProperty());
		
		this.viewModel.getExclusivityProperty().bind(this.exclusivityComboBox.getSelectionModel().selectedItemProperty());
	}
	
	
	private void initializeTextFieldFormatters() {
		this.idNumberTextField.setTextFormatter(new TextFormatter<Change>(this.filterIntegers));
		
		this.heightTextField.setTextFormatter(new TextFormatter<Change>(this.filterDecimals));
		this.weightTextField.setTextFormatter(new TextFormatter<Change>(this.filterDecimals));
	}
	
	private void initializeComboBoxes() {
		this.initializePrimaryTypeComboBox();
		this.initializeSecondaryTypeComboBox();
		this.initializeExclusivityComboBox();
	}
	
	private void initializePrimaryTypeComboBox() {
		ObservableList<Type> comboBoxItems = this.primaryTypeComboBox.getItems();
		
		comboBoxItems.addAll(Type.values());
		
		this.primaryTypeComboBox.getSelectionModel().selectFirst();
	}
	
	private void initializeSecondaryTypeComboBox() {
		ObservableList<Type> comboBoxItems = this.secondaryTypeComboBox.getItems();
		
		comboBoxItems.add(null);
		comboBoxItems.addAll(Type.values());
		
		this.secondaryTypeComboBox.getSelectionModel().selectFirst();
	}
	
	private void initializeExclusivityComboBox() {
		ObservableList<Exclusivity> comboBoxItems = this.exclusivityComboBox.getItems();
		
		comboBoxItems.addAll(Exclusivity.values());
		
		this.exclusivityComboBox.getSelectionModel().select(Exclusivity.BOTH);
	}
	
}
