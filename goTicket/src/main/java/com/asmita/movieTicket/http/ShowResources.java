package com.asmita.movieTicket.http;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.jboss.resteasy.annotations.providers.jaxb.Wrapped;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.asmita.movieTicket.http.entity.httpShow;
import com.asmita.movieTicket.service.TheaterService;
import com.asmita.movieTicket.service.exception.MovieTicketException;

@Path("/show")
@Component
@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
public class ShowResources {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	TheaterService theater;
	
	/*@Autowired
	MovieSearchService moviesearch; 
*/
	@POST
	@Path("/")
	public Response bookticket(httpShow httpshow){
		boolean resp =theater.bookTicket( httpshow.theaterName, httpshow.movieName, httpshow.noOfSeat, httpshow.time);
		
		if(resp == true){
			return Response.status(Status.CREATED).header("Location", "/ticket/"+httpshow+resp).entity(new httpShow(resp, httpshow.noOfSeat)).build();
		}
		return Response.status(Status.BAD_REQUEST).header("Location", "/ticket/"+httpshow+resp).entity(new httpShow(resp, httpshow.noOfSeat)).build();
	}
	
	@GET
	@Path("/")
	@Wrapped(element="show")
	public httpShow checkTicket(@QueryParam("movieName") String movieName,@QueryParam("theaterName") String theaterName,@QueryParam("noOfSeat") String seats,@QueryParam("showTime") String showTime)throws MovieTicketException{
		int noOfSeat = Integer.parseInt(seats);
		boolean result = theater.checkAvailTicket(theaterName, movieName, noOfSeat, showTime);
		
		if(result == true){
			logger.info("checkAvail"+result);
			return  new httpShow(result, noOfSeat);
		}
		logger.info("checkAvail"+result);
		return new httpShow(result, noOfSeat);
	}
	
}
