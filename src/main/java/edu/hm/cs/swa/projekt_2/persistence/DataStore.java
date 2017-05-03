package edu.hm.cs.swa.projekt_2.persistence;

import edu.hm.cs.swa.projekt_2.datamodel.Book;
import edu.hm.cs.swa.projekt_2.datamodel.Copy;
import edu.hm.cs.swa.projekt_2.datamodel.Disc;
import edu.hm.cs.swa.projekt_2.datamodel.Medium;

import java.util.LinkedList;
import java.util.List;

/**
 * Basic datastore that does not persist elements to the harddrive or database.
 * Elements are volatile only!
 *
 */
public class DataStore {
    public static DataStore INSTANCE = new DataStore();

    private List<Medium> bookList;
    private List<Medium> discList;
    private List<Copy> copyList;

    /**
     * Default constructor, creates an empty datastore
     */
    protected DataStore() {
    	
        bookList = new LinkedList<>();
        discList = new LinkedList<>();
        copyList = new LinkedList<>();
    }

    /**
     * Assignes the {@link #INSTANCE} referece a new and clear datastore
     */
    public void reset() {
        INSTANCE = new DataStore();
    }

    /**
     * Returns an array of all known books
     * @return
     */
    public Medium[] getBooks() {
        return bookList.toArray(new Medium[bookList.size()]);
    }

    /**
     * Returns an array of all known discs
     * @return
     */
    public Medium[] getDiscs() {
        return discList.toArray(new Medium[discList.size()]);
    }


    /**
     * Searches in all known discs for a disc with the given barcode. 
     * Returns null if none was found
     * @param barcode
     * @return
     */
    public Medium getDisc(String barcode) {
        return discList.stream().filter(i -> ((Disc) i).getBarcode().equals(barcode)).findFirst().orElse(null);
    }

    /**
     * Returns an array of all known copies
     * @return
     */
    public Copy[] getCopies() {
        return copyList.toArray(new Copy[copyList.size()]);
    }

    /**
     * Adds a new book to the store
     * 
     * @param book
     */
    public void addBook(Book book) {
        bookList.add(book);
    }

    /**
     * Searches in all known books for a book with the given ISBN.
     * Returns null if none was found.
     * @param isbn
     * @return
     */
    public Medium getBook(String isbn) {
        return bookList.stream().filter(i -> ((Book) i).getIsbn().equals(isbn)).findFirst().orElse(null);
    }

    /**
     * Adds a new copy to the list of known copies.
     * @param copy
     */
    public void addCopy(Copy copy) {
        copyList.add(copy);
    }

    /**
     * Adds a new Disc to the list of known discs.
     * @param disc
     */
    public void addDisc(Disc disc) {
        discList.add(disc);
    }
}
