package edu.hm.cs.swa.projekt_2.datamodel;

public class Book extends Medium {

	private String author;
	private String isbn;
	
	
	public Book(String author, String isbn, String title) {		
		super(title);
		// TODO Auto-generated constructor stub
		
		this.author = author;
		this.isbn = isbn;
	}
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

	public String getAuthor() {
		return author;
	}

	public String getIsbn() {
		return isbn;
	}
	
	

}
