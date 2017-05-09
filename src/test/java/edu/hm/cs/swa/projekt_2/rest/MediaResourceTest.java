package edu.hm.cs.swa.projekt_2.rest;

import edu.hm.cs.swa.projekt_2.datamodel.Book;
import edu.hm.cs.swa.projekt_2.datamodel.Disc;
import edu.hm.cs.swa.projekt_2.datamodel.Medium;
import edu.hm.cs.swa.projekt_2.persistence.DataStore;
import org.junit.Test;

import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MediaResourceTest {

    private final String ISBN = "978-3836217798";
    private final String ISBN2 = "879-3836217798";
    private final String author = "bert";
    private final String author2 = "karl";
    private final String title = "testbuch";
    private final String title2 = "kinderbuch";

    private final String barcode = "5449000096241";
    private final String barcode2 = "4003994155486";
    private final String director = "steven";
    private final String director2 = "spielberg";


    @Test
    public void testCreateBook() {

        MediaResource media = new MediaResource();
        DataStore.INSTANCE.reset();

        Book newBook = new Book(author, ISBN, title);

        Response response = media.createBook(newBook);

        assertEquals(response.getStatus(), 201);
    }

    @Test
    public void testGetBook() {

        MediaResource media = new MediaResource();
        DataStore.INSTANCE.reset();

        Book newBook = new Book(author, ISBN, title);

        media.createBook(newBook);

        Response response = media.getBook(ISBN);
        assertEquals(200, response.getStatus());

        Object o = response.getEntity();
        assertTrue(o instanceof Book);

        Book book = (Book) o;

        assertEquals(author, book.getAuthor());
        assertEquals(ISBN.replaceAll("[^0-9]", ""), book.getIsbn());
        assertEquals(title, book.getTitle());

    }

    @Test
    public void testGetBookNoISBN() {

        MediaResource media = new MediaResource();
        DataStore.INSTANCE.reset();

        Book newBook = new Book(author, ISBN, title);

        media.createBook(newBook);

        Response response = media.getBook("");
        assertEquals(404, response.getStatus());
    }

    @Test
    public void testGetBooks() {

        MediaResource media = new MediaResource();
        DataStore.INSTANCE.reset();

        Book newBook = new Book(author, ISBN, title);
        media.createBook(newBook);

        Book newBook2 = new Book(author + "1", ISBN2, title + "1");
        media.createBook(newBook2);

        Response response = media.getBooks();

        assertEquals(response.getStatus(), 200);

        Object o = response.getEntity();

        assertTrue(o instanceof Medium[]);

        Medium[] books = (Medium[]) o;

        assertEquals(2, books.length);

    }

    @Test
    public void testUpdateBook() {

        MediaResource media = new MediaResource();
        DataStore.INSTANCE.reset();

        Book newBook = new Book(author, ISBN, title);
        media.createBook(newBook);

        Book newBook2 = new Book(author2, ISBN, title2);

        Response response = media.updateBook(ISBN, newBook2);

        assertEquals(200, response.getStatus());

        response = media.getBook(ISBN);
        Book book = (Book) response.getEntity();

        assertEquals(author2, book.getAuthor());
        assertEquals(title2, book.getTitle());

    }

    @Test
    public void testCreateDisc() {

        MediaResource media = new MediaResource();
        DataStore.INSTANCE.reset();

        Disc disc = new Disc(barcode, director, 10, title);

        Response response = media.createDisc(disc);

        assertEquals(response.getStatus(), 201);
    }

    @Test
    public void testGetDiscBarcode() {

        MediaResource media = new MediaResource();
        DataStore.INSTANCE.reset();

        Disc newDisc = new Disc(barcode, director, 10, title);

        media.createDisc(newDisc);

        Response response = media.getDisc(barcode);
        assertEquals(200, response.getStatus());

        Object o = response.getEntity();
        assertTrue(o instanceof Disc);

        Disc disc = (Disc) o;

        assertEquals(director, disc.getDirector());
        assertEquals(title, disc.getTitle());

    }

    @Test
    public void testGetDisc() {

        MediaResource media = new MediaResource();
        DataStore.INSTANCE.reset();

        Disc newDisc = new Disc(barcode, director, 10, title);
        media.createDisc(newDisc);

        Disc newDisc2 = new Disc(barcode2, director2, 10, title2);
        media.createDisc(newDisc2);

        Response response = media.getDiscs();

        assertEquals(200, response.getStatus());

        Object o = response.getEntity();

        assertTrue(o instanceof Medium[]);

        Medium[] medien = (Medium[]) o;

        assertEquals(2, medien.length);
    }

    @Test
    public void testUpdateDisc() {

        MediaResource media = new MediaResource();
        DataStore.INSTANCE.reset();

        Disc newDisc = new Disc(barcode, director, 10, title);
        media.createDisc(newDisc);

        Disc newDisc2 = new Disc(barcode, director2, 10, title2);


        Response response = media.updateDiscs(barcode, newDisc2);

        assertEquals(200, response.getStatus());

        response = media.getDisc(barcode);
        Disc disc = (Disc) response.getEntity();

        assertEquals(director2, disc.getDirector());
        assertEquals(title2, disc.getTitle());

    }

}
