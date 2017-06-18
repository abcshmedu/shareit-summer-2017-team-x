package edu.hm.cs.swa.projekt_2.logic;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import edu.hm.cs.swa.projekt_2.datamodel.Book;
import edu.hm.cs.swa.projekt_2.datamodel.Medium;
import edu.hm.cs.swa.projekt_2.persistence.DataStore;

public class MockTest {

	@Mock
	private DataStore mockedDataStore;
	
	@InjectMocks
	private MediaServiceImpl mediaService;
	
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		
		
		
		Medium[] books = {new Book("","",""), new Book("","","")}; 
		Mockito.when(mockedDataStore.getBooks()).thenReturn(books);
		
		Medium[] returnedBooks = mediaService.getBooks();
		
		Mockito.verify(mockedDataStore, Mockito.times(1)).getBooks();
		
	}

}
