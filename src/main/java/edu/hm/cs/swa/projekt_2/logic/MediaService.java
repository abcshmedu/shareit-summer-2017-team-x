package edu.hm.cs.swa.projekt_2.logic;

import edu.hm.cs.swa.projekt_2.datamodel.Book;
import edu.hm.cs.swa.projekt_2.datamodel.Disc;
import edu.hm.cs.swa.projekt_2.datamodel.Medium;

public interface MediaService {


    public MediaServiceResult addBook(Book book);

    public MediaServiceResult addDisc(Disc disc);

    public Medium[] getBooks();

    public Medium getBook(String isbn);

    public Medium[] getDiscs();

    public Medium getDisc(String barcode);


    public MediaServiceResult updateBook(String isbn, Book book);

    public MediaServiceResult updateDisc(String barcode, Disc disc);

}
