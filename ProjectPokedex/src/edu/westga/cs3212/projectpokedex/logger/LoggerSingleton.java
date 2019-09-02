package edu.westga.cs3212.projectpokedex.logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import edu.westga.cs3212.projectpokedex.utils.ExceptionText;

/**
 * Utility logger singleton that can log to standard output or a log file.
 * 
 * @author Kyle Riggs, Cody Graham, Dylan Knox, Joseph Fuller
 */
public class LoggerSingleton {
	private static LoggerSingleton instance;

	private PrintStream outputStream;

	private LoggerSingleton() {
		try {
			this.outputStream = new PrintStream(new File("log.txt"));
		} catch (FileNotFoundException e) {
			// NOTE: This line will not be reached and is present to catch exceptions from PrintStream's constructor
			this.outputStream = System.out;
		}
	}

	/**
	 * Returns the instance of the logger singleton.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the logger singleton
	 */
	public static LoggerSingleton getInstance() {
		if (instance == null) {
			instance = new LoggerSingleton();
		}

		return instance;
	}

	/**
	 * Sets the output stream to the given stream.
	 * 
	 * @param outputStream the stream to log to
	 * 
	 * @precondition outputStream != null
	 * @postcondition all future logging is logged to the given output stream
	 */
	public void setOutputStream(PrintStream outputStream) {
		if (outputStream == null) {
			throw new NullPointerException(ExceptionText.OUTPUT_STREAM_NULL);
		}

		this.outputStream = outputStream;
	}

	/**
	 * Logs the given text to the set output stream.
	 * 
	 * @param text the text to log
	 * 
	 * @precondition text != null
	 * @postcondition text is written to stream
	 */
	public void log(String text) {
		if (text == null) {
			throw new NullPointerException(ExceptionText.TEXT_NULL);
		}

		this.outputStream.println(text);
	}
}
