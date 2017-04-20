package edu.hm.cs.swa.projekt_2.logic;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import edu.hm.cs.swa.projekt_2.datamodel.Book;
import edu.hm.cs.swa.projekt_2.datamodel.Disc;
import edu.hm.cs.swa.projekt_2.datamodel.Medium;
import edu.hm.cs.swa.projekt_2.persistence.DataStore;



public class MediaServiceImpl implements MediaService {
	
	Logger LOGGER = Logger.getLogger(MediaServiceResult.class.getName());


	@Override
	public MediaServiceResult addBook(Book book) {
		
		System.out.println("neues buch");
		
		return MediaServiceResult.OK;
	}

	@Override
	public MediaServiceResult addDisc(Disc disc) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Medium[] getBooks() {
		

		
		return DataStore.INSTANCE.getBooks();
	}

	@Override
	public Medium[] getDiscs() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MediaServiceResult updateBook(Book book) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MediaServiceResult updateDisc(Disc disc) {
		// TODO Auto-generated method stub
		return null;
	}
}
