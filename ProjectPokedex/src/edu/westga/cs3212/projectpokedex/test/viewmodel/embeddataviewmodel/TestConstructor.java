package edu.westga.cs3212.projectpokedex.test.viewmodel.embeddataviewmodel;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.projectpokedex.viewmodel.EmbedDataViewModel;

class TestConstructor {

	@Test
	void testValidConstructor() {
		EmbedDataViewModel viewModel = new EmbedDataViewModel();

		assertAll(() -> assertNotNull(viewModel.getSummaryTextAreaProperty()),
				() -> assertNotNull(viewModel.getDisplayImageProperty()),
				() -> assertNotNull(viewModel.getHeightLabelProperty()),
				() -> assertNotNull(viewModel.getWeightLabelProperty()),
				() -> assertNotNull(viewModel.getExclusivityProperty()),
				() -> assertNotNull(viewModel.getNameLabelProperty()),
				() -> assertNotNull(viewModel.getIdLabelProperty()),
				() -> assertNotNull(viewModel.getType1LabelProperty()),
				() -> assertNotNull(viewModel.getType2LabelProperty()),
				() -> assertNotNull(viewModel.getLocationLabelProperty()),
				() -> assertNotNull(viewModel.getCloseActionProperty()),
				() -> assertNotNull(viewModel.getLevelUpMovesetProperty()),
				() -> assertNotNull(viewModel.getEvolvesFromProperty()),
				() -> assertNotNull(viewModel.getEvolvesToProperty())
		);
	}

}
