package edu.hm.cs.swa.projekt_2.datamodel;

public class Book extends Medium {

    private String author ="";
    private String isbn ="";

    Book() {
    }

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

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
