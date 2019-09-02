package edu.westga.cs3212.projectpokedex.test.io.data.sockets.zmqsocket;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.projectpokedex.io.sockets.ZMQSocket;

public class TestPostToServer {

	@Test
	void testReqPostIgnoredNotStarted() {
		
		ZMQSocket socket = new ZMQSocket("tcp://127.0.0.1:6666",false);
		socket.postToServer("data");
		socket.delete();
	}
	
	@Test
	void testReqPostToServerValidAfterStarted() {
		
		ZMQSocket socket = new ZMQSocket("tcp://127.0.0.1:6666",false);
		try {
			socket.start();
			socket.postToServer("data");
		} catch (IOException e) {
			e.printStackTrace();
		}
		socket.delete();
	}
	
	@Test
	void testSubInvalidBeforeStartingAndAfter() {
		
		ZMQSocket socket = new ZMQSocket("tcp://127.0.0.1:6666",true);
		try {
			socket.postToServer("data");
			socket.start();
			socket.postToServer("data");
		} catch (IOException e) {
			e.printStackTrace();
		}
		socket.delete();
	}
	
}
