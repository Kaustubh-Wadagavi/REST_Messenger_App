package org.kaustubh.RestProject.messenger.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.kaustubh.RestProject.messenger.model.ErrorMessage;

@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable>{

	@Override
	public Response toResponse(Throwable exception) {
		ErrorMessage errorMessage=new ErrorMessage(exception.getMessage(),500,"http://localhost:8080/messenger");
		return Response.status(Status.INTERNAL_SERVER_ERROR).entity(errorMessage).build();
	}
}