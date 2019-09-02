package edu.westga.cs3212.projectpokedex.io.sockets;

import java.io.IOException;
import java.util.LinkedList;

import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Context;
import org.zeromq.ZMQ.Socket;

import edu.westga.cs3212.projectpokedex.logger.LoggerSingleton;

/**
 * The Class ZMQSocket.
 */
public class ZMQSocket implements NetworkSocket{

	private static final int TIMEOUT_DELAY = 2000;

	private static Context CONTEXT;
	private static int SOCKETS;

	private Socket socket;
	private String ipConnection;
	private LinkedList<String> returnQueue;
	private boolean isSubscriber;
	private boolean started;

	/**
	 * Instantiates a new ZMQ socket manager.
	 *
	 * @param ipConnection
	 *            the ip connection
	 * @param subscribe
	 *            the subscribe
	 */
	public ZMQSocket(String ipConnection, boolean subscribe) {

		if (CONTEXT == null) {
			CONTEXT = ZMQ.context(2);
			SOCKETS = 0;
		}
		
		SOCKETS++;

		this.ipConnection = ipConnection;
		this.returnQueue = new LinkedList<String>();

		this.isSubscriber = subscribe;
		this.started = false;

		if (this.isSubscriber) {
			this.setupSubscriber();
		} else {
			this.setupPolling();
		}

	}

	/**
	 * Start.
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public void start() throws IOException {

		if (this.started) {
			return;
		}
		this.started = true;

		try {
			socket.connect(this.ipConnection);
		} catch (Exception e) {
			this.started = false;
			throw new IOException("Error Connecting To Specified Remote: " + this.ipConnection);
		}

	}
	
	/**
	 * Post data to the server and wait for the response
	 *
	 * @param data
	 *            the data to send
	 * @return the string response
	 */
	public String postToServer(String data) {

		if (!this.started || this.isSubscriber) {
			return null;
		}

		String response = null;
		try {
			socket.send(data.getBytes(ZMQ.CHARSET), 0);

			byte[] reply = this.socket.recv(0);
			response = new String(reply, ZMQ.CHARSET);

		} catch (Exception e) {
			if (e.getMessage() == null) {
				LoggerSingleton.getInstance().log("Exception thrown in ZMQSocket::postToServer");
			} else {
				LoggerSingleton.getInstance().log(e.getMessage());
			}
		}

		return response;
	}

	/**
	 * Gets the response.
	 *
	 * @return the response
	 */
	public String getResponse() {
		String value = null;

		if ((!this.started || !this.returnQueue.isEmpty()) && !this.isSubscriber) {
			value = this.returnQueue.remove();
		}
		
		if(this.isSubscriber) {
			byte[] data = this.socket.recv();
			if(data!=null) {
				value = new String(data,ZMQ.CHARSET);
			}
		} else {
			this.returnQueue.remove();
		}

		return value;

	}

	/**
	 * Delete The Socket And Free Resources.
	 */
	public void delete() {

		SOCKETS--;

		if (this.started) {
			try {
				this.socket.disconnect(ipConnection);
				this.socket.unbind(ipConnection);
				this.socket.close();
			} catch (Exception e) {
			}
		}

		if (SOCKETS == 0) {
			try {
				CONTEXT.close();
				CONTEXT.term();
			} catch (Exception e) {
			}
			CONTEXT = null;
		}

		this.started = false;
		
	}

	private void setupPolling() {
		this.socket = CONTEXT.socket(ZMQ.REQ);
		this.setTimeouts();
	}

	private void setupSubscriber() {
		this.socket = CONTEXT.socket(ZMQ.SUB);
		this.setTimeouts();
		this.socket.subscribe(ZMQ.SUBSCRIPTION_ALL);
	}
	
	private void setTimeouts() {
		this.socket.setSendTimeOut(TIMEOUT_DELAY);
		this.socket.setReceiveTimeOut(TIMEOUT_DELAY);
	}

}
