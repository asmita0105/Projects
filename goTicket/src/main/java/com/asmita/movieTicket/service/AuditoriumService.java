package com.asmita.movieTicket.service;

import java.util.ArrayList;


public interface AuditoriumService {
	
    boolean checkAvailability(String movie, String time, int numTicket,int audiId);
    public boolean  bookTicket(String movie, int numTicket,String time,int audiId);
    public ArrayList<String> getMovieTime(String movie,int audId);
	int getAdudiCapacity(int audiID);
    
}
