package edu.westga.cs3212.projectpokedex.view.utils;

import java.io.IOException;

import edu.westga.cs3212.projectpokedex.utils.ExceptionText;
import edu.westga.cs3212.projectpokedex.view.MainPageCodeBehind;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

/**
 * Class for containing FXML document and data while enforcing MVVM location.
 *
 * @author Kyle Riggs, Cody Graham, Dylan Knox, Joseph Fuller
 * @param <T> the generic type
 */
public class FXMLContainer<T> {
	
	private Pane backedPane;
	private FXMLLoader backedLoader;
	
	/**
	 * Loads a JavaFX pane by name.
	 * Enforces documents to be in the view package, or sub-packages.
	 *
	 * @param resourceName the name used to identify the document
	 * @return the pane loaded
	 */
	public FXMLContainer(String resourceName) {
		this.backedPane = null;
		this.backedLoader = new FXMLLoader();
		this.backedLoader.setLocation(MainPageCodeBehind.class.getResource(resourceName + ".fxml"));
		try {
			this.backedPane = this.backedLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
			throw new IllegalArgumentException(ExceptionText.FXML_RESOURCE_NOT_LOADED);
		}
	}
	
	/**
	 * Gets the stored pane.
	 *
	 * @return the stored pane
	 */
	public Pane getPane() {
		return this.backedPane;
	}
	
	/**
	 * Gets the controller.
	 *
	 * @return the controller
	 */
	public T getController() {
		return this.backedLoader.getController();
	}

}
