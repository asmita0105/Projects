package com.asmita.movieTicket.repository;

import java.util.List;

import com.asmita.movieTicket.entity.ShowInfo;

public interface ShowInforepository {
	public ShowInfo getMovie(int showId);
	public ShowInfo getShowDetail(String movieName,int audiId,String time);
	public List<ShowInfo> getShowInfo(String movieName);
	public List<String> getMovieList(int Tid);
	public int addShow(ShowInfo show);
	public List<String> getMovieSList();

}
