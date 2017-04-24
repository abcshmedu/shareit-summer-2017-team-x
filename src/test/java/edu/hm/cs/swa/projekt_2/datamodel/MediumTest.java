package edu.hm.cs.swa.projekt_2.datamodel;

import static org.junit.Assert.*;

import org.junit.Test;

public class MediumTest {


	@Test
	public void testEquals(){
		
		Medium m1 = new Medium("xsa") {};
		Medium m2 = new Medium("asd") {};
		
		assertTrue(m1.equals(m1));
		
		assertFalse(m1.equals(m2));
		
		assertFalse(m1.equals(new Integer(1)));
	}
}
