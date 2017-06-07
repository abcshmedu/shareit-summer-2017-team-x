package edu.hm.cs.swa.projekt_2.datamodel;

import static org.junit.Assert.*;

import org.junit.Test;

public class CopyTest {
	
	final String owner = "Bert";

	@Test
	public void testConstructor() {
		
		Medium book = new Book();
		
		Copy copy = new Copy(book, owner);
		
		assertEquals(copy.getMedium(), book);
		assertEquals(copy.getCopyOwner(), owner);
	}

}
