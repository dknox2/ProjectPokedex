package edu.westga.cs3212.projectpokedex.io.sockets;

import java.io.IOException;

/**
 * The Interface NetworkSocket.
 */
public interface NetworkSocket {
	
	/**
	 * Start the ocket's communications.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void start() throws IOException;

	/**
	 * Post data to server.
	 *
	 * @param data the data
	 * @return the string
	 */
	public String postToServer(String data);

	/**
	 * Gets the polled server response.
	 *
	 * @return the response
	 */
	public String getResponse();

	/**
	 * Delete and close resources.
	 */
	public void delete();
	
}
