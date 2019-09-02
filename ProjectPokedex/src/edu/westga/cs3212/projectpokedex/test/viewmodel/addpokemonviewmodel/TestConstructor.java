package edu.westga.cs3212.projectpokedex.test.viewmodel.addpokemonviewmodel;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.projectpokedex.viewmodel.AddPokemonViewModel;

class TestConstructor {

	@Test
	void testValidConstructor() {
		AddPokemonViewModel vm = new AddPokemonViewModel();
		
		assertAll(() -> assertNotNull(vm.getNameProperty()),
				  () -> assertNotNull(vm.getIdNumberProperty()),
				  () -> assertNotNull(vm.getPrimaryTypeProperty()),
				  () -> assertNotNull(vm.getSecondaryTypeProperty()),
				  () -> assertNotNull(vm.getDescriptionProperty()),
				  () -> assertNotNull(vm.getHeightProperty()),
				  () -> assertNotNull(vm.getWeightProperty()),
				  () -> assertNotNull(vm.getLocationProperty()),
				  () -> assertNotNull(vm.getExclusivityProperty())
				  );
	}

}
