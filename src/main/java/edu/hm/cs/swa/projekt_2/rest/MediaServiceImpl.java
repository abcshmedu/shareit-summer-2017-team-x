package edu.hm.cs.swa.projekt_2.rest;

import java.util.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import edu.hm.cs.swa.projekt_2.datamodel.Book;
import edu.hm.cs.swa.projekt_2.datamodel.Disc;
import edu.hm.cs.swa.projekt_2.datamodel.Medium;


@Path("media")
public class MediaServiceImpl implements MediaService {
	
	Logger LOGGER = Logger.getLogger(MediaServiceResult.class.getName());

	@Override
	public MediaServiceResult addBook(Book book) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MediaServiceResult addDisc(Disc disc) {
		// TODO Auto-generated method stub
		return null;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public Medium[] getBooks() {
		// TODO Auto-generated method stub
		LOGGER.info("i am here");
		System.out.println("asdasd");
		return null;
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