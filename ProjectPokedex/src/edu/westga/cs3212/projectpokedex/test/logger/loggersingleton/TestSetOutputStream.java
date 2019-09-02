package edu.westga.cs3212.projectpokedex.test.logger.loggersingleton;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.projectpokedex.logger.LoggerSingleton;

class TestSetOutputStream {
	
	@Test
	void testNullPrintStream() {
		assertThrows(NullPointerException.class, () -> LoggerSingleton.getInstance().setOutputStream(null));
	}
	
	@Test
	void testValidPrintStream() {
		LoggerSingleton logger = LoggerSingleton.getInstance();
		
		File file = new File("log_test.txt");
		try {
			file.createNewFile();
			logger.setOutputStream(new PrintStream(file));
			logger.log("text");
			try (BufferedReader br = new BufferedReader(new FileReader("log_test.txt"))) {
				String line = br.readLine();
				assertEquals("text", line);
			} catch (Exception e) {
				fail("Could not read file.");
			}
		} catch (IOException e) {
			fail("Could not create file.");
		}
		
	}

}
