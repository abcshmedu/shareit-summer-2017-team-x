package edu.hm.cs.swa.projekt_2.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import edu.hm.cs.swa.projekt_2.datamodel.Book;
import edu.hm.cs.swa.projekt_2.persistence.DataStore;

public class MediaResourceTest {
	
	private final String ISBN ="";

	@Test
	public void testCreateBook() {
		
		MediaResource media = new MediaResource();
		
		Book newBook = new Book("bert", "abcd", "test");
		
		media.createBook(newBook);
		
		DataStore store = DataStore.INSTANCE;
		assertNotNull(store.getBook(newBook.getIsbn()));
	}
	
	@Test
	public void testgetBook() {
		
		MediaResource media = new MediaResource();
		
		Book newBook = new Book("bert", "abcd", "test");
		
		media.createBook(newBook);
		
		assertEquals(media.getBook(newBook.getIsbn()), newBook);
	}

}
