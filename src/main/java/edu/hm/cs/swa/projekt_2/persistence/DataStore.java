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
        bookList.add(new Book("hans", "blabla", "blubdiwub"));
        bookList.add(new Book("Günther", "asdasderfiuhch", "ÜmlÄÜte in Ällen Lebenslägen"));

        discList = new LinkedList<>();
        copyList = new LinkedList<>();
    }


    public Medium[] getBooks() {
        return bookList.toArray(new Medium[bookList.size()]);
    }

    public Medium[] getDiscs() {
        return discList.toArray(new Medium[discList.size()]);
    }

    public Copy[] getCopies() {
        return copyList.toArray(new Copy[copyList.size()]);
    }

    public void addBook(Book book) {
        bookList.add(book);
    }

    public void addCopy(Copy copy) {
        copyList.add(copy);
    }

    public void addDisc(Disc disc) {
        discList.add(disc);
    }
}
