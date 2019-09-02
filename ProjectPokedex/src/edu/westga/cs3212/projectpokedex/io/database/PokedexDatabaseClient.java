package edu.westga.cs3212.projectpokedex.io.database;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import edu.westga.cs3212.projectpokedex.io.PokemonIoConstants;
import edu.westga.cs3212.projectpokedex.io.data.PokemonReader;
import edu.westga.cs3212.projectpokedex.io.data.PokemonWriter;
import edu.westga.cs3212.projectpokedex.io.sockets.NetworkSocket;
import edu.westga.cs3212.projectpokedex.io.sockets.ZMQSocket;
import edu.westga.cs3212.projectpokedex.logger.LoggerSingleton;
import edu.westga.cs3212.projectpokedex.model.pokemon.Pokemon;

/**
 * Client that subscribes to the server, and posts Pokemon to the server, via a
 * TCP connection.
 */
public class PokedexDatabaseClient {

	private static final String REQ_SERVER_ADDRESS = "tcp://127.0.0.1:6660";
	private static final String SUB_SERVER_ADDRESS = "tcp://127.0.0.1:6666";

	private boolean isPolling;

	private List<Pokemon> initialDatabase;
	private List<Pokemon> storageForCreatedPokemon;

	private NetworkSocket requester;
	private NetworkSocket subscriber;
	private boolean connected;

	/**
	 * Instantiates a new pokedex database client.
	 *
	 * @param storageForCreatedPokemon the storage for created pokemon
	 */
	public PokedexDatabaseClient(List<Pokemon> storageForCreatedPokemon) {
		NetworkSocket requester = new ZMQSocket(REQ_SERVER_ADDRESS, false);
		NetworkSocket subscriber = new ZMQSocket(SUB_SERVER_ADDRESS, true);
		this.constructorInit(storageForCreatedPokemon, requester, subscriber);
	}

	/**
	 * Instantiates a new pokedex database client.
	 *
	 * @param storageForCreatedPokemon the storage for created pokemon
	 * @param requester                the requester
	 * @param subscriber               the subscriber
	 */
	public PokedexDatabaseClient(List<Pokemon> storageForCreatedPokemon, NetworkSocket requester,
			NetworkSocket subscriber) {
		this.constructorInit(storageForCreatedPokemon, requester, subscriber);
	}

	private void constructorInit(List<Pokemon> storageForCreatedPokemon, NetworkSocket requester,
			NetworkSocket subscriber) {
		this.initialDatabase = new ArrayList<Pokemon>();
		this.isPolling = false;
		this.storageForCreatedPokemon = storageForCreatedPokemon;

		this.requester = requester;
		this.subscriber = subscriber;

		this.connected = this.attemptRemoteConnection();
	}

	/**
	 * Gets the default database.
	 *
	 * @return the default database
	 * @throws Exception the exception
	 */
	public List<Pokemon> getDefaultDatabase() throws Exception {

		if (this.connected && this.initialDatabase.isEmpty()) {
			this.initialConnection();
		}

		return this.initialDatabase;
	}

	/**
	 * Continuous polling for new pokemon.
	 *
	 * @throws Exception the exception
	 */
	public void continuousPollingForNewPokemon() throws Exception {
		Thread poll = new Thread(() -> {
			if (this.isPolling) {
				return;
			}
			this.isPolling = true;

			while (true) {
				try {
					String content = this.subscriber.getResponse();
					if (content != null) {

						PokemonReader reader = new PokemonReader(content);
						this.storageForCreatedPokemon.add(reader.getPokemon());

					}
					Thread.sleep(250);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		});
		poll.setDaemon(true);
		poll.start();
	}

	/**
	 * Post pokemon to server.
	 *
	 * @param pokemon the pokemon
	 * @throws Exception the exception
	 */
	public boolean postPokemonToServer(Pokemon pokemon) throws Exception {
		try {
			String value = this.getNetworkPacket(pokemon);
			String success = this.requester.postToServer(value);
			if (success != null) {
				JSONObject parsed = (JSONObject) new JSONParser().parse(success);
				String successValue = parsed.get(PokemonIoConstants.SERVER_REQUEST_SUCCESS).toString();
				if (successValue.toUpperCase().compareTo(PokemonIoConstants.JSON_TRUE) == 0) {
					return true;
				}
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	@SuppressWarnings("unchecked")
	public void initialConnection() throws Exception {
		Pokemon values[];
		try {

			JSONObject getter = new JSONObject();
			getter.put(PokemonIoConstants.SERVER_REQUEST_BOOT, PokemonIoConstants.JSON_TRUE);

			String jsonData = this.requester.postToServer(getter.toJSONString());

			JSONArray list = (JSONArray) new JSONParser().parse(jsonData);

			values = new Pokemon[list.size()];

			for (int i = 0; i < list.size(); i++) {
				try {
					PokemonReader reader = new PokemonReader(list.get(i).toString());

					values[i] = reader.getPokemon();

				} catch (Exception e) {
					System.err.print("Parse Error: " + (i) + 1);
				}
			}

		} catch (Exception e) {
			if (e.getMessage() == null) {
				LoggerSingleton.getInstance().log("No connection to server, loading local database");
			} else {
				LoggerSingleton.getInstance().log(e.getMessage());
			}
			throw new IllegalArgumentException("Could Not Parse The Given Input. Illegal Data");
		}

		this.initialDatabase.clear();

		for (int i = 0; i < values.length; i++) {
			if (values[i] != null) {
				this.initialDatabase.add(values[i]);
			}
		}

	}

	private boolean attemptRemoteConnection() {
		try {
			this.requester.start();
			this.subscriber.start();
		} catch (Exception e) {
			e.printStackTrace();
			this.deleteConnections();
			return false;
		}
		return true;
	}

	private void deleteConnections() {
		this.requester.delete();
		this.subscriber.delete();
	}

	@SuppressWarnings("unchecked")
	private String getNetworkPacket(Pokemon pokemon) {
		PokemonWriter writer = new PokemonWriter(pokemon);
		String value = writer.getJSONString();

		JSONObject json = new JSONObject();
		json.put(PokemonIoConstants.SERVER_REQUEST_CREATE, PokemonIoConstants.JSON_TRUE);
		String dict = json.toJSONString();

		dict = dict.substring(0, dict.length() - 1);
		dict += ",\"" + PokemonIoConstants.SERVER_REQUEST_DATA + "\":" + value + "}";

		StringBuilder finalizer = new StringBuilder("");
		boolean escaped = false;
		for (char chr : dict.toCharArray()) {
			if (chr == '\\') {
				escaped = true;
				continue;
			}
			if (escaped) {
				if (chr == '"' || chr == '\'') {
					finalizer.append('\\');
					finalizer.append(chr);
				}
				escaped = false;
				continue;
			}
			escaped = false;
			finalizer.append(chr);
		}

		return finalizer.toString();
	}

}
