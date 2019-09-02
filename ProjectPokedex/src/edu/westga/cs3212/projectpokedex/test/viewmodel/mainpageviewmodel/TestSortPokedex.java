package edu.westga.cs3212.projectpokedex.test.viewmodel.mainpageviewmodel;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.projectpokedex.model.pokedexitem.PokedexItemComparators;
import edu.westga.cs3212.projectpokedex.view.PokedexSortingItem;
import edu.westga.cs3212.projectpokedex.viewmodel.MainPageViewModel;

class TestSortPokedex {

	@Test
	void testDefaultSortAscending() {
		MainPageViewModel viewModel = new MainPageViewModel();
		
		viewModel.sortPokedex();	
	}
	
	@Test
	void testChosenComparatorSort() {
		MainPageViewModel viewModel = new MainPageViewModel();
		
		viewModel.getSelectedSortingEntryProperty().set(new PokedexSortingItem("", PokedexItemComparators.COMPARE_BY_NAME));
		
		viewModel.sortPokedex();
	}

	@Test
	void testDescendingSort() {
		MainPageViewModel viewModel = new MainPageViewModel();
		
		viewModel.getSortDescendingProperty().set(true);
		
		viewModel.sortPokedex();
	}
}
