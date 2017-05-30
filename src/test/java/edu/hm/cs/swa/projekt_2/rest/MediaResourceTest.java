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
    private final String token = "";


    @Test
    public void testCreateBook() {

        MediaResource media = new MediaResource();
        DataStore.INSTANCE.reset();

        Book newBook = new Book(author, ISBN, title);

        Response response = media.createBook(newBook, token);

        assertEquals(response.getStatus(), 201);
    }

    @Test
    public void testGetBook() {

        MediaResource media = new MediaResource();
        DataStore.INSTANCE.reset();

        Book newBook = new Book(author, ISBN, title);

        media.createBook(newBook, token);

        Response response = media.getBook(ISBN, token);
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

        media.createBook(newBook, token);

        Response response = media.getBook("", token);
        assertEquals(404, response.getStatus());
    }

    @Test
    public void testGetBooks() {

        MediaResource media = new MediaResource();
        DataStore.INSTANCE.reset();

        Book newBook = new Book(author, ISBN, title);
        media.createBook(newBook, token);

        Book newBook2 = new Book(author + "1", ISBN2, title + "1");
        media.createBook(newBook2, token);

        Response response = media.getBooks(token);

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
        media.createBook(newBook, token);

        Book newBook2 = new Book(author2, ISBN, title2);

        Response response = media.updateBook(ISBN, newBook2, token);

        assertEquals(200, response.getStatus());

        response = media.getBook(ISBN, token);
        Book book = (Book) response.getEntity();

        assertEquals(author2, book.getAuthor());
        assertEquals(title2, book.getTitle());

    }

    @Test
    public void testCreateDisc() {

        MediaResource media = new MediaResource();
        DataStore.INSTANCE.reset();

        Disc disc = new Disc(barcode, director, 10, title);

        Response response = media.createDisc(disc, token);

        assertEquals(response.getStatus(), 201);
    }

    @Test
    public void testGetDiscBarcode() {

        MediaResource media = new MediaResource();
        DataStore.INSTANCE.reset();

        Disc newDisc = new Disc(barcode, director, 10, title);

        media.createDisc(newDisc, token);

        Response response = media.getDisc(barcode, token);
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
        media.createDisc(newDisc, token);

        Disc newDisc2 = new Disc(barcode2, director2, 10, title2);
        media.createDisc(newDisc2, token);

        Response response = media.getDiscs(token);

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
        media.createDisc(newDisc, token);

        Disc newDisc2 = new Disc(barcode, director2, 10, title2);


        Response response = media.updateDiscs(barcode, newDisc2, token);

        assertEquals(200, response.getStatus());

        response = media.getDisc(barcode, token);
        Disc disc = (Disc) response.getEntity();

        assertEquals(director2, disc.getDirector());
        assertEquals(title2, disc.getTitle());

    }

}
