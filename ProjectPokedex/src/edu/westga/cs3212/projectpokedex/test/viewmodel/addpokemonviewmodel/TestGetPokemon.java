package edu.westga.cs3212.projectpokedex.test.viewmodel.addpokemonviewmodel;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.projectpokedex.model.pokemon.Exclusivity;
import edu.westga.cs3212.projectpokedex.model.pokemon.Pokemon;
import edu.westga.cs3212.projectpokedex.model.pokemon.Type;
import edu.westga.cs3212.projectpokedex.viewmodel.AddPokemonViewModel;

class TestGetPokemon {

	@Test
	void testSecondaryTypeNull() {
		AddPokemonViewModel vm = new AddPokemonViewModel();
		vm.getNameProperty().setValue("Vaporeon");
		vm.getIdNumberProperty().setValue("134");
		vm.getPrimaryTypeProperty().set(Type.WATER);
		
		vm.getDescriptionProperty().setValue("Its body's cellular structure is "
				+ "similar to the molecular composition of water. It can melt "
				+ "invisibly in water.");
		vm.getHeightProperty().setValue("1.0");
		vm.getWeightProperty().setValue("29.0");
		vm.getLocationProperty().setValue("Evolve Eevee");
		vm.getExclusivityProperty().set(Exclusivity.BOTH);
		
		Pokemon pkmn = vm.getPokemon();
		
		assertAll(() -> assertEquals(vm.getNameProperty().getValue(), pkmn.getName()),
				  () -> assertEquals(Integer.parseInt(vm.getIdNumberProperty().getValue()), pkmn.getPokedexNumber()),
				  () -> assertEquals(vm.getPrimaryTypeProperty().get().toString(), pkmn.getPrimaryType()),
				  () -> assertEquals(vm.getSecondaryTypeProperty().getValue(), pkmn.getSecondaryType()),
				  () -> assertEquals(vm.getDescriptionProperty().getValue(), pkmn.getDescription()),
				  () -> assertEquals(Double.parseDouble(vm.getHeightProperty().getValue()), pkmn.getHeight()),
				  () -> assertEquals(Double.parseDouble(vm.getWeightProperty().getValue()), pkmn.getWeight()),
				  () -> assertEquals(vm.getLocationProperty().getValue(), pkmn.getLocation()),
				  () -> assertEquals(vm.getExclusivityProperty().get().toString(), pkmn.getExclusivity())
				  );
	}
	
	@Test
	void testSecondaryTypeNotNull() {
		AddPokemonViewModel vm = new AddPokemonViewModel();
		vm.getNameProperty().setValue("Gengar");
		vm.getIdNumberProperty().setValue("94");
		vm.getPrimaryTypeProperty().set(Type.GHOST);
		vm.getSecondaryTypeProperty().set(Type.POISON);
		vm.getDescriptionProperty().setValue("It is said to emerge from darkness to "
				+ "steal the lives of those who become lost in mountains.");
		vm.getHeightProperty().setValue("1.5");
		vm.getWeightProperty().setValue("40.5");
		vm.getLocationProperty().setValue("Evolve Haunter");
		vm.getExclusivityProperty().set(Exclusivity.BOTH);
		
		Pokemon pkmn = vm.getPokemon();
		
		assertAll(() -> assertEquals(vm.getNameProperty().getValue(), pkmn.getName()),
				  () -> assertEquals(Integer.parseInt(vm.getIdNumberProperty().getValue()), pkmn.getPokedexNumber()),
				  () -> assertEquals(vm.getPrimaryTypeProperty().get().toString(), pkmn.getPrimaryType()),
				  () -> assertEquals(vm.getSecondaryTypeProperty().get().toString(), pkmn.getSecondaryType()),
				  () -> assertEquals(vm.getDescriptionProperty().getValue(), pkmn.getDescription()),
				  () -> assertEquals(Double.parseDouble(vm.getHeightProperty().getValue()), pkmn.getHeight()),
				  () -> assertEquals(Double.parseDouble(vm.getWeightProperty().getValue()), pkmn.getWeight()),
				  () -> assertEquals(vm.getLocationProperty().getValue(), pkmn.getLocation()),
				  () -> assertEquals(vm.getExclusivityProperty().get().toString(), pkmn.getExclusivity())
				  );
	}
	
	@Test
	void testBadPokemon() {
		AddPokemonViewModel vm = new AddPokemonViewModel();
		Pokemon pkmn = vm.getPokemon();
		assertNull(pkmn);
	}
	
}
