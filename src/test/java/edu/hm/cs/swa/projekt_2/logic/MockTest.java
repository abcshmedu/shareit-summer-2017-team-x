package edu.hm.cs.swa.projekt_2.logic;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Module;
import com.google.inject.util.Modules;

import edu.hm.Configuration;
import edu.hm.cs.swa.projekt_2.datamodel.Book;
import edu.hm.cs.swa.projekt_2.datamodel.Medium;
import edu.hm.cs.swa.projekt_2.persistence.DataStore;

public class MockTest {


	@Mock
	DataStore mockedDataStore;
	
	
	@Before
	public void setUp() throws Exception {
		
		mockedDataStore = Mockito.mock(DataStore.class);
		
		
		Module testModule = new AbstractModule() {
			
			@Override
			protected void configure() {
				// TODO Auto-generated method stub
				
				 bind(MediaService.class).to(MediaServiceImpl.class);

			        bind(ValidationService.class).toInstance(ValidationServiceImpl.INSTANCE);
			        
			        bind(DataStore.class).toInstance(mockedDataStore);

			}
		};
		
		Guice.createInjector(Modules.override(new Configuration()).with(testModule));
		
	}

	@Test
    public void getBooks() throws Exception {
		
		Medium[] books = {new Book("", "", "")};
		Mockito.when(mockedDataStore.getBooks()).thenReturn(books);
        
        MediaService service = new MediaServiceImpl();
        Medium[] returnedBooks = service.getBooks();
        
        Assert.assertTrue(returnedBooks != null);
        Assert.assertEquals(1, returnedBooks.length);
    }

}
