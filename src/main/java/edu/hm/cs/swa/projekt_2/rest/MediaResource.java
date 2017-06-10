package edu.hm.cs.swa.projekt_2.rest;

import com.google.inject.Guice;
import com.google.inject.Injector;
import edu.hm.Configuration;
import edu.hm.cs.swa.projekt_2.datamodel.Book;
import edu.hm.cs.swa.projekt_2.datamodel.Disc;
import edu.hm.cs.swa.projekt_2.datamodel.Medium;
import edu.hm.cs.swa.projekt_2.logic.AuthorizationIDEnum;
import edu.hm.cs.swa.projekt_2.logic.MediaService;
import edu.hm.cs.swa.projekt_2.logic.MediaServiceImpl;
import edu.hm.cs.swa.projekt_2.logic.MediaServiceResult;
import edu.hm.cs.swa.projekt_2.logic.ValidationResult;
import edu.hm.cs.swa.projekt_2.logic.ValidationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * This represents the REST API as described in the readme file.
 * Please have a look at this file for detailed information about the methods
 * and how to use them.
 */
@Path("media")
public class MediaResource {

    private static final Logger LOGGER = LogManager.getLogger(MediaResource.class);


    private Injector injector = Guice.createInjector(new Configuration());

    @POST
    @Path("books")
    @Produces(MediaType.APPLICATION_JSON + "; charset=utf-8")
    public Response createBook(Book newBook, @QueryParam("token") String token) {
        LOGGER.info("rest request: add new book");

        ValidationResult valResult = getInjector().getInstance(ValidationService.class).validateToken(token, AuthorizationIDEnum.BOOK_WRITE);
        if (valResult != ValidationResult.AUTHORIZATION_OK) {
            return getResponse(MediaServiceResult.NO_AUTHORIZATION);
        }


        MediaService service = getInjector().getInstance(MediaService.class);

        MediaServiceResult result = service.addBook(newBook);
        return getResponse(result);

    }

    @GET
    @Path("books/{isbn}")
    @Produces(MediaType.APPLICATION_JSON + "; charset=utf-8")
    public Response getBook(@PathParam("isbn") String isbn, @QueryParam("token") String token) {

        LOGGER.info("rest request: get single book. isbn: " + isbn);

        ValidationResult valResult = getInjector().getInstance(ValidationService.class).validateToken(token, AuthorizationIDEnum.BOOK_READ);
        if (valResult != ValidationResult.AUTHORIZATION_OK) {
            return getResponse(MediaServiceResult.NO_AUTHORIZATION);
        }

        MediaService service = getInjector().getInstance(MediaService.class);
        Medium medium = service.getBook(isbn);
        if (medium != null)
            return Response.ok(medium).build();
        else
            return getResponse(MediaServiceResult.NOT_FOUND);
    }

    @GET
    @Path("books")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON + "; charset=utf-8")
    public Response getBooks(@QueryParam("token") String token) {
        LOGGER.info("rest request: get all books");

        ValidationResult valResult = getInjector().getInstance(ValidationService.class).validateToken(token, AuthorizationIDEnum.BOOK_READ);
        if (valResult != ValidationResult.AUTHORIZATION_OK) {
            return getResponse(MediaServiceResult.NO_AUTHORIZATION);
        }
        MediaService service = getInjector().getInstance(MediaService.class);
        Medium[] books = service.getBooks();
        if (books == null)
            return getResponse(MediaServiceResult.NO_AUTHORIZATION);

        return Response.ok(books).build();

    }

    @PUT
    @Path("books/{isbn}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON + "; charset=utf-8")
    public Response updateBook(@PathParam("isbn") String isbn, Book updatedBook, @QueryParam("token") String token) {
        LOGGER.info("rest request: update Book. isbn: " + isbn);

        ValidationResult valResult = getInjector().getInstance(ValidationService.class).validateToken(token, AuthorizationIDEnum.BOOK_WRITE);
        if (valResult != ValidationResult.AUTHORIZATION_OK) {
            return getResponse(MediaServiceResult.NO_AUTHORIZATION);
        }

        MediaService service = getInjector().getInstance(MediaService.class);

        MediaServiceResult result = service.updateBook(isbn, updatedBook);
        return getResponse(result);

    }

    @POST
    @Path("discs")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON + "; charset=utf-8")
    public Response createDisc(Disc newDisc, @QueryParam("token") String token) {
        LOGGER.info("rest request: add new book");

        ValidationResult valResult = getInjector().getInstance(ValidationService.class).validateToken(token, AuthorizationIDEnum.DISC_WRITE);
        if (valResult != ValidationResult.AUTHORIZATION_OK) {
            return getResponse(MediaServiceResult.NO_AUTHORIZATION);
        }

        MediaService service = getInjector().getInstance(MediaService.class);

        MediaServiceResult result = service.addDisc(newDisc);
        return getResponse(result);

    }

    @GET
    @Path("discs")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON + "; charset=utf-8")
    public Response getDiscs(@QueryParam("token") String token) {
        LOGGER.info("rest request: get all discs");


        ValidationResult valResult = getInjector().getInstance(ValidationService.class).validateToken(token, AuthorizationIDEnum.DISC_READ);
        if (valResult != ValidationResult.AUTHORIZATION_OK) {
            return getResponse(MediaServiceResult.NO_AUTHORIZATION);
        }

        MediaService service = getInjector().getInstance(MediaService.class);

        Medium[] discs = service.getDiscs();
        if (discs == null)
            return getResponse(MediaServiceResult.NO_AUTHORIZATION);

        return Response.ok(discs).build();

    }

    @GET
    @Path("discs/{barcode}")
    @Produces(MediaType.APPLICATION_JSON + "; charset=utf-8")
    public Response getDisc(@PathParam("barcode") String barcode, @QueryParam("token") String token) {
        LOGGER.info("rest request: get disc. barcode: " + barcode);

        ValidationResult valResult = getInjector().getInstance(ValidationService.class).validateToken(token, AuthorizationIDEnum.BOOK_READ);
        if (valResult != ValidationResult.AUTHORIZATION_OK) {
            return getResponse(MediaServiceResult.NO_AUTHORIZATION);
        }

        MediaService service = getInjector().getInstance(MediaService.class);
        Medium medium = service.getDisc(barcode);
        if (medium != null)
            return Response.ok(medium).build();
        else
            return getResponse(MediaServiceResult.NOT_FOUND);
    }

    @PUT
    @Path("discs/{barcode}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON + "; charset=utf-8")
    public Response updateDiscs(@PathParam("barcode") String barcode, Disc updatedDisc, @QueryParam("token") String token) {
        LOGGER.info("rest request: update disc. barcode: " + barcode);

        ValidationResult valResult = getInjector().getInstance(ValidationService.class).validateToken(token, AuthorizationIDEnum.DISC_WRITE);
        if (valResult != ValidationResult.AUTHORIZATION_OK) {
            return getResponse(MediaServiceResult.NO_AUTHORIZATION);
        }

        MediaService service = new MediaServiceImpl();
        if (!barcode.isEmpty()) {

            return Response.ok(service.updateDisc(barcode, updatedDisc)).build();

        }
        return Response.serverError().build();
    }

    private Injector getInjector() {
        return injector;
    }

    private Response getResponse(MediaServiceResult msr) {
        return Response.status(msr.getStatus()).entity(msr).build();
    }
}
