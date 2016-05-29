package com.asmita.movieTicket.repository;

import java.util.List;

import com.asmita.movieTicket.entity.Auditorium;
import com.asmita.movieTicket.entity.impl.AuditoriumImpl;


public interface TheaterRepository {
	
	public int  getAuditoriumId(String MovieName,String ShowTime,int tId);
	public List<AuditoriumImpl> getAudilist(String tname);
	public int getTheaterId(String tName);
	public String getTheaterName(int Tid);
	List<String> getTheaterList();

}
