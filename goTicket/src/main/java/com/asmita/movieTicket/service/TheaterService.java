package com.asmita.movieTicket.service;

import java.util.ArrayList;
import java.util.List;

import com.asmita.movieTicket.entity.Theater;


public interface TheaterService {
	
	public boolean  bookTicket(String tName,String movie, int numTicket,String time);
	public ArrayList<String> getShowTimes(String movie,int audiId);
	public List<Theater> getTheaterIdList(String movieName);
	public boolean checkAvailTicket(String tName,String movie, int numTicket,String time);
	
}
