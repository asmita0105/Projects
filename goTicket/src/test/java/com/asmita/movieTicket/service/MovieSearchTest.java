package com.asmita.movieTicket.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration(locations = {"classpath:spring-context.xml"})
public class MovieSearchTest extends AbstractJUnit4SpringContextTests {
	
	@Autowired
	MovieSearchService moviesearch; 
	
	@Test
	public void searchMovieTest(){
		List<String> movies = moviesearch.getMovie("rand_Lake");
		assertNotNull(movies);
	}
	
	@Test
	public void searchTheaterTest(){
		List<String> theater =moviesearch.getThreater("Martian");
		assertNotNull(theater);
	}
	
	@Test
	public void getMoviesList(){
		List<String>movies =moviesearch.getMovies();
		assertNotNull(movies);
	}
	
	@Test
	public void getTheaterList(){
		List<String>movies =moviesearch.getTheaterList();
		assertNotNull(movies);
	}

}
