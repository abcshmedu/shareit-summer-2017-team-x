package edu.hm.cs.swa.projekt_2;

import com.fasterxml.jackson.databind.exc.PropertyBindingException;
import edu.hm.cs.swa.projekt_2.logic.MediaServiceResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class MappingErrorHandler implements ExceptionMapper<PropertyBindingException> {
    private static final Logger LOGGER = LogManager.getLogger(MappingErrorHandler.class);

    @Override
    public Response toResponse(PropertyBindingException exception) {
        LOGGER.info("Error while trying to bind Proprerty!", exception);
        return Response.status(MediaServiceResult.INVALID_MAPPING.getStatus()).entity(MediaServiceResult.INVALID_MAPPING).build();
    }
}
