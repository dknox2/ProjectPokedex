package edu.westga.cs3212.projectpokedex.viewmodel;

import edu.westga.cs3212.projectpokedex.model.Pokedex;
import edu.westga.cs3212.projectpokedex.model.pokemon.Exclusivity;
import edu.westga.cs3212.projectpokedex.model.pokemon.Pokemon;
import edu.westga.cs3212.projectpokedex.model.pokemon.Type;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Viewmodel class for the Add Pokemon window.
 */
public class AddPokemonViewModel {
	private final StringProperty nameProperty;
	private final StringProperty idNumberProperty;

	private final ObjectProperty<Type> primaryTypeProperty;
	private final ObjectProperty<Type> secondaryTypeProperty;

	private final StringProperty descriptionProperty;

	private final StringProperty heightProperty;
	private final StringProperty weightProperty;

	private final StringProperty locationProperty;

	private final ObjectProperty<Exclusivity> exclusivityProperty;
	
	private Pokedex pokedex;

	/**
	 * Instantiates a new AddPokemonViewModel.
	 */
	public AddPokemonViewModel() {		
		this.nameProperty = new SimpleStringProperty();
		this.idNumberProperty = new SimpleStringProperty();

		this.primaryTypeProperty = new SimpleObjectProperty<Type>();
		this.secondaryTypeProperty = new SimpleObjectProperty<Type>();

		this.descriptionProperty = new SimpleStringProperty();

		this.heightProperty = new SimpleStringProperty();
		this.weightProperty = new SimpleStringProperty();

		this.locationProperty = new SimpleStringProperty();

		this.exclusivityProperty = new SimpleObjectProperty<Exclusivity>();
		
		this.pokedex = new Pokedex();
	}

	/**
	 * Gets the Pokemon from the input.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the Pokemon, or null if the Pokemon is invalid.
	 */
	public Pokemon getPokemon() {
		String secondaryType;
		if (this.secondaryTypeProperty.get() == null) {
			secondaryType = null;
		} else {
			secondaryType = this.secondaryTypeProperty.get().toString();
		}
		
		try {
			return new Pokemon(this.nameProperty.get(), Integer.parseInt(this.idNumberProperty.get()),
					this.primaryTypeProperty.get().toString(), secondaryType,
					this.exclusivityProperty.get().toString(), this.locationProperty.get(), this.descriptionProperty.get(),
					Double.parseDouble(this.heightProperty.get()), Double.parseDouble(this.weightProperty.get()));
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * Gets the Pokedex.
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
	 * Sets the Pokedex.
	 * 
	 * @param pokedex the pokedex
	 * 
	 * @precondition none
	 * @postcondition getPokedex() == pokedex
	 */
	public void setPokedex(Pokedex pokedex) {
		this.pokedex = pokedex;
	}
	
	/**
	 * Gets the name property.
	 *
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the name property
	 */
	public StringProperty getNameProperty() {
		return this.nameProperty;
	}

	/**
	 * Gets the id number property.
	 *
	 * @precondition none
	 * @postcondition none
	 *
	 * @return the id number property
	 */
	public StringProperty getIdNumberProperty() {
		return this.idNumberProperty;
	}

	/**
	 * Gets the primary type property.
	 *
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the primary type property
	 */
	public ObjectProperty<Type> getPrimaryTypeProperty() {
		return this.primaryTypeProperty;
	}

	/**
	 * Gets the secondary type property.
	 *
	 * @precondition none
	 * @postcondition none
	 *
	 * @return the secondary type property
	 */
	public ObjectProperty<Type> getSecondaryTypeProperty() {
		return this.secondaryTypeProperty;
	}

	/**
	 * Gets the description property.
	 *
	 * @precondition none
	 * @postcondition none
	 *
	 * @return the description property
	 */
	public StringProperty getDescriptionProperty() {
		return this.descriptionProperty;
	}

	/**
	 * Gets the height property.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the height property
	 */
	public StringProperty getHeightProperty() {
		return this.heightProperty;
	}

	/**
	 * Gets the weight property.
	 *
	 * @precondition none
	 * @postcondition none
	 *
	 * @return the weight property
	 */
	public StringProperty getWeightProperty() {
		return this.weightProperty;
	}

	/**
	 * Gets the location property.
	 *
	 * @precondition none
	 * @postcondition none
	 *
	 * @return the location property
	 */
	public StringProperty getLocationProperty() {
		return this.locationProperty;
	}

	/**
	 * Gets the exclusivity property.
	 *
	 * @precondition none
	 * @postcondition none
	 *
	 * @return the exclusivity property
	 */
	public ObjectProperty<Exclusivity> getExclusivityProperty() {
		return this.exclusivityProperty;
	}
}
