package edu.westga.cs3212.projectpokedex.test.io.database.pokedexdatabaseclient;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.util.LinkedList;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.projectpokedex.io.database.PokedexDatabaseClient;
import edu.westga.cs3212.projectpokedex.io.sockets.NetworkSocket;
import edu.westga.cs3212.projectpokedex.model.pokemon.Pokemon;

public class TestContinuousPollingForPokemon {

	private static class ServerFake implements NetworkSocket{
		public void start() throws IOException {}
		public String postToServer(String data) {
			return null;
		}

		public String getResponse() {
			return null;
		}
		public void delete() {}
	}

	@Test
	void testUsualCircumstances() {
		
		NetworkSocket req = new ServerFake();
		NetworkSocket sub = new ServerFake();
		
		PokedexDatabaseClient cli = new PokedexDatabaseClient(new LinkedList<Pokemon>(), req, sub);
		try {
			cli.continuousPollingForNewPokemon();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
