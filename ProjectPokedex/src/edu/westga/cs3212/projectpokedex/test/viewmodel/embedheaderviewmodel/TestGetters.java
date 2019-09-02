package edu.westga.cs3212.projectpokedex.test.viewmodel.embedheaderviewmodel;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.projectpokedex.viewmodel.EmbedHeaderViewModel;

public class TestGetters {

	@Test
	void testValidGetters() {
		EmbedHeaderViewModel viewModel = new EmbedHeaderViewModel();
		
		assertAll(() -> assertNotNull(viewModel.getNameLabelProperty()),
				  () -> assertNotNull(viewModel.getIdLabelProperty()),
				  () -> assertNotNull(viewModel.getType1LabelProperty()),
				  () -> assertNotNull(viewModel.getExclusivityLabelProperty()),
				  () -> assertNotNull(viewModel.getExclusivityLabelProperty()),
				  () -> assertNotNull(viewModel.getType2LabelProperty()),
				  () -> assertNotNull(viewModel.getHeightLabelProperty()),
				  () -> assertNotNull(viewModel.getWeightLabelProperty())
				  
				  );
	}

	
}
