package edu.westga.cs3212.projectpokedex.test.viewmodel.mainpageviewmodel;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.projectpokedex.viewmodel.MainPageViewModel;

class TestAddSampleEvolutions {

	@Test
	void testAddSampleEvolutions() {
		MainPageViewModel viewModel = new MainPageViewModel();
		
		viewModel.loadDataFromDatabase();
		viewModel.addSampleEvolutions();
	}

}
