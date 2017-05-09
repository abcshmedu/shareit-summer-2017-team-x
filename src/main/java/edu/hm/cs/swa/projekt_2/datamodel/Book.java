package edu.hm.cs.swa.projekt_2.datamodel;

/**
 * Representing a Book, derived from {@linkplain Medium}
 */
public class Book extends Medium {

    private String author;


    private String isbn;

    /**
     * Default constructor for serialization
     */
    Book() {
    }

    /**
     * Parameterized constructor for creating a new element
     *
     * @param author
     * @param isbn
     * @param title
     */
    public Book(String author, String isbn, String title) {
        super(title);
        // TODO Auto-generated constructor stub

        this.author = author;
        this.isbn = isbn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        if (!super.equals(o)) return false;

        Book book = (Book) o;

        if (!getAuthor().equals(book.getAuthor())) return false;
        return getIsbn().equals(book.getIsbn());
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + getAuthor().hashCode();
        result = 31 * result + getIsbn().hashCode();
        return result;
    }

    @Override
    public String toString() {

        return "Book{" + super.toString() +
                ", author='" + author + '\'' +
                ", isbn='" + isbn + '\'' +
                '}';
    }

    /**
     * Returns the author or null, if none was set
     *
     * @return String the author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Returns the ISBN or null, if none was set
     *
     * @return String the ISBN
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * Changes the ISBN.
     *
     * @param isbn String of the new ISBN
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     * Sets the author for this book. Can be null
     *
     * @param author
     */
    public void setAuthor(String author) {
        this.author = author;
    }
}
