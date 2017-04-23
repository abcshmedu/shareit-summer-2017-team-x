package edu.hm.cs.swa.projekt_2.persistence;

import edu.hm.cs.swa.projekt_2.datamodel.Book;
import edu.hm.cs.swa.projekt_2.datamodel.Copy;
import edu.hm.cs.swa.projekt_2.datamodel.Disc;
import edu.hm.cs.swa.projekt_2.datamodel.Medium;

import java.util.LinkedList;
import java.util.List;

public class DataStore {
    public static DataStore INSTANCE = new DataStore();

    private List<Medium> bookList;
    private List<Medium> discList;
    private List<Copy> copyList;

    private DataStore() {
        bookList = new LinkedList<>();
        discList = new LinkedList<>();
        copyList = new LinkedList<>();
    }

    public void reset() {
        INSTANCE = new DataStore();
    }

    public Medium[] getBooks() {
        return bookList.toArray(new Medium[bookList.size()]);
    }

    public Medium[] getDiscs() {
        return discList.toArray(new Medium[discList.size()]);
    }


    public Medium getDisc(String barcode) {
        return discList.stream().filter(i -> ((Disc) i).getBarcode().equals(barcode)).findFirst().orElse(null);
    }

    public Copy[] getCopies() {
        return copyList.toArray(new Copy[copyList.size()]);
    }

    public void addBook(Book book) {
        bookList.add(book);
    }

    public Medium getBook(String isbn) {
        return bookList.stream().filter(i -> ((Book) i).getIsbn().equals(isbn)).findFirst().orElse(null);
    }

    public void addCopy(Copy copy) {
        copyList.add(copy);
    }

    public void addDisc(Disc disc) {
        discList.add(disc);
    }
}
