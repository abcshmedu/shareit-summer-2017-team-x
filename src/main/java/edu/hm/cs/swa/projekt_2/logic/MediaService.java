package edu.hm.cs.swa.projekt_2.logic;

import edu.hm.cs.swa.projekt_2.datamodel.Book;
import edu.hm.cs.swa.projekt_2.datamodel.Disc;
import edu.hm.cs.swa.projekt_2.datamodel.Medium;
import edu.hm.cs.swa.projekt_2.datamodel.Token;

/**
 * This interface describes all available methods of our business logic.
 */
public interface MediaService {


	/**
	 * Add a new book to the list.
	 * 
	 * @param book
	 * @return {@link MediaServiceResult}, contains status information about the operation
	 */
    public MediaServiceResult addBook(Book book,String token);

    /**
     * Add a new disc to the list
     * @param disc
     * @return {@link MediaServiceResult}, contains status information about the operation

     */
    public MediaServiceResult addDisc(Disc disc,String token);

    /**
     * Returns an array containing all known books to the client
     * @return an array of books
     */
    public Medium[] getBooks(String token);

    /**
     * Searches for a book with the given ISBN. if none exists, it returns null
     * @param isbn
     * @return The found book or null
     */
    public Medium getBook(String isbn,String token);

    /**
     * Returns an array containing all known discs to the client
     * @return an array of discs
     */
    public Medium[] getDiscs(String token);

    /**
     * Searches for a disc with the given barcode. if none exists, it returns null
     * 
     * @param barcode
     * @return the found disc or null
     */
    public Medium getDisc(String barcode,String token);


    /**
     * This will search for a book with the specified ISBN. The book is then 
     * updated by replacing all values with the values from the given book, if they are
     * not null. Attention, the ISBN cannot be changed!
     * 
     * @param isbn
     * @param book
     * @return {@link MediaServiceResult}, contains status information about the operation
     */
    public MediaServiceResult updateBook(String isbn, Book book,String token);

    /**
     * This will search for a disc with the specified barcode. The disc is then 
     * updated by replacing all values with the values from the given disc, if they are
     * not null. Attention, the barcode cannot be changed!
     * @param barcode
     * @param disc
     * @return {@link MediaServiceResult}, contains status information about the operation

     */
    public MediaServiceResult updateDisc(String barcode, Disc disc,String token);

}
