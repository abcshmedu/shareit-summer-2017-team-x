package edu.hm.cs.swa.projekt_2.datamodel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class BookTest {
	
	final String author = "test дь$`?";
	final String isbn ="abc123";
	final String title ="testbook";

	@Test
	public void testDefaultConstructor(){
		
		Book book = new Book();
		
		assertNotNull(book);
		
		assertEquals(book.getAuthor(),"");
		assertEquals(book.getIsbn(),"");
		assertEquals(book.getTitle(),"");
	}
	
	
	@Test
	public void testParameterizedConstructor() {
		
		
		Book book = new Book(author, isbn, title);
		
		assertEquals(author, book.getAuthor());
		assertEquals(isbn, book.getIsbn());
		assertEquals(title, book.getTitle());
		
	}
	
	@Test
	public void testSetAuthor() {
		
		Book book = new Book();
		
		book.setAuthor(author);
		assertTrue(author.equals(book.getAuthor()));

	}
	
	@Test
	public void testToString(){
		
		Book book = new Book(author, isbn, title);
		
		book.toString();
		
		assertEquals(book.toString(),"Book{Medium{title='testbook'}, author='test дь$`?', isbn='abc123'}");
		
	}
	
	@Test
	public void testHashcode(){
		
		Book book = new Book(author, isbn, title);
		
		// static hash code
		assertEquals(book.hashCode(), -527491186);
	}
	
	@Test
	public void testEquals(){
		
		Book book = new Book(author, isbn, title);
		Book book2 = new Book("", isbn, "");
		
		assertTrue(book.equals(book));
		
		assertFalse(book.equals(new Integer(1)));
		
		assertFalse(book.equals(book2));
		
		book2.setTitle(title);
		assertFalse(book.equals(book2));
		
		book2.setAuthor(author);
		assertTrue(book.equals(book2));
	}

}
