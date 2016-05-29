package com.asmita.movieTicket.service.impl;



import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.asmita.movieTicket.entity.ShowInfo;
import com.asmita.movieTicket.entity.Theater;
import com.asmita.movieTicket.repository.impl.ShowInfoRepositoryImpl;
import com.asmita.movieTicket.repository.impl.TheaterRepositoryImpl;
import com.asmita.movieTicket.service.AuditoriumService;
import com.asmita.movieTicket.service.TheaterService;
import com.asmita.movieTicket.service.exception.InvalidFieldException;

/* This is theater service which has auditorium in it. This service will 
 * book ticket, and will give list of showTime of a movie.*/
//
@Service
@Transactional
public class TheaterServiceImpl implements TheaterService {
	
	@Autowired
	AuditoriumService auditorium;
	
	//@Autowired
	//TheaterImpl theater;
	
	@Autowired
	TheaterRepositoryImpl theaterRepo;
	
	@Autowired
	ShowInfoRepositoryImpl showInfo;

	@Override
	public boolean bookTicket(String tName,String movie, int numTicket, String time) {
		//theaterRepo = new TheaterRepositoryImpl();
		
		if(StringUtils.isEmpty(tName)){			
			throw new InvalidFieldException("Theater name is required");
		}
		
		if(StringUtils.isEmpty(movie)){			
			throw new InvalidFieldException("movie name is required");
		}
		if(numTicket == 0){			
			throw new InvalidFieldException("Number of ticket is required");
		}
		
		if(StringUtils.isEmpty(time)){			
			throw new InvalidFieldException("time is required");
		}
		int theaterID = theaterRepo.getTheaterId(tName);
		int audiId =theaterRepo.getAuditoriumId(movie, time, theaterID);
		
		return auditorium.bookTicket(movie, numTicket, time,audiId);
	}
	
	@Override
	public boolean checkAvailTicket(String tName,String movie, int numTicket,String time){
		if(StringUtils.isEmpty(tName)){			
			throw new InvalidFieldException("Theater name is required");
		}
		
		if(StringUtils.isEmpty(movie)){			
			throw new InvalidFieldException("movie name is required");
		}
		if(numTicket == 0){			
			throw new InvalidFieldException("Number of ticket is required");
		}
		
		if(StringUtils.isEmpty(time)){			
			throw new InvalidFieldException("time is required");
		}
		int theaterID = theaterRepo.getTheaterId(tName);
		int audiId =theaterRepo.getAuditoriumId(movie, time, theaterID);
		return auditorium.checkAvailability(movie, time, numTicket, audiId);
	}

	@Override
	public ArrayList<String> getShowTimes(String movie,int audiId) {
		
		ArrayList<String>times = auditorium.getMovieTime(movie,audiId);
		
		return times;
	}
	
	@Override
	public List<Theater> getTheaterIdList(String movieName){
		List<ShowInfo> shows =showInfo.getShowInfo(movieName);
		List<Theater> theaterIdList = new ArrayList<Theater>();
		for(ShowInfo show : shows){
			theaterIdList.add(show.getTheaterId());
		}
		return theaterIdList;
	}
	
	
	

}
