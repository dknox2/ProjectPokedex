package edu.westga.cs3212.projectpokedex.test.logger.loggersingleton;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.projectpokedex.logger.LoggerSingleton;

class TestLog {

	@Test
	void testNullText() {
		assertThrows(NullPointerException.class, () -> LoggerSingleton.getInstance().log(null));
	}

}
