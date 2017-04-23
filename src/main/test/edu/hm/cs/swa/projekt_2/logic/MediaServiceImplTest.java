package edu.hm.cs.swa.projekt_2.logic;

import edu.hm.cs.swa.projekt_2.datamodel.Book;
import edu.hm.cs.swa.projekt_2.datamodel.Medium;
import edu.hm.cs.swa.projekt_2.persistence.DataStore;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by christian on 23.04.17.
 */
public class MediaServiceImplTest {
    @Before
    public void setUp() {
        DataStore.INSTANCE.reset();
    }

    @Test
    public void addBook() throws Exception {
        Book newBook = new Book("author", "978-3836217798", "title");
        MediaService service = new MediaServiceImpl();
        service.addBook(newBook);
        Assert.assertEquals(1, DataStore.INSTANCE.getBooks().length);
    }

    @Test
    public void addBookTwice() throws Exception {
        Book newBook = new Book("author", "978-3836217798", "title");
        MediaService service = new MediaServiceImpl();
        MediaServiceResult r1 = service.addBook(newBook);
        MediaServiceResult r2 = service.addBook(newBook);
        Assert.assertEquals(1, DataStore.INSTANCE.getBooks().length);
        Assert.assertEquals(MediaServiceResult.OK, r1);
        Assert.assertEquals(MediaServiceResult.NOT_FOUND, r2);
    }

    @Test
    public void addDisc() throws Exception {
    }

    @Test
    public void getBooks() throws Exception {
        Book newBook = new Book("author", "978-3836217798", "title");
        MediaService service = new MediaServiceImpl();
        service.addBook(newBook);
        Assert.assertEquals(1, service.getBooks().length);
        Assert.assertEquals(newBook, service.getBooks()[0]);
    }

    @Test
    public void getBook() throws Exception {
        Book newBook = new Book("author", "978-3836217798", "title");
        MediaService service = new MediaServiceImpl();
        service.addBook(newBook);
        Medium result = service.getBook(newBook.getIsbn());
        Assert.assertEquals(newBook, result);
    }

    @Test
    public void getDisc() throws Exception {
    }

    @Test
    public void getDiscs() throws Exception {
    }

    @Test
    public void updateBook() throws Exception {
    }

    @Test
    public void updateDisc() throws Exception {
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