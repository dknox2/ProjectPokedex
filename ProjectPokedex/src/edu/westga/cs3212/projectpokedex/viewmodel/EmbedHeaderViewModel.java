package edu.westga.cs3212.projectpokedex.viewmodel;

import javafx.beans.property.SimpleStringProperty;

/**
 * The Class ProjectPokedexEmbedHeaderViewModel.
 * 
 * @author Kyle Riggs, Cody Graham, Dylan Knox, Joseph Fuller 
 */
public class EmbedHeaderViewModel {
	
	private SimpleStringProperty nameLabel;
	private SimpleStringProperty idLabel;
	private SimpleStringProperty type1Label;
	private SimpleStringProperty exclusivityLabel;
	private SimpleStringProperty weightLabel;
	private SimpleStringProperty type2Label;
	private SimpleStringProperty heightyLabel;
	

	/**
	 * Instantiates a new project pokedex embed header view model.
	 */
	public EmbedHeaderViewModel() {
		
		this.nameLabel = new SimpleStringProperty();
		this.idLabel = new SimpleStringProperty();
		this.type1Label = new SimpleStringProperty();
		this.exclusivityLabel = new SimpleStringProperty();
		this.type2Label = new SimpleStringProperty();
		this.weightLabel = new SimpleStringProperty();
		this.heightyLabel = new SimpleStringProperty();
			
	}

	/**
	 * Gets the name label property.
	 *
	 * @return the name label property
	 */
	public SimpleStringProperty getNameLabelProperty() {
		return this.nameLabel;
	}

	/**
	 * Gets the id label property.
	 *
	 * @return the id label property
	 */
	public SimpleStringProperty getIdLabelProperty() {
		return this.idLabel;
	}

	/**
	 * Gets the type2 label property.
	 *
	 * @return the type2 label property
	 */
	public SimpleStringProperty getType2LabelProperty() {
		return this.type2Label;
	}
	/**
	 * Gets the type1 label property.
	 *
	 * @return the type1 label property
	 */
	public SimpleStringProperty getType1LabelProperty() {
		return this.type1Label;
	}
	

	/**
	 * Gets the exclusivity label property.
	 *
	 * @return the exclusivity label property
	 */
	public SimpleStringProperty getExclusivityLabelProperty() {
		return this.exclusivityLabel;
	}
	
	/**
	 * Gets the height label property.
	 *
	 * @return the height label property
	 */
	public SimpleStringProperty getHeightLabelProperty() {
		return this.heightyLabel;
	}
	
	/**
	 * Gets the weight label property.
	 *
	 * @return the weight label property
	 */
	public SimpleStringProperty getWeightLabelProperty() {
		return this.weightLabel;
	}

}
