package edu.westga.cs3212.projectpokedex.test.io.data.sockets.zmqsocket;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.projectpokedex.io.sockets.ZMQSocket;

public class TestDelete {

	@Test
	void testSingleDeleteAfterStart() {
		
		ZMQSocket socket = new ZMQSocket("tcp://127.0.0.1:6666",false);
		try {
			socket.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
		socket.delete();
	}
	
	@Test
	void testMultipleDeleteAfterStart() {
		
		ZMQSocket socket = new ZMQSocket("tcp://127.0.0.1:6666",false);
		ZMQSocket socket2 = new ZMQSocket("tcp://127.0.0.1:6667",false);
		try {
			socket2.start();
			socket.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
		socket.delete();
		socket2.delete();
	}
	
	
}
