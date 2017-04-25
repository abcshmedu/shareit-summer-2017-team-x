package edu.hm.cs.swa.projekt_2.persistence;

import edu.hm.cs.swa.projekt_2.datamodel.Book;
import edu.hm.cs.swa.projekt_2.datamodel.Copy;
import edu.hm.cs.swa.projekt_2.datamodel.Disc;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DataStoreTest {

    @Test
    public void testEmptyStore() {

        DataStore store = new DataStore();

        assertEquals(store.getBooks().length, 0);
        assertEquals(store.getCopies().length, 0);
        assertEquals(store.getDiscs().length, 0);
    }

    @Test
    public void testAddBook() {

        DataStore store = new DataStore();

        Book book = new Book("", "", "");

        assertEquals(store.getBooks().length, 0);
        store.addBook(book);
        assertEquals(store.getBooks().length, 1);
        assertEquals(store.getBooks()[0], book);
    }

    @Test
    public void testAddDisc() {

        DataStore store = new DataStore();

        Disc disc = new Disc("", "", 0, "");

        assertEquals(store.getDiscs().length, 0);
        store.addDisc(disc);
        assertEquals(store.getDiscs().length, 1);
        assertEquals(store.getDiscs()[0], disc);

    }

    @Test
    public void testAddCopy() {

        DataStore store = new DataStore();

        Disc disc = new Disc("", "", 0, "");
        Copy copy = new Copy(disc, "bert");

        assertEquals(store.getCopies().length, 0);
        store.addCopy(copy);
        assertEquals(store.getCopies().length, 1);
        assertEquals(store.getCopies()[0], copy);

        DataStore.INSTANCE.reset();
    }

    @Test
    public void testGetByBarcode() {

        String barcode = "abcde";

        DataStore store = new DataStore();

        Disc disc = new Disc(barcode, "", 0, "");
        store.addDisc(disc);

        Book book = new Book("", barcode, "");
        store.addBook(book);

        assertEquals(store.getBook(barcode), book);
        assertEquals(store.getDisc(barcode), disc);
    }

    @Test
    public void testResetStore() {
        DataStore.INSTANCE.reset();

        String barcode = "abcde";

        DataStore store = DataStore.INSTANCE;

        Disc disc = new Disc(barcode, "director", 0, "");
        store.addDisc(disc);

        Book book = new Book("", barcode, "");
        store.addBook(book);

        assertEquals(1, store.getBooks().length);
        assertEquals(1, store.getDiscs().length);

        store.reset();
        store = DataStore.INSTANCE;

        assertEquals(0, store.getBooks().length);
        assertEquals(0, store.getDiscs().length);

    }

}
