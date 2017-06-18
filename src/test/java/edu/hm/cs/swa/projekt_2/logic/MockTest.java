package edu.hm.cs.swa.projekt_2.logic;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.util.Modules;

import edu.hm.Configuration;
import edu.hm.cs.swa.projekt_2.datamodel.Book;
import edu.hm.cs.swa.projekt_2.datamodel.Medium;
import edu.hm.cs.swa.projekt_2.persistence.DataStore;

public class MockTest {
	
	private String valideToken ="abcdef1234";
	private AuthorizationIDEnum valideAuthID = AuthorizationIDEnum.BOOK_READ;


	@Mock
	DataStore mockedDataStore;
	
	@Mock
	ValidationService mockedValidationService;
	
	
	@Before
	public void setUp() throws Exception {
		
		mockedDataStore = Mockito.mock(DataStore.class);
		mockedValidationService = Mockito.mock(ValidationService.class);
		
		Mockito.when(mockedValidationService.validateToken(valideToken, valideAuthID)).thenReturn(ValidationResult.AUTHORIZATION_OK);
		Mockito.when(mockedValidationService.validateToken(Mockito.anyString(), Mockito.any())).thenReturn(ValidationResult.TOKEN_INVALIDE);
		
		
		Module testModule = new AbstractModule() {
			
			@Override
			protected void configure() {
				// TODO Auto-generated method stub
				
				 bind(MediaService.class).to(MediaServiceImpl.class);

			        bind(ValidationService.class).toInstance(mockedValidationService);
			        
			        bind(DataStore.class).toInstance(DataStore.INSTANCE);

			}
		};
		
		Injector injector = Guice.createInjector(Modules.override(new Configuration()).with(testModule));
		
		
	}


	private final String author = "author";
    private final String title = "title";

    private final String isbn_valid = "978-3836217798";
    private final String isbn_valid_2 = "978-3836277198";
    private final String isbn_invalid = "978-3836217275";


    private final String barcode_valid = "5449000096241";

    @Test
    public void addBook() throws Exception {
        Book newBook = new Book(author, isbn_valid, title);
        MediaService service = new MediaServiceImpl();
        service.addBook(newBook);
        Assert.assertEquals(1, DataStore.INSTANCE.getBooks().length);
    }

    @Test
    public void addBookNull() throws Exception {
        MediaService service = new MediaServiceImpl();
        MediaServiceResult r = service.addBook(null);
        Assert.assertEquals(MediaServiceResult.MISSING_CONTENT, r);
        Assert.assertEquals(0, DataStore.INSTANCE.getBooks().length);
    }

    @Test
    public void addBookFalseIsbn() throws Exception {
        Book newBook = new Book(author, isbn_invalid, title);
        MediaService service = new MediaServiceImpl();
        MediaServiceResult r = service.addBook(newBook);
        Assert.assertEquals(MediaServiceResult.ISBN_INVALID, r);
        Assert.assertEquals(0, DataStore.INSTANCE.getBooks().length);

    }

    @Test
    public void addBookFalseTitleEmpty() throws Exception {
        Book newBook = new Book(author, isbn_valid, "");
        MediaService service = new MediaServiceImpl();
        MediaServiceResult r = service.addBook(newBook);
        Assert.assertEquals(MediaServiceResult.MISSING_TITLE, r);
        Assert.assertEquals(0, DataStore.INSTANCE.getBooks().length);
    }

    @Test
    public void addBookFalseTitleNull() throws Exception {
        Book newBook = new Book(author, isbn_valid, null);
        MediaService service = new MediaServiceImpl();
        MediaServiceResult r = service.addBook(newBook);
        Assert.assertEquals(MediaServiceResult.MISSING_TITLE, r);
        Assert.assertEquals(0, DataStore.INSTANCE.getBooks().length);
    }

    @Test
    public void addBookFalseAuthorEmpty() throws Exception {
        Book newBook = new Book("", isbn_valid, title);
        MediaService service = new MediaServiceImpl();
        MediaServiceResult r = service.addBook(newBook);
        Assert.assertEquals(MediaServiceResult.MISSING_AUTHOR, r);
        Assert.assertEquals(0, DataStore.INSTANCE.getBooks().length);
    }

    @Test
    public void addBookFalseAuthorNull() throws Exception {
        Book newBook = new Book(null, isbn_valid, title);
        MediaService service = new MediaServiceImpl();
        MediaServiceResult r = service.addBook(newBook);
        Assert.assertEquals(MediaServiceResult.MISSING_AUTHOR, r);
        Assert.assertEquals(0, DataStore.INSTANCE.getBooks().length);
    }

    @Test
    public void addBookTwice() throws Exception {
        Book newBook = new Book(author, isbn_valid, title);
        MediaService service = new MediaServiceImpl();
        MediaServiceResult r1 = service.addBook(newBook);
        MediaServiceResult r2 = service.addBook(newBook);
        Assert.assertEquals(1, DataStore.INSTANCE.getBooks().length);
        Assert.assertEquals(MediaServiceResult.CREATED, r1);
        Assert.assertEquals(MediaServiceResult.ALREADY_EXISTS, r2);
    }

    @Test
    public void getBooks() throws Exception {
        Book newBook = new Book(author, isbn_valid, title);
        MediaService service = new MediaServiceImpl();
        service.addBook(newBook);
        Medium[] books = service.getBooks();
        Assert.assertTrue(books != null);
        Assert.assertEquals(1, books.length);
        Assert.assertEquals(newBook, books[0]);
    }

    @Test
    public void getBook() throws Exception {
        Book newBook = new Book(author, isbn_valid, title);
        MediaService service = new MediaServiceImpl();
        service.addBook(newBook);
        Medium result = service.getBook(newBook.getIsbn());
        Assert.assertEquals(newBook, result);
    }

    @Test
    public void updateBookAuthor() throws Exception {
        Book newBook = new Book(author, isbn_valid, title);
        MediaService service = new MediaServiceImpl();
        service.addBook(newBook);
        Book upBook = new Book("author2", isbn_valid, title);
        MediaServiceResult result = service.updateBook(isbn_valid, upBook);
        Assert.assertEquals(MediaServiceResult.OK, result);
        Medium rB = service.getBook(isbn_valid);
        Assert.assertEquals(upBook, rB);
    }

    @Test
    public void updateBookNull() throws Exception {
        Book newBook = new Book(author, isbn_valid, title);
        MediaService service = new MediaServiceImpl();
        service.addBook(newBook);

        MediaServiceResult result = service.updateBook(isbn_valid, null);

        Assert.assertEquals(MediaServiceResult.MISSING_CONTENT, result);
        Medium rB = service.getBook(isbn_valid);
        Assert.assertEquals(newBook, rB);
    }

    @Test
    public void updateBookTitle() throws Exception {
        Book newBook = new Book(author, isbn_valid, title);
        MediaService service = new MediaServiceImpl();
        service.addBook(newBook);
        Book upBook = new Book(author, isbn_valid, "title2");
        MediaServiceResult result = service.updateBook(isbn_valid, upBook);
        Assert.assertEquals(MediaServiceResult.OK, result);
        Medium rB = service.getBook(isbn_valid);
        Assert.assertEquals(upBook, rB);
    }

    @Test
    public void updateBookBoth() throws Exception {
        Book newBook = new Book(author, isbn_valid, title);
        MediaService service = new MediaServiceImpl();
        service.addBook(newBook);
        Book upBook = new Book("author2", isbn_valid, "title2");
        MediaServiceResult result = service.updateBook(isbn_valid, upBook);
        Assert.assertEquals(MediaServiceResult.OK, result);
        Medium rB = service.getBook(isbn_valid);
        Assert.assertEquals(upBook, rB);
    }

    @Test
    public void updateBookFail() throws Exception {
        Book newBook = new Book(author, isbn_valid, title);
        MediaService service = new MediaServiceImpl();
        service.addBook(newBook);
        Book upBook = new Book("author2", isbn_valid_2, "title2");
        MediaServiceResult result = service.updateBook(isbn_valid, upBook);
        Assert.assertEquals(MediaServiceResult.ISBN_IMMUTABLE, result);
    }

    @Test
    public void updateBookFailRessourceNotExists() throws Exception {
        MediaService service = new MediaServiceImpl();
        Book upBook = new Book("author2", isbn_valid, "title2");
        MediaServiceResult result = service.updateBook(isbn_valid, upBook);
        Assert.assertEquals(MediaServiceResult.NOT_FOUND, result);
    }

    @Test
    public void updateBookFailInvalidIsbn() throws Exception {
        MediaService service = new MediaServiceImpl();
        Book upBook = new Book("author2", isbn_invalid, "title2");
        MediaServiceResult result = service.updateBook(isbn_invalid, upBook);
        Assert.assertEquals(MediaServiceResult.ISBN_INVALID, result);
    }

    @Test
    public void checkBarcode() {
        String barcode = "5449000096241";
        Assert.assertTrue(MediaServiceImpl.checkBarcode(barcode));
    }

    @Test
    public void checkBarcodeNull() {
        Assert.assertFalse(MediaServiceImpl.checkBarcode(null));
    }

    @Test
    public void checkBarcodeToShort() {
        Assert.assertFalse(MediaServiceImpl.checkBarcode("1234567"));
    }

    @Test
    public void checkIsbn() {
        String isbn = "978-3-12-732320-7";
        Assert.assertTrue(MediaServiceImpl.checkIsbn(isbn));
    }

    @Test
    public void checkIsbn2() {
        String isbn = "978 3 12 732320 7";
        Assert.assertTrue(MediaServiceImpl.checkIsbn(isbn));
    }

    @Test
    public void checkIsbnFalse() {
        String isbn = "978-3-12-732320-8";
        Assert.assertFalse(MediaServiceImpl.checkIsbn(isbn));
    }

    @Test
    public void checkIsbnNull() {
        Assert.assertFalse(MediaServiceImpl.checkIsbn(null));
    }

    @Test
    public void checkIsbnToShort() {
        String isbn = "9783127323208";
        Assert.assertFalse(MediaServiceImpl.checkIsbn(isbn));
    }



}
