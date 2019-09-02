package edu.westga.cs3212.projectpokedex.io.database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.json.simple.parser.ParseException;

import edu.westga.cs3212.projectpokedex.io.PokemonIoConstants;
import edu.westga.cs3212.projectpokedex.io.data.PokemonReader;
import edu.westga.cs3212.projectpokedex.io.data.PokemonWriter;
import edu.westga.cs3212.projectpokedex.logger.LoggerSingleton;
import edu.westga.cs3212.projectpokedex.model.pokemon.Pokemon;

/**
 * Responsible for handling connections to the remote server and local database.
 */
public class PokemonDatabase {
	
	private static final int BUFFER_SIZE = 1000000;
	
	private List<Pokemon> loadedPokemon;
	private List<Pokemon> createdPokemon;
	private PokedexDatabaseClient client;
	
	/**
	 * Instantiates a new pokemon database.
	 */
	public PokemonDatabase() {
		this.loadedPokemon = new LinkedList<Pokemon>();
		this.createdPokemon = new LinkedList<Pokemon>();
		this.client = new PokedexDatabaseClient(this.createdPokemon);
		
		this.createDatabaseFolder();
	}
	
	/**
	 * Load a database from the server or from the local cache.
	 *
	 * @return the list
	 */
	public List<Pokemon> loadDatabase() {
		this.loadedPokemon.clear();
		
		try {
			this.loadServerDatabase();
		} catch(Exception e) {
			this.loadedPokemon.clear();
		}
		
		return this.loadedPokemon;
	}
	
	/**
	 * Gets the created pokemon.
	 *
	 * @return the created pokemon
	 */
	public List<Pokemon> getCreatedPokemon(){
		return this.createdPokemon;
	}
	
	/**
	 * Distribute created pokemon to the database.
	 *
	 * @param pokemon the pokemon to be posted
	 * 
	 * @return successfully added
	 */
	public boolean distributeNewPokemon(Pokemon pokemon) {
		try {
			return this.client.postPokemonToServer(pokemon);
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	private void createDatabaseFolder() {
		new File(PokemonIoConstants.DEFAULT_DATABASE_DIR).mkdirs();
	}
	
	private void loadServerDatabase() throws Exception{
		
		for(Pokemon pokemon : this.client.getDefaultDatabase()) {
			this.loadedPokemon.add(pokemon);
		}
		
		this.client.continuousPollingForNewPokemon();
		
	}
}