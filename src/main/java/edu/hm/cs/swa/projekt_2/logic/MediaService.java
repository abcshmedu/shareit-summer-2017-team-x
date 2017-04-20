package edu.hm.cs.swa.projekt_2.logic;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;

import edu.hm.cs.swa.projekt_2.datamodel.Book;
import edu.hm.cs.swa.projekt_2.datamodel.Disc;
import edu.hm.cs.swa.projekt_2.datamodel.Medium;

public interface MediaService {

	
	public MediaServiceResult addBook(Book book);
	public MediaServiceResult addDisc(Disc disc);
	
	public Medium[] getBooks();
	public Medium[] getDiscs();
	
	

	public MediaServiceResult updateBook(Book book);
	public MediaServiceResult updateDisc(Disc disc);
	
}
