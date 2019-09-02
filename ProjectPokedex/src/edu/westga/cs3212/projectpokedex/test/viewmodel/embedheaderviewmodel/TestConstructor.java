package edu.westga.cs3212.projectpokedex.test.viewmodel.embedheaderviewmodel;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.projectpokedex.viewmodel.EmbedHeaderViewModel;

class TestConstructor {

	@Test
	void testValidConstructor() {
		EmbedHeaderViewModel viewModel = new EmbedHeaderViewModel();
		
		assertNotNull(viewModel);
	}

}
