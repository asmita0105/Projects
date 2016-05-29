package com.asmita.movieTicket.entity;

public interface ShowInfo {
	//Movie movie;
	public String getTime();
	public int getbookTicket();
	public void setbookTicket(int numTicket);
	public int getShowId();
	public String getMovieName();
	public Auditorium getAudiId();
	public Theater getTheaterId();
	public Auditorium getAudi();
	public void setAudi(Auditorium audi);
	public void setShowId(int showId);
	public void setTime(String time);
	public void setMovieName(String movieName);
	public void setTheaterId(Theater theaterId);
	

}
