package edu.westga.cs3212.projectpokedex.test.io.data.sockets.zmqsocket;

import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.projectpokedex.io.sockets.ZMQSocket;

public class TestConstructor {
	
	@Test
	void testReqConstructorValid() {
		
		ZMQSocket socket = new ZMQSocket("tcp://127.0.0.1:6666",false);
		assertNotNull(socket);
		socket.delete();
	}
	
	@Test
	void testSubConstructorValid() {
		
		ZMQSocket socket = new ZMQSocket("tcp://127.0.0.1:6666",true);
		assertNotNull(socket);
		socket.delete();
	}
	

}
