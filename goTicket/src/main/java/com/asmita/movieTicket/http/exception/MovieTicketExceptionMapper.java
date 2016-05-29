package com.asmita.movieTicket.http.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.springframework.stereotype.Component;

import com.asmita.movieTicket.service.exception.MovieTicketException;

@Provider
@Component
public class MovieTicketExceptionMapper implements ExceptionMapper<MovieTicketException> {

	@Override
	public Response toResponse(MovieTicketException exception) {
		
		return Response.status(Status.CONFLICT).entity(new HttpError(exception)).build();
	}

}
