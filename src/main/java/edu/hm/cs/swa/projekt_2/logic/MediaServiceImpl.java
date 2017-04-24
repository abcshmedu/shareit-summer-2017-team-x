package edu.hm.cs.swa.projekt_2.logic;

import edu.hm.cs.swa.projekt_2.datamodel.Book;
import edu.hm.cs.swa.projekt_2.datamodel.Disc;
import edu.hm.cs.swa.projekt_2.datamodel.Medium;
import edu.hm.cs.swa.projekt_2.persistence.DataStore;

import java.util.Arrays;
import java.util.logging.Logger;
import java.util.regex.Pattern;


public class MediaServiceImpl implements MediaService {

    private Logger LOGGER = Logger.getLogger(MediaServiceResult.class.getName());

    private static String REGEX_ISBN_13 = "[0-9]{3}.?[0-9].?[0-9]{2}.?[0-9]{6}.?[0-9]";
    private static String REGEX_BARCODE = "[0-9]{8,13}";

    @Override
    public MediaServiceResult addBook(Book book) {
        if (!checkIsbn(book.getIsbn()))
            return MediaServiceResult.NOT_FOUND; // Ungülitge ISBN
        if (containsIsbn(book.getIsbn()))
            return MediaServiceResult.NOT_FOUND; // ISBN bereits vorhanden
        if (book.getTitle() == null || book.getTitle().isEmpty())
            return MediaServiceResult.NOT_FOUND; // Titel fehlt
        if (book.getAuthor() == null || book.getAuthor().isEmpty())
            return MediaServiceResult.NOT_FOUND; // Autor fehlt

        DataStore.INSTANCE.addBook(book);
        LOGGER.info("New book added: " + book.toString());

        return MediaServiceResult.OK;
    }

    static boolean checkIsbn(String isbn) {
        if (isbn == null || !Pattern.matches(REGEX_ISBN_13, isbn))
            return false;
        int[] z = isbn.replaceAll("[^0-9]", "").chars().map(Character::getNumericValue).toArray();
        return 0 == (10 - ((z[0] + z[2] + z[4] + z[6] + z[8] + z[10] + z[12] + 3 * (z[1] + z[3] + z[5] + z[7] + z[9] + z[11])) % 10)) % 10;
    }

    static boolean checkBarcode(String barcode) {
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

        if (!checkIsbn(disc.getBarcode()))
            return MediaServiceResult.NOT_FOUND; // Ungülitge Barcode
        if (containsBarcode(disc.getBarcode()))
            return MediaServiceResult.NOT_FOUND; // Barcode bereits vorhanden
        if (disc.getTitle() == null || disc.getTitle().isEmpty())
            return MediaServiceResult.NOT_FOUND; // Titel fehlt
        if (disc.getDirector() == null || disc.getDirector().isEmpty())
            return MediaServiceResult.NOT_FOUND; // Director fehlt

        DataStore.INSTANCE.addDisc(disc);
        LOGGER.info("New disc added: " + disc.toString());
        return MediaServiceResult.OK;
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
        return DataStore.INSTANCE.getBook(isbn);
    }

    public Medium getDisc(String barcode) {
        return DataStore.INSTANCE.getDisc(barcode);
    }

    @Override
    public Medium[] getDiscs() {
        return DataStore.INSTANCE.getDiscs();
    }

    @Override
    public MediaServiceResult updateBook(String isbn, Book book) {
        if (!checkIsbn(isbn) || !isbn.equals(book.getIsbn()))
            return MediaServiceResult.NOT_FOUND;

        Book old = (Book) DataStore.INSTANCE.getBook(isbn);

        if (!old.getAuthor().equals(book.getAuthor()))
            old.setAuthor(book.getAuthor());

        if (!old.getTitle().equals(book.getTitle()))
            old.setTitle(book.getTitle());

        return MediaServiceResult.OK;
    }

    @Override
    public MediaServiceResult updateDisc(String barcode, Disc disc) {
        if (!checkBarcode(barcode) || !barcode.equals(disc.getBarcode()))
            return MediaServiceResult.NOT_FOUND;

        Disc old = (Disc) DataStore.INSTANCE.getDisc(barcode);

        if (!old.getDirector().equals(disc.getDirector()))
            old.setDirector(disc.getDirector());

        if (old.getFsk() != disc.getFsk())
            old.setFsk(disc.getFsk());

        if (!old.getTitle().equals(disc.getTitle()))
            old.setTitle(disc.getTitle());

        return MediaServiceResult.OK;
    }
}
