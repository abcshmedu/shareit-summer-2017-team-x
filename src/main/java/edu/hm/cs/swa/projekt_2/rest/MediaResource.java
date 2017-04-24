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
import javax.ws.rs.PathParam;
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

            /*
             Möglicher Fehler: Ungültige ISBN
             Möglicher Fehler: ISBN bereits vorhanden
             Möglicher Fehler: Autor oder Titel fehlt
             */
        }
        return Response.serverError().build();
    }

    @GET
    @Path("books/{isbn}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response getBook(@PathParam("isbn") String isbn) {

        MediaService service = new MediaServiceImpl();
        if (!isbn.isEmpty()) {
            LOGGER.info("rest request: get single book. isbn: " + isbn);

            try {
                return Response.ok(service.getBook(isbn)).build();
            } catch (Exception exc) {
                // error handling for later multi user
            }
        }
        return Response.serverError().build();
    }

    @GET
    @Path("books")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response getBooks() {
        MediaService service = new MediaServiceImpl();
        LOGGER.info("rest request: get all books");

        try {
            return Response.ok(service.getBooks()).build();
        } catch (Exception ex) {
            // error handling for later multi user
        }
        return Response.serverError().build();
    }

    @PUT
    @Path("books/{isbn}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateBook(@PathParam("isbn") String isbn, Book updatedBook) {

        MediaService service = new MediaServiceImpl();
        LOGGER.info("rest request: update Book. isbn: " + isbn);

        try {
            return Response.ok(service.updateBook(isbn, updatedBook)).build();
        } catch (Exception ex) {
            // error handling for later multi user
        }
        return Response.serverError().build();
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

        MediaService service = new MediaServiceImpl();
        LOGGER.info("rest request: get all discs");

        try {
            return Response.ok(service.getDiscs()).build();
        } catch (Exception ex) {
            // error handling for later multi user
        }
        return Response.serverError().build();
    }

    @GET
    @Path("discs/{barcode}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response getDisc(@PathParam("barcode") String barcode) {
        MediaService service = new MediaServiceImpl();
        if (!barcode.isEmpty()) {
            LOGGER.info("rest request: get single disc. barcode: " + barcode);

            try {
                return Response.ok(service.getDisc(barcode)).build();
            } catch (Exception exc) {
                // error handling for later multi user
                LOGGER.info("failure: " + exc.getLocalizedMessage());
            }
        }
        return Response.serverError().build();
    }

    @PUT
    @Path("discs/{barcode}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateDiscs(@PathParam("barcode") String barcode, Disc updatedDisc) {
        MediaService service = new MediaServiceImpl();
        if (!barcode.isEmpty()) {
            LOGGER.info("rest request: update disc. barcode: " + barcode);

            try {
                return Response.ok(service.updateDisc(barcode, updatedDisc)).build();
            } catch (Exception exc) {
                // error handling for later multi user
                LOGGER.info("failure: " + exc.getLocalizedMessage());
            }
        }
        return Response.serverError().build();
    }
}
