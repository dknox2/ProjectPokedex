package edu.westga.cs3212.projectpokedex.model.pokemon.evolution;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * A Pokemon can only have one pre-evolution, but a multitude of post-evolutions.
 * This class is used to model these multiple pre-evolutions.
 * 
 * @author Kyle Riggs, Cody Graham, Dylan Knox, Joseph Fuller@author csuser
 */
public class PostEvolutions implements Collection<Evolution> {
	private Collection<Evolution> evolutions;
	
	/**
	 * Instantiates a new empty set of post evolutions.
	 * 
	 * @precondition none
	 * @postconditions isEmpty() == true
	 * 				   && size() == 0
	 */
	public PostEvolutions() {
		this.evolutions = new ArrayList<Evolution>();
	}

	@Override
	public boolean add(Evolution arg0) {
		return this.evolutions.add(arg0);
	}

	@Override
	public boolean addAll(Collection<? extends Evolution> arg0) {
		return this.evolutions.addAll(arg0);
	}

	@Override
	public void clear() {
		this.evolutions.clear();
	}

	@Override
	public boolean contains(Object arg0) {
		return this.evolutions.contains(arg0);
	}

	@Override
	public boolean containsAll(Collection<?> arg0) {
		return this.evolutions.containsAll(arg0);
	}

	@Override
	public boolean isEmpty() {
		return this.evolutions.isEmpty();
	}

	@Override
	public Iterator<Evolution> iterator() {
		return this.evolutions.iterator();
	}

	@Override
	public boolean remove(Object arg0) {
		return this.evolutions.remove(arg0);
	}

	@Override
	public boolean removeAll(Collection<?> arg0) {
		return this.evolutions.removeAll(arg0);
	}

	@Override
	public boolean retainAll(Collection<?> arg0) {
		return this.evolutions.retainAll(arg0);
	}

	@Override
	public int size() {
		return this.evolutions.size();
	}

	@Override
	public Object[] toArray() {
		return this.evolutions.toArray();
	}

	@Override
	public <T> T[] toArray(T[] arg0) {
		return this.evolutions.toArray(arg0);
	}
	
	@Override
	public String toString() {
		String output = "";
		for (Evolution evolution : this.evolutions) {
			output += evolution.toString() + System.lineSeparator();
		}
		
		return output;
	}
}
