package edu.westga.cs3212.projectpokedex.view.embed;

import edu.westga.cs3212.projectpokedex.model.pokemon.moves.LevelUpMove;
import edu.westga.cs3212.projectpokedex.view.CaughtSeenSelection;
import edu.westga.cs3212.projectpokedex.viewmodel.EmbedDataViewModel;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;

/**
 * Code-behind file for the embed data view. The embed data is the view displayed when a Pokemon header is selected
 * from the list view on the main page.
 * 
 * @author Kyle Riggs, Cody Graham, Dylan Knox, Joseph Fuller 
 */
public class EmbedDataCodeBehind {
	
    @FXML
    private ImageView imageDisplay;
    
    @FXML
    private TextArea summaryDisplay;
    
    @FXML
    private Label idLabel;
    
    @FXML
    private Label nameLabel;
    
    @FXML
    private Label heightLabel;
    
    @FXML
    private Label locationLabel;
    
    @FXML
    private Label exclusivityLabel;
    
    @FXML
    private Label weightLabel;
    
    @FXML
    private Label type1Label;
    
    @FXML
    private Label type2Label;
    
    @FXML
    private Label evolvesFromLabel;
    
    @FXML
    private Label evolvesToLabel;
    
    @FXML
    private Button closeButton;
    
    @FXML
    private ListView<LevelUpMove> movesetListView;
    
    @FXML
    private ComboBox<CaughtSeenSelection> caughtSeenComboBox;
    
    private EmbedDataViewModel viewModel;
    
    /**
     * JavaFx ComponentInitializer.
     */
    @FXML
	public void initialize() {
    	this.bindProperties();
    	this.initializeCaughtSeenComboBox();
	}
    
    /**
     * Instantiates a new project pokedex embed data code behind.
     */
    public EmbedDataCodeBehind() {
    	this.viewModel = new EmbedDataViewModel();
	}

	/**
	 * Gets the view model.
	 *
	 * @return the view model
	 */
	public EmbedDataViewModel getViewModel() {
		return viewModel;
	}
	   
	/**
	 * Gets the selected caught seen selection.
	 *
	 * @return the selected caught seen selection
	 */
	public CaughtSeenSelection getSelectedCaughtSeenSelection() {
		return this.caughtSeenComboBox.getSelectionModel().getSelectedItem();
	}
	
	private void bindProperties() {
    	this.viewModel.getDisplayImageProperty().bindBidirectional(this.imageDisplay.imageProperty());
    	this.viewModel.getNameLabelProperty().bindBidirectional(this.nameLabel.textProperty());
    	this.viewModel.getIdLabelProperty().bindBidirectional(this.idLabel.textProperty());
    	this.viewModel.getExclusivityProperty().bindBidirectional(this.exclusivityLabel.textProperty());
    	this.viewModel.getType1LabelProperty().bindBidirectional(this.type1Label.textProperty());
    	this.viewModel.getType2LabelProperty().bindBidirectional(this.type2Label.textProperty());
    	this.viewModel.getSummaryTextAreaProperty().bindBidirectional(this.summaryDisplay.textProperty());
    	this.viewModel.getWeightLabelProperty().bindBidirectional(this.weightLabel.textProperty());
    	this.viewModel.getHeightLabelProperty().bindBidirectional(this.heightLabel.textProperty());
    	this.viewModel.getLocationLabelProperty().bindBidirectional(this.locationLabel.textProperty());
    	this.viewModel.getCloseActionProperty().bindBidirectional(this.closeButton.onActionProperty());
    	this.viewModel.getLevelUpMovesetProperty().bindBidirectional(this.movesetListView.itemsProperty());
    	
    	this.viewModel.getEvolvesFromProperty().bindBidirectional(this.evolvesFromLabel.textProperty());
    	this.viewModel.getEvolvesToProperty().bindBidirectional(this.evolvesToLabel.textProperty());
	}
	
	private void initializeCaughtSeenComboBox() {
		ObservableList<CaughtSeenSelection> comboBoxItems = this.caughtSeenComboBox.getItems();
		
    	comboBoxItems.add(CaughtSeenSelection.UNSEEN);
    	comboBoxItems.add(CaughtSeenSelection.SEEN);
    	comboBoxItems.add(CaughtSeenSelection.CAUGHT);
    	
    	this.caughtSeenComboBox.getSelectionModel().selectFirst();
	}
}
