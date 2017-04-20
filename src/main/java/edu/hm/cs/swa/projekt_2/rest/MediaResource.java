package edu.hm.cs.swa.projekt_2.rest;

import edu.hm.cs.swa.projekt_2.datamodel.Book;
import edu.hm.cs.swa.projekt_2.datamodel.Disc;
import edu.hm.cs.swa.projekt_2.logic.MediaService;
import edu.hm.cs.swa.projekt_2.logic.MediaServiceImpl;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.logging.Logger;

@Path("media")
public class MediaResource {

    private Logger LOGGER = Logger.getLogger(MediaResource.class.getName());

    @POST
    @Path("books")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createBook(Book newBook) {

        LOGGER.info("rest request: add new book");

        MediaService service = new MediaServiceImpl();
        try {
            return Response.ok(service.addBook(newBook)).build();
        } catch (Exception ex) {
            // error handling for later multi user
        }
        return Response.serverError().build();
    }

    @GET
    @Path("books")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response getBooks() {
        LOGGER.info("rest request: get all books");

        MediaService service = new MediaServiceImpl();
        try {
            return Response.ok(service.getBooks()).build();
        } catch (Exception ex) {
            // error handling for later multi user
        }
        return Response.serverError().build();
    }

    @PUT
    @Path("books")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateBook(Book updatedBook) {
        LOGGER.info("rest request: update existing book");

        return null;
    }

    @POST
    @Path("discs")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createDisc(Disc newDisc) {
        LOGGER.info("rest request: add new book");

        MediaService service = new MediaServiceImpl();
        try {
            return Response.ok(service.addDisc(newDisc)).build();
        } catch (Exception ex) {
            // error handling for later multi user
        }
        return Response.serverError().build();
    }

    @GET
    @Path("discs")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response getDiscs() {
        LOGGER.info("rest request: get all discs");

        MediaService service = new MediaServiceImpl();
        try {
            return Response.ok(service.getDiscs()).build();
        } catch (Exception ex) {
            // error handling for later multi user
        }
        return Response.serverError().build();
    }

    @PUT
    @Path("discs")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateDiscs(Disc updatedDisc) {

        LOGGER.info("rest request: update existing book");

        return null;
    }
}
