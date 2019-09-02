package edu.westga.cs3212.projectpokedex.test.io.data.sockets.zmqsocket;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.projectpokedex.io.sockets.ZMQSocket;

public class TestStart {
	
	@Test
	void testReqStartOnce() {
		
		ZMQSocket socket = new ZMQSocket("tcp://127.0.0.1:6666",false);
		try {
			socket.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testReqStartMultipleIgnoreAfterFirst() {
		
		ZMQSocket socket = new ZMQSocket("tcp://127.0.0.1:6666",false);
		try {
			socket.start();
			socket.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testSubStartOnce() {
		
		ZMQSocket socket = new ZMQSocket("tcp://127.0.0.1:6666",true);
		try {
			socket.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
		socket.delete();
	}
	
	@Test
	void testSubStartMultipleIgnoreAfterFirst() {
		
		ZMQSocket socket = new ZMQSocket("tcp://127.0.0.1:6666",true);
		try {
			socket.start();
			socket.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
		socket.delete();
	}
	
	
}
