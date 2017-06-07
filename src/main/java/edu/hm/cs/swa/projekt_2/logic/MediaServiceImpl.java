package edu.hm.cs.swa.projekt_2.logic;

import edu.hm.cs.swa.projekt_2.datamodel.Book;
import edu.hm.cs.swa.projekt_2.datamodel.Disc;
import edu.hm.cs.swa.projekt_2.datamodel.Medium;
import edu.hm.cs.swa.projekt_2.persistence.DataStore;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * this is an implementation of the {@linkplain MediaService} interface.
 */
public class MediaServiceImpl implements MediaService {

    private Logger LOGGER = LogManager.getLogger(MediaServiceResult.class);

    @Override
    public MediaServiceResult addBook(Book book) {

        if (book == null)
            return MediaServiceResult.MISSING_CONTENT;
        if (!checkIsbn(book.getIsbn()))
            return MediaServiceResult.ISBN_INVALID; // Ungülitge ISBN

        // ISBN wird vereinheitlicht um unterschiedliche Füllzeichen herauszufiltern.
        book.setIsbn(book.getIsbn().replaceAll("[^0-9]", ""));
        if (containsIsbn(book.getIsbn()))
            return MediaServiceResult.ALREADY_EXISTS; // ISBN bereits vorhanden
        if (book.getTitle() == null || book.getTitle().isEmpty())
            return MediaServiceResult.MISSING_TITLE; // Titel fehlt
        if (book.getAuthor() == null || book.getAuthor().isEmpty())
            return MediaServiceResult.MISSING_AUTHOR; // Autor fehlt

        DataStore.INSTANCE.addBook(book);
        LOGGER.info("New book added: " + book.toString());

        return MediaServiceResult.CREATED;
    }

    static boolean checkIsbn(String isbn) {
        final String REGEX_ISBN_13 = "[0-9]{3}.?[0-9].?[0-9]{2}.?[0-9]{6}.?[0-9]";
        if (isbn == null || !Pattern.matches(REGEX_ISBN_13, isbn))
            return false;
        int[] z = isbn.replaceAll("[^0-9]", "").chars().map(Character::getNumericValue).toArray();
        return 0 == (10 - ((z[0] + z[2] + z[4] + z[6] + z[8] + z[10] + z[12] + 3 * (z[1] + z[3] + z[5] + z[7] + z[9] + z[11])) % 10)) % 10;
    }

    static boolean checkBarcode(String barcode) {
        final String REGEX_BARCODE = "[0-9]{8,13}";
        if (barcode == null || !Pattern.matches(REGEX_BARCODE, barcode))
            return false;
        int[] z = barcode.replaceAll("[^0-9]", "").chars().map(Character::getNumericValue).toArray();
        int pruefsumme = z[z.length - 1];
        z = Arrays.copyOf(z, z.length - 1);
        int sum = 0;
        int multiplier = 3;
        for (int i = z.length - 1; i >= 0; i--) {
            sum += z[i] * multiplier;
            multiplier = (multiplier == 3) ? 1 : 3;
        }
        int sumPlus9 = sum + 9;
        int nextMultipleOfTen = sumPlus9 - (sumPlus9 % 10); // nextMultipleOfTen ist jetzt das nächste Vielfache von zehn
        return pruefsumme == nextMultipleOfTen - sum;
    }

    @Override
    public MediaServiceResult addDisc(Disc disc) {
        if (disc == null)
            return MediaServiceResult.MISSING_CONTENT;
        if (!checkBarcode(disc.getBarcode()))
            return MediaServiceResult.BARCODE_INVALID; // Ungülitge Barcode

        // Barcode wird vereinheitlicht um unterschiedliche Füllzeichen herauszufiltern.
        disc.setBarcode(disc.getBarcode().replaceAll("[^0-9]", ""));
        if (containsBarcode(disc.getBarcode()))
            return MediaServiceResult.ALREADY_EXISTS; // Barcode bereits vorhanden
        if (disc.getTitle() == null || disc.getTitle().isEmpty())
            return MediaServiceResult.MISSING_TITLE; // Titel fehlt
        if (disc.getDirector() == null || disc.getDirector().isEmpty())
            return MediaServiceResult.MISSING_DIRECTOR; // Director fehlt
        if (disc.getFsk() == null)
            return MediaServiceResult.MISSING_FSK;

        DataStore.INSTANCE.addDisc(disc);
        LOGGER.info("New disc added: " + disc.toString());
        return MediaServiceResult.CREATED;
    }

    private boolean containsIsbn(String isbn) {
        return Arrays.stream(DataStore.INSTANCE.getBooks()).anyMatch(i -> ((Book) i).getIsbn().equals(isbn));
    }

    private boolean containsBarcode(String barcode) {
        return Arrays.stream(DataStore.INSTANCE.getDiscs()).anyMatch(i -> ((Disc) i).getBarcode().equals(barcode));
    }

    @Override
    public Medium[] getBooks() {
        return DataStore.INSTANCE.getBooks();
    }

    @Override
    public Medium getBook(String isbn) {
        return DataStore.INSTANCE.getBook(isbn.replaceAll("[^0-9]", ""));
    }

    @Override
    public Medium getDisc(String barcode) {
        return DataStore.INSTANCE.getDisc(barcode.replaceAll("[^0-9]", ""));
    }

    @Override
    public Medium[] getDiscs() {
        return DataStore.INSTANCE.getDiscs();
    }

    /**
     * updates a single book.
     *
     * @param isbn identifier of a book
     * @param book Book with only changing values
     */
    @Override
    public MediaServiceResult updateBook(String isbn, Book book) {
        isbn = isbn.replaceAll("[^0-9]", "");
        if (book == null)
            return MediaServiceResult.MISSING_CONTENT;

        if (!checkIsbn(isbn))
            return MediaServiceResult.ISBN_INVALID;

        Book old = (Book) DataStore.INSTANCE.getBook(isbn);

        if (old == null)
            return MediaServiceResult.NOT_FOUND;

        book.setIsbn(book.getIsbn().replaceAll("[^0-9]", ""));
        if (book.getIsbn() != null && !isbn.equals(book.getIsbn()))
            return MediaServiceResult.ISBN_IMMUTABLE;

        if (book.getAuthor() != null && !old.getAuthor().equals(book.getAuthor()))
            old.setAuthor(book.getAuthor());

        if (book.getTitle() != null && !old.getTitle().equals(book.getTitle()))
            old.setTitle(book.getTitle());

        return MediaServiceResult.OK;
    }

    @Override
    public MediaServiceResult updateDisc(String barcode, Disc disc) {
        barcode = barcode.replaceAll("[^0-9]", "");
        if (disc == null)
            return MediaServiceResult.MISSING_CONTENT;

        if (!checkBarcode(barcode))
            return MediaServiceResult.BARCODE_INVALID;

        Disc old = (Disc) DataStore.INSTANCE.getDisc(barcode);

        if (old == null)
            return MediaServiceResult.NOT_FOUND;

        disc.setBarcode(disc.getBarcode().replaceAll("[^0-9]", ""));

        if (disc.getBarcode() != null && !barcode.equals(disc.getBarcode()))
            return MediaServiceResult.BARCODE_IMMUTABLE;

        if (disc.getDirector() != null && !old.getDirector().equals(disc.getDirector()))
            old.setDirector(disc.getDirector());

        if (disc.getTitle() != null && !old.getTitle().equals(disc.getTitle()))
            old.setTitle(disc.getTitle());

        if (disc.getFsk() != null && !old.getFsk().equals(disc.getFsk()))
            old.setFsk(disc.getFsk());

        return MediaServiceResult.OK;
    }
}
