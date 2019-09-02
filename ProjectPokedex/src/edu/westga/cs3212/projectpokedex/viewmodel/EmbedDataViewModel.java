/*
 * 
 */
package edu.westga.cs3212.projectpokedex.viewmodel;

import java.util.Collection;

import edu.westga.cs3212.projectpokedex.model.pokemon.moves.LevelUpMove;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;

/**
 * ViewModel for the embed data code behind. The embed data view is displayed
 * after clicking on a header, and contains the bulk of Pokemon information.
 * 
 * @author Kyle Riggs, Cody Graham, Dylan Knox, Joseph Fuller
 */
public class EmbedDataViewModel {

	private ObjectProperty<EventHandler<ActionEvent>> closeActionProperty;
	private ObjectProperty<Image> displayImage;
	private SimpleStringProperty idLabel;
	private SimpleStringProperty nameLabel;
	private SimpleStringProperty heightLabel;
	private SimpleStringProperty locationLabel;
	private SimpleStringProperty exclusivityLabel;
	private SimpleStringProperty weightLabel;
	private SimpleStringProperty type1Label;
	private SimpleStringProperty type2Label;
	private SimpleStringProperty summaryTextArea;

	private StringProperty evolvesFromProperty;
	private StringProperty evolvesToProperty;

	private ListProperty<LevelUpMove> levelUpMovesetProperty;

	/**
	 * Instantiates a new project pokedex embed data view model.
	 */
	public EmbedDataViewModel() {

		this.displayImage = new SimpleObjectProperty<Image>();
		this.closeActionProperty = new SimpleObjectProperty<EventHandler<ActionEvent>>();

		this.idLabel = new SimpleStringProperty();
		this.nameLabel = new SimpleStringProperty();
		this.heightLabel = new SimpleStringProperty();
		this.locationLabel = new SimpleStringProperty();
		this.exclusivityLabel = new SimpleStringProperty();
		this.weightLabel = new SimpleStringProperty();
		this.type1Label = new SimpleStringProperty();
		this.type2Label = new SimpleStringProperty();
		this.summaryTextArea = new SimpleStringProperty();

		this.evolvesFromProperty = new SimpleStringProperty();
		this.evolvesToProperty = new SimpleStringProperty();

		this.levelUpMovesetProperty = new SimpleListProperty<LevelUpMove>();
	}

	public void setMoveset(Collection<LevelUpMove> moveset) {
		this.levelUpMovesetProperty.set(FXCollections.observableArrayList(moveset));
	}

	/**
	 * Sets the display image.
	 *
	 * @param img the new display image
	 */
	public void setDisplayImage(Image img) {
		this.displayImage.setValue(img);
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
	 * Gets the name label property.
	 *
	 * @return the name label property
	 */
	public SimpleStringProperty getNameLabelProperty() {
		return this.nameLabel;
	}

	/**
	 * Gets the height label property.
	 *
	 * @return the height label property
	 */
	public SimpleStringProperty getHeightLabelProperty() {
		return this.heightLabel;
	}

	/**
	 * Gets the location label property.
	 *
	 * @return the location label property
	 */
	public SimpleStringProperty getLocationLabelProperty() {
		return this.locationLabel;
	}

	/**
	 * Gets the exclusivity property.
	 *
	 * @return the exclusivity property
	 */
	public SimpleStringProperty getExclusivityProperty() {
		return this.exclusivityLabel;
	}

	/**
	 * Gets the weight label property.
	 *
	 * @return the weight label property
	 */
	public SimpleStringProperty getWeightLabelProperty() {
		return this.weightLabel;
	}

	/**
	 * Gets the type 1 label property.
	 *
	 * @return the type 1 label property
	 */
	public SimpleStringProperty getType1LabelProperty() {
		return this.type1Label;
	}

	/**
	 * Gets the type 2 label property.
	 *
	 * @return the type 2 label property
	 */
	public SimpleStringProperty getType2LabelProperty() {
		return this.type2Label;
	}

	/**
	 * Gets the summary text area property.
	 *
	 * @return the summary text area property
	 */
	public SimpleStringProperty getSummaryTextAreaProperty() {
		return this.summaryTextArea;
	}

	/**
	 * Gets the display image property.
	 *
	 * @return the display image property
	 */
	public ObjectProperty<Image> getDisplayImageProperty() {
		return this.displayImage;
	}

	/**
	 * Gets the evolves from property
	 *
	 * @return the evolves from property
	 */
	public StringProperty getEvolvesFromProperty() {
		return this.evolvesFromProperty;
	}

	/**
	 * Gets the evolves to property
	 *
	 * @return the evolves to property
	 */
	public StringProperty getEvolvesToProperty() {
		return this.evolvesToProperty;
	}

	/**
	 * Gets the level up moveset property.
	 * 
	 * @return the level up moveset property
	 */
	public ListProperty<LevelUpMove> getLevelUpMovesetProperty() {
		return this.levelUpMovesetProperty;
	}

	/**
	 * Gets the close action property.
	 *
	 * @return the close action property
	 */
	public ObjectProperty<EventHandler<ActionEvent>> getCloseActionProperty() {
		return this.closeActionProperty;
	}

}
