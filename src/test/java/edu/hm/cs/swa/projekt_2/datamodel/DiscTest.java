package edu.hm.cs.swa.projekt_2.datamodel;

import static org.junit.Assert.*;

import java.nio.channels.AsynchronousServerSocketChannel;

import org.junit.Test;

public class DiscTest {

	private final String barcode ="abcde";
	private final String director ="spielberg";
	private final String title ="test";
	private final int fsk = 5;
	
	@Test
	public void testDefaultConstructor() {
		
		Disc disc = new Disc();
		
		assertEquals(disc.getBarcode(), "");
		assertEquals(disc.getDirector(), "");
		assertEquals(disc.getTitle(), "");
		assertEquals(disc.getFsk(), 0);
	}
	
	@Test
	public void testParameterizedConstructor() {
		
		Disc disc = new Disc(barcode,director,fsk,title);
		
		assertEquals(disc.getBarcode(), barcode);
		assertEquals(disc.getDirector(), director);
		assertEquals(disc.getTitle(), title);
		assertEquals(disc.getFsk(), fsk);
	}
	
	@Test
	public void testSetter(){
		
		Disc disc = new Disc();
		
		disc.setDirector(director);
		assertEquals(disc.getDirector(), director);
		
		disc.setFsk(fsk);
		assertEquals(disc.getFsk(), fsk);
	}
	
	@Test
	public void testToString(){
		
		Disc disc = new Disc(barcode,director,fsk,title);
		
		assertEquals(disc.toString(), "Disc{Medium{title='test'}, barcode='abcde', director='spielberg', fsk=5}");
	}
	
	@Test
	public void testHashCode(){
		
		Disc disc = new Disc(barcode,director,fsk,title);
		
		assertEquals(disc.hashCode(), 911093675);
	}
	
	@Test
	public void testEquals(){
		
		Disc disc = new Disc(barcode,director,fsk,title);
		Disc disc2 = new Disc(barcode,director+"1",fsk+1,title+"1");
		

		assertTrue(disc.equals(disc));
		
		assertFalse(disc.equals(new Integer(1)));
		
		assertFalse(disc.equals(disc2));
		
		disc2.setTitle(title);
		assertFalse(disc.equals(disc2));
		
		disc2.setFsk(fsk);
		assertFalse(disc.equals(disc2));
		
		disc2.setDirector(director);
		assertTrue(disc.equals(disc2));
		
		Disc disc3 = new Disc(barcode+"1", director, fsk, title);
		assertFalse(disc.equals(disc3));
	}

}
