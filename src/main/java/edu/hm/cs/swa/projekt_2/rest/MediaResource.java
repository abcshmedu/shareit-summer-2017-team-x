package edu.hm.cs.swa.projekt_2.rest;

import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import edu.hm.cs.swa.projekt_2.datamodel.Book;
import edu.hm.cs.swa.projekt_2.logic.MediaService;
import edu.hm.cs.swa.projekt_2.logic.MediaServiceImpl;

@Path("media")
public class MediaResource {
	
	Logger LOGGER = Logger.getLogger(MediaResource.class.getName());

	@POST
	@Path("books")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createBook(Book newBook){
		
		LOGGER.info("rest request: add new book");
		
		return null;
	}
	
	@GET
	@Path("books")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getBooks(){
		
		LOGGER.info("rest request: get all books");
		
		MediaService service = new MediaServiceImpl();
		
		try{
			
			return Response.ok(service.getBooks()).build();
			
		}catch(Exception ex){
			// error handling for later multi user
		}
		
		return Response.serverError().build();
	}
	
	@PUT
	@Path("books")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateBook(Book updatedBook){
		
		LOGGER.info("rest request: update existing book");
		
		return null;
	}
}
