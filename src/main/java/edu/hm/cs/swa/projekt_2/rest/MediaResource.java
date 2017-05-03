package edu.hm.cs.swa.projekt_2.rest;

import edu.hm.cs.swa.projekt_2.datamodel.Book;
import edu.hm.cs.swa.projekt_2.datamodel.Disc;
import edu.hm.cs.swa.projekt_2.datamodel.Medium;
import edu.hm.cs.swa.projekt_2.logic.MediaService;
import edu.hm.cs.swa.projekt_2.logic.MediaServiceImpl;
import edu.hm.cs.swa.projekt_2.logic.MediaServiceResult;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.logging.Logger;

/**
 * This represents the REST API as described in the readme file.
 * Please have a look at this file for detailed information about the methods
 * and how to use them.
 */
@Path("media")
public class MediaResource {

    private Logger LOGGER = Logger.getLogger(MediaResource.class.getName());

    @POST
    @Path("books")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON + "; charset=utf-8")
    public Response createBook(Book newBook) {

        LOGGER.info("rest request: add new book");

        MediaService service = new MediaServiceImpl();

        MediaServiceResult result = service.addBook(newBook);
        return Response.status(result.getStatus()).entity(result).build();

    }

    @GET
    @Path("books/{isbn}")
    @Produces(MediaType.APPLICATION_JSON + "; charset=utf-8")
    public Response getBook(@PathParam("isbn") String isbn) {

        MediaService service = new MediaServiceImpl();
        LOGGER.info("rest request: get single book. isbn: " + isbn);

        Medium medium = service.getBook(isbn);
        if (medium != null)
            return Response.ok(medium).build();
        else
            return Response.status(MediaServiceResult.NOT_FOUND.getStatus()).entity(MediaServiceResult.NOT_FOUND).build();
    }

    @GET
    @Path("books")
    @Produces(MediaType.APPLICATION_JSON + "; charset=utf-8")
    public Response getBooks() {
        MediaService service = new MediaServiceImpl();
        LOGGER.info("rest request: get all books");

        return Response.ok(service.getBooks()).build();

    }

    @PUT
    @Path("books/{isbn}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON + "; charset=utf-8")
    public Response updateBook(@PathParam("isbn") String isbn, Book updatedBook) {

        MediaService service = new MediaServiceImpl();
        LOGGER.info("rest request: update Book. isbn: " + isbn);

        MediaServiceResult result = service.updateBook(isbn, updatedBook);
        return Response.status(result.getStatus()).entity(result).build();

    }

    @POST
    @Path("discs")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON + "; charset=utf-8")
    public Response createDisc(Disc newDisc) {
        LOGGER.info("rest request: add new book");

        MediaService service = new MediaServiceImpl();

        MediaServiceResult result = service.addDisc(newDisc);
        return Response.status(result.getStatus()).entity(result).build();

    }

    @GET
    @Path("discs")
    @Produces(MediaType.APPLICATION_JSON + "; charset=utf-8")
    public Response getDiscs() {

        MediaService service = new MediaServiceImpl();
        LOGGER.info("rest request: get all discs");

        return Response.ok(service.getDiscs()).build();

    }

    @GET
    @Path("discs/{barcode}")
    @Produces(MediaType.APPLICATION_JSON + "; charset=utf-8")
    public Response getDisc(@PathParam("barcode") String barcode) {
        MediaService service = new MediaServiceImpl();
        Medium medium = service.getDisc(barcode);
        if (medium != null)
            return Response.ok(medium).build();
        else
            return Response.status(MediaServiceResult.NOT_FOUND.getStatus()).entity(MediaServiceResult.NOT_FOUND).build();
    }

    @PUT
    @Path("discs/{barcode}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON + "; charset=utf-8")
    public Response updateDiscs(@PathParam("barcode") String barcode, Disc updatedDisc) {
        MediaService service = new MediaServiceImpl();
        if (!barcode.isEmpty()) {
            LOGGER.info("rest request: update disc. barcode: " + barcode);

            return Response.ok(service.updateDisc(barcode, updatedDisc)).build();

        }
        return Response.serverError().build();
    }
}
