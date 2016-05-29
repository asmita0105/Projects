package com.asmita.movieTicket.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asmita.movieTicket.entity.ShowInfo;
import com.asmita.movieTicket.entity.impl.ShowInfoImpl;
import com.asmita.movieTicket.repository.impl.AuditoriumRepositoryimpl;
import com.asmita.movieTicket.repository.impl.ShowInfoRepositoryImpl;
import com.asmita.movieTicket.service.AuditoriumService;

/*This is auditorium service which has dependent on Show info  class to get 
 * information about show. with help of show info data it check ticket 
 * availabilty . and provide list of show time to theater service.
 * */
@Service
@Transactional
public class AuditoriumServiceImpl implements AuditoriumService{

	@Autowired
	ShowInfoRepositoryImpl showsRepo;
	
	@Autowired
	AuditoriumRepositoryimpl auditorium; 
	int availTicket;
	
	private void setAvailTicket(int audiId,String movieName,String time) {
		 int cap = auditorium.getAuditiroriumCapacity(audiId);
		 ShowInfo show =  showsRepo.getShowDetail(movieName, audiId,time);
		 int bookedTicket= show.getbookTicket();
		 availTicket = cap - bookedTicket; 
	}
	
	public int getAvailTicket() {
		return availTicket;
	}



	private ShowInfoImpl getShow(String movie, String time,int audiId){
		List<ShowInfoImpl>shows = auditorium.getCurrentMovie(audiId);
		if(shows == null){
			return null;
		}
		for(ShowInfoImpl show:shows){
			if(show.getTime().equals(time) && show.getMovieName().equals(movie)){
				return show;
			}
		}
		return null;
	}
	
	@Override
	public  boolean checkAvailability(String movie, String time, int numTicket,int audiId){
			setAvailTicket(audiId, movie,time);
			if(getAvailTicket() >= numTicket){
				return true;
			}
		return false;
	}


	@Override
	public boolean  bookTicket(String movie, int numTicket,String time, int audiId){
		//if()
		if(checkAvailability(movie,time,numTicket,audiId) == true){
			ShowInfoImpl show = getShow(movie,time,audiId);
			if(show != null){
				show.setbookTicket(numTicket);
				return true;
			}
		}
		return false;
	}

	@Override
	public ArrayList<String> getMovieTime(String movie,int audiId){
		ArrayList<String> times = new ArrayList<String>();
		List<ShowInfoImpl>shows = auditorium.getCurrentMovie(audiId);
		if(shows == null){
			return null;
		}
		for(ShowInfoImpl show:shows){
			if(show.getMovieName().equals(movie)){
				times.add(show.getTime());
			}
		}
		return times;
	}
	
	@Override
	public int getAdudiCapacity(int audiID){
		
		int cap = auditorium.getAuditiroriumCapacity(audiID);
		return cap;
	}

	
}
