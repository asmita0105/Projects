package com.asmita.movieTicket.service;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration(locations = {"classpath:spring-context.xml"})
public class AudiServiceTest extends AbstractJUnit4SpringContextTests{
	
	@Autowired
	AuditoriumService audi;
	
	@Test
	public void getCapacityTest(){
		int cap = audi.getAdudiCapacity(2);
		assertEquals(90,cap);
	}
	
	@Test
	public void getShowTime(){
		ArrayList<String> Time = audi.getMovieTime("Star Wars", 2);
		assertNotNull(Time);
	}

}
