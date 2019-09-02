package edu.westga.cs3212.projectpokedex.test.logger.loggersingleton;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.projectpokedex.logger.LoggerSingleton;

class TestConstructor {

	@Test
	void testFileNotFound() {
		File file = new File("log.txt");
		
		file.delete();
		
		LoggerSingleton.getInstance();
		
		try {
			file.createNewFile();
		} catch (IOException e) {
			
		}
	}

}
