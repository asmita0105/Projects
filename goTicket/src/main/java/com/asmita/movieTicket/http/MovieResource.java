package com.asmita.movieTicket.http;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.annotations.providers.jaxb.Wrapped;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.asmita.movieTicket.http.entity.HttpMovie;
import com.asmita.movieTicket.service.MovieSearchService;
import com.asmita.movieTicket.service.exception.ErrorCode;
import com.asmita.movieTicket.service.exception.MovieTicketException;

@Path("/movie")
@Component
@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
public class MovieResource {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	MovieSearchService moviesearch; 
	
	@GET
	@Path("/")
	@Wrapped(element="movie")
	public List<HttpMovie> getmovie(@QueryParam("theaterName") String theaterName)throws MovieTicketException{
		
		List<String> movies = moviesearch.getMovie(theaterName);
		/*if(movies == null ||movies.size() ==0){
			throw new MovieTicketException(ErrorCode.MISSING_DATA, "no search parameter provided");
		}*/
		List<HttpMovie> found = new ArrayList<HttpMovie>();
		for(String movie:movies){
			found.add(new HttpMovie(movie,theaterName));
		}
		return found;
	} 
	
	@GET
	@Path("/movieList")
	@Wrapped(element="movie")
	public List<HttpMovie> getmovies()throws MovieTicketException{
		
		List<String> movies = moviesearch.getMovies();
		/*if(movies == null ||movies.size() ==0){
			throw new MovieTicketException(ErrorCode.MISSING_DATA, "no search parameter provided");
		}*/
		List<HttpMovie> found = new ArrayList<HttpMovie>();
		for(String movie:movies){
			found.add(new HttpMovie(movie));
		}
		return found;
	} 
	
	@GET
	@Path("/theater")
	@Wrapped(element="theater")
	public List<HttpMovie> getTheater(@QueryParam("movieName") String movieName) throws MovieTicketException{
		
		List<String> theaters = moviesearch.getThreater(movieName);
		/*if(theaters == null ||theaters.size() == 0){
			throw new MovieTicketException(ErrorCode.MISSING_DATA, "no search parameter provided");
		}*/
		List<HttpMovie> found = new ArrayList<HttpMovie>();
		for(String theater:theaters){
			found.add(new HttpMovie(theater,movieName));
		}
		return found;
	} 
	
	@GET
	@Path("/theaterList")
	@Wrapped(element="theater")
	public List<HttpMovie> getTheater() throws MovieTicketException{
		int tid= 1;
		List<String> theaters = moviesearch.getTheaterList();
		/*if(theaters == null ||theaters.size() == 0){
			throw new MovieTicketException(ErrorCode.MISSING_DATA, "no search parameter provided");
		}*/
		List<HttpMovie> found = new ArrayList<HttpMovie>();
		for(String theater:theaters){
			found.add(new HttpMovie(theater,tid));
		}
		return found;
	} 
	

}
