package com.asmita.movieTicket.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;


@ContextConfiguration(locations = {"classpath:spring-context.xml"})
public class TheaterTest extends AbstractJUnit4SpringContextTests{
		
	@Autowired
	TheaterService theater;
	
	
	/*book ticket function will update "bookticket" data in the database.everytime user will book the ticket*/
	@Test 
	public void testbookticket(){
		boolean check =theater.bookTicket( "Regal_Jack","Star Wars", 80, "1500");		
		assertEquals(false,check);
		boolean check1 =theater.bookTicket( "Regal_Jack","Star Wars", 40, "1500");
		assertEquals(false,check1);
	}
	
	@Test
	public void Checkavailablity(){
		boolean check =theater.checkAvailTicket( "Regal_Jack","Star Wars", 40, "1500");		
		assertEquals(false,check);
	}
	
		
}
