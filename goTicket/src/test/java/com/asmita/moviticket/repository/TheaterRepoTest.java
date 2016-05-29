package com.asmita.moviticket.repository;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import com.asmita.movieTicket.entity.impl.AuditoriumImpl;
import com.asmita.movieTicket.repository.TheaterRepository;

@ContextConfiguration(locations = {"classpath:spring-context.xml"})
public class TheaterRepoTest extends AbstractTransactionalJUnit4SpringContextTests{
	
	@Autowired
	TheaterRepository trepo;

	@Test
	public void theaterTest(){
		int check = trepo.getTheaterId("rand_Lake");
		assertEquals(1,check);
	}
	
	@Test
	public void getAudiTest(){
		List<AuditoriumImpl> audis =trepo.getAudilist("rand_Lake");
		assertNotEquals(0, audis);
	}

}
