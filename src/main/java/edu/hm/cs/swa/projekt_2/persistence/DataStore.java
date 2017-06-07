package edu.hm.cs.swa.projekt_2.persistence;

import edu.hm.cs.swa.projekt_2.datamodel.Book;
import edu.hm.cs.swa.projekt_2.datamodel.Copy;
import edu.hm.cs.swa.projekt_2.datamodel.Disc;
import edu.hm.cs.swa.projekt_2.datamodel.Medium;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.persistence.NoResultException;
import java.util.List;

/**
 * Basic datastore that does not persist elements to the harddrive or database.
 * Elements are volatile only!
 */
public class DataStore {
    public static DataStore INSTANCE = new DataStore();

    private static final Logger LOGGER = LogManager.getLogger(DataStore.class);

    private SessionFactory sessionFactory;

    /**
     * Default constructor, creates an empty datastore
     */
    protected DataStore() {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            LOGGER.error("Failed to setup Session", e);
            // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
            // so destroy it manually.
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    private SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    /**
     * Assignes the {@link #INSTANCE} referece a new and clear datastore
     */
    public void reset() {
        INSTANCE = new DataStore();
    }

    /**
     * Returns an array of all known books
     *
     * @return
     */
    public Medium[] getBooks() {
        Session session = getSessionFactory().openSession();
        session.beginTransaction();
        List<Book> bookList = session.createQuery("from Book", Book.class).list();
        session.getTransaction().commit();
        session.close();
        return bookList.toArray(new Medium[bookList.size()]);
    }

    /**
     * Returns an array of all known discs
     *
     * @return
     */
    public Medium[] getDiscs() {
        Session session = getSessionFactory().openSession();
        session.beginTransaction();
        List<Disc> discList = session.createQuery("from Disc", Disc.class).list();
        session.getTransaction().commit();
        session.close();
        return discList.toArray(new Medium[discList.size()]);
    }


    /**
     * Searches in all known discs for a disc with the given barcode.
     * Returns null if none was found
     *
     * @param barcode
     *
     * @return
     */
    public Medium getDisc(String barcode) {
        Session session = getSessionFactory().openSession();
        session.beginTransaction();
        Disc disc = null;
        try {
            disc = session.createQuery("from Disc where barcode = :barcode", Disc.class).setParameter("barcode", barcode).getSingleResult();
        } catch (NoResultException nre) {
            // disc is already null. so nothing to do
        }
        session.getTransaction().commit();
        session.close();

        return disc;
        //return discList.stream().filter(i -> ((Disc) i).getBarcode().equals(barcode)).findFirst().orElse(null);
    }

    /**
     * Returns an array of all known copies
     *
     * @return
     */
    public Copy[] getCopies() {
        Session session = getSessionFactory().openSession();
        session.beginTransaction();
        List<Copy> copyList = session.createQuery("from Copy", Copy.class).list();
        session.getTransaction().commit();
        session.close();
        return copyList.toArray(new Copy[copyList.size()]);
    }

    /**
     * Adds a new book to the store
     *
     * @param book
     */
    public void addBook(Book book) {
        saveObject(book);
    }

    /**
     * Searches in all known books for a book with the given ISBN.
     * Returns null if none was found.
     *
     * @param isbn
     *
     * @return
     */
    public Medium getBook(String isbn) {

        Session session = getSessionFactory().openSession();
        session.beginTransaction();
        Book book = null;
        try {
            book = session.createQuery("from Book where isbn = :isbn", Book.class).setParameter("isbn", isbn).getSingleResult();
        } catch (NoResultException nre) {
            // book is already null. so nothing to do
        }
        session.getTransaction().commit();
        session.close();

        return book;
        //return bookList.stream().filter(i -> ((Book) i).getIsbn().equals(isbn)).findFirst().orElse(null);
    }

    /**
     * Adds a new copy to the list of known copies.
     *
     * @param copy
     */
    public void addCopy(Copy copy) {
        saveObject(copy);
    }

    /**
     * Adds a new Disc to the list of known discs.
     *
     * @param disc
     */
    public void addDisc(Disc disc) {
        saveObject(disc);
    }

    private void saveObject(Object o) {
        Session session = getSessionFactory().openSession();
        session.beginTransaction();
        session.save(o);
        session.getTransaction().commit();
        session.close();
    }
}
