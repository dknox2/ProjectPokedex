package edu.westga.cs3212.projectpokedex.test.viewmodel.mainpageviewmodel;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.projectpokedex.viewmodel.MainPageViewModel;

class TestConstructor {

	@Test
	void testValidConstruction() {
		MainPageViewModel viewModel = new MainPageViewModel();
		
		assertNotNull(viewModel.getPokedex());
		assertNotNull(viewModel.getSelectedSortingEntryProperty());
		assertNotNull(viewModel.getSortDescendingProperty());
	}

}
