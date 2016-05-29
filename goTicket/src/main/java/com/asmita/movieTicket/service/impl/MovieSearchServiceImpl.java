package com.asmita.movieTicket.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.asmita.movieTicket.entity.ShowInfo;
import com.asmita.movieTicket.entity.Theater;
import com.asmita.movieTicket.repository.ShowInforepository;
import com.asmita.movieTicket.repository.impl.TheaterRepositoryImpl;
import com.asmita.movieTicket.service.MovieSearchService;
import com.asmita.movieTicket.service.TheaterService;
import com.asmita.movieTicket.service.exception.ErrorCode;
import com.asmita.movieTicket.service.exception.MovieTicketException;


/*This is search service class. it provides movie search  by theaterName and Theater search by movie name*/
@Service
@Transactional
public class MovieSearchServiceImpl implements MovieSearchService{
	
	@Autowired
	TheaterService theater;
	@Autowired
	TheaterRepositoryImpl tRepo; // = new TheaterRepositoryImpl();
	@Autowired
	ShowInforepository showInfo;

	@Override
	public List<String> getThreater(String movie) {
		if(StringUtils.isEmpty(movie) ){
			throw new MovieTicketException(ErrorCode.MISSING_DATA, "no search parameter provided");	
		}
		List<Theater>theaters = theater.getTheaterIdList(movie);
		List<String> theaterName = new ArrayList<String>();
		for(Theater theater:theaters){
			theaterName.add(tRepo.getTheaterName(theater.getTheaterId()));
			
		}
		return theaterName;
	}
	@Override
	public List<ShowInfo> getShowDetail(String movie){
		List<ShowInfo> shows = showInfo.getShowInfo(movie);
		return shows;
	}

	@Override
	public List<String> getMovie(String TheaterName) {
		if(StringUtils.isEmpty(TheaterName) ){
			throw new MovieTicketException(ErrorCode.MISSING_DATA, "no search parameter provided");	
		}
		int tId = tRepo.getTheaterId(TheaterName);
		List<String> movies = showInfo.getMovieList(tId);
		
		return movies;
	}
	
	@Override
	public List<String> getMovies() {
		
		List<String>movies = showInfo.getMovieSList();
		
		return movies;
	}
	
	@Override
	public List<String> getTheaterList() {
		
		List<String>movies = tRepo.getTheaterList();
		
		return movies;
	}
	

}
