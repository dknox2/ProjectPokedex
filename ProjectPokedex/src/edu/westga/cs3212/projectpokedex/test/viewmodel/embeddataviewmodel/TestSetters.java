package edu.westga.cs3212.projectpokedex.test.viewmodel.embeddataviewmodel;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.projectpokedex.model.pokemon.moves.LevelUpMove;
import edu.westga.cs3212.projectpokedex.viewmodel.EmbedDataViewModel;

public class TestSetters {

	@Test
	public void testValidSetters() {
		EmbedDataViewModel viewModel = new EmbedDataViewModel();

		viewModel.setDisplayImage(null);	
	}
	
	@Test
	void testSetMoveset() {
		EmbedDataViewModel viewModel = new EmbedDataViewModel();
		Collection<LevelUpMove> moveset = new ArrayList<LevelUpMove>();
		
		viewModel.setMoveset(moveset);
		
		assertEquals(0, viewModel.getLevelUpMovesetProperty().get().size());
	}
	
}
