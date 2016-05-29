package com.asmita.moviticket.repository;


import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import com.asmita.movieTicket.entity.Auditorium;
import com.asmita.movieTicket.entity.impl.ShowInfoImpl;
import com.asmita.movieTicket.repository.impl.AuditoriumRepositoryimpl;

@ContextConfiguration(locations = {"classpath:spring-context.xml"})
public class AuditoriumRepoTest extends AbstractTransactionalJUnit4SpringContextTests{
	
	@Autowired
	AuditoriumRepositoryimpl audi;
	
	@Test
	public void getAudiList(){
		List<Auditorium> audilist = audi.getAudiListTheater(2);
		
		Assert.assertNotNull(audilist);
	}

	@Test
	public void getMovieList(){
		List<ShowInfoImpl> movies = audi.getCurrentMovie(1);
		Assert.assertNotNull(movies);
	}
	
}
