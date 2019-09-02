package edu.westga.cs3212.projectpokedex.view.embed;

import edu.westga.cs3212.projectpokedex.viewmodel.EmbedHeaderViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * Code-behind file for the embed header view. The embed header is displayed on the list view on the main page, and display
 * the embed data when chosen.
 * 
 * @author Kyle Riggs, Cody Graham, Dylan Knox, Joseph Fuller
 */
public class EmbedHeaderCodeBehind {

	@FXML
	private Label nameLabel;
	@FXML
	private Label type1Label;
	@FXML
	private Label exclusivityLabel;
	@FXML
	private Label idLabel;
	@FXML
	private Label type2Label;
	@FXML
	private Label weightLabel;
	@FXML
	private Label heightLabel;

	private EmbedHeaderViewModel viewModel;

	/**
	 * JavaFx ComponentInitializer.
	 */
	@FXML
	public void initialize() {

		this.viewModel.getNameLabelProperty().bindBidirectional(this.nameLabel.textProperty());
		this.viewModel.getType1LabelProperty().bindBidirectional(this.type1Label.textProperty());
		this.viewModel.getExclusivityLabelProperty().bindBidirectional(this.exclusivityLabel.textProperty());
		this.viewModel.getIdLabelProperty().bindBidirectional(this.idLabel.textProperty());
		
		this.viewModel.getType2LabelProperty().bindBidirectional(this.type2Label.textProperty());
		this.viewModel.getWeightLabelProperty().bindBidirectional(this.weightLabel.textProperty());
		this.viewModel.getHeightLabelProperty().bindBidirectional(this.heightLabel.textProperty());

	}

	/**
	 * Instantiates a new project pokedex embed header code behind.
	 */
	public EmbedHeaderCodeBehind() {
		this.viewModel = new EmbedHeaderViewModel();
	}

	/**
	 * Gets the view model.
	 *
	 * @return the view model
	 */
	public EmbedHeaderViewModel getViewModel() {
		return viewModel;
	}

}