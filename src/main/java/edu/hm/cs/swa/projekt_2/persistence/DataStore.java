package edu.hm.cs.swa.projekt_2.persistence;

import java.util.LinkedList;
import java.util.List;

import edu.hm.cs.swa.projekt_2.datamodel.Book;
import edu.hm.cs.swa.projekt_2.datamodel.Medium;

public class DataStore {

	public static DataStore INSTANCE = new DataStore();
	
	private List<Medium> bookList;
	
	protected DataStore(){
		
		bookList = new LinkedList<>();
		bookList.add(new Book("hans","blabla", "blubdiwub"));
		bookList.add(new Book("Günther", "asdasderfiuhch", "Ümläüte in ällen Lebenslägen"));
		
	}
	
	
	public Medium[] getBooks(){
		
		return bookList.toArray(new Medium[bookList.size()]);
		
	}
}
