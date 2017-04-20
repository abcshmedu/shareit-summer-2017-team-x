package edu.hm.cs.swa.projekt_2.logic;

import edu.hm.cs.swa.projekt_2.datamodel.Book;
import edu.hm.cs.swa.projekt_2.datamodel.Disc;
import edu.hm.cs.swa.projekt_2.datamodel.Medium;
import edu.hm.cs.swa.projekt_2.persistence.DataStore;

import java.util.logging.Logger;


public class MediaServiceImpl implements MediaService {

    Logger LOGGER = Logger.getLogger(MediaServiceResult.class.getName());


    @Override
    public MediaServiceResult addBook(Book book) {
        // TODO Barcode handling (Aufgabenstellung beachten)
        DataStore.INSTANCE.addBook(book);
        LOGGER.info("New book added: " + book.toString());
        return MediaServiceResult.OK;
    }

    @Override
    public MediaServiceResult addDisc(Disc disc) {
        // TODO Barcode handling (Aufgabenstellung beachten)
        DataStore.INSTANCE.addDisc(disc);
        LOGGER.info("New disc added: " + disc.toString());
        return MediaServiceResult.OK;
    }


    @Override
    public Medium[] getBooks() {
        return DataStore.INSTANCE.getBooks();
    }

    @Override
    public Medium[] getDiscs() {
        return DataStore.INSTANCE.getDiscs();
    }

    @Override
    public MediaServiceResult updateBook(Book book) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public MediaServiceResult updateDisc(Disc disc) {
        // TODO Auto-generated method stub
        return null;
    }
}
