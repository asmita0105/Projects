package com.asmita.movieTicket.entity;

import java.util.List;

import com.asmita.movieTicket.entity.impl.ShowInfoImpl;

public interface Auditorium {
	
	//public List<ShowInfoImpl> getCurrentMoviesSchedule(String movie);
	public int getAudiId();
	public int getCapacity();

}
