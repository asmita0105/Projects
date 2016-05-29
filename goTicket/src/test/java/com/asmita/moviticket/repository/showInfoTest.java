package com.asmita.moviticket.repository;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import com.asmita.movieTicket.entity.ShowInfo;
import com.asmita.movieTicket.entity.Theater;
import com.asmita.movieTicket.entity.impl.AuditoriumImpl;
import com.asmita.movieTicket.entity.impl.ShowInfoImpl;
import com.asmita.movieTicket.entity.impl.TheaterImpl;
import com.asmita.movieTicket.repository.impl.ShowInfoRepositoryImpl;

@ContextConfiguration(locations = {"classpath:spring-context.xml"})
public class showInfoTest extends AbstractTransactionalJUnit4SpringContextTests{
	
	@Autowired
	ShowInfoRepositoryImpl showinforepo;
	
	
	@Autowired
	Theater th;
	
	@Test
	public void checkShowinfo(){
		ShowInfo shows = showinforepo.getMovie(1);
		
		assertEquals("1500",shows.getTime());
		
	}
	@Test
	public void checkShowInfolist(){
		List<ShowInfo>  shows= showinforepo.getShowInfo("Star Wars");
		Assert.assertNotNull(shows);

	}
	
	@Test
	public void createShowTest(){
		ShowInfoImpl show = new ShowInfoImpl();
		AuditoriumImpl audi = new AuditoriumImpl();
		TheaterImpl theater = new TheaterImpl();
		theater.SetTheaterId(2);
		show.setMovieName("The Godfather");
		show.setbookTicket(0);
		audi.setAudiId(2);
		show.setAudi(audi);
		show.setTheaterId(theater);
		show.setTime("1400");
		
		int newShow = showinforepo.addShow(show);
		Assert.assertNotEquals(0, newShow);
		
	}
	
	

}
