package com.asmita.movieTicket.entity.impl;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.asmita.movieTicket.entity.Auditorium;
import com.asmita.movieTicket.entity.ShowInfo;
import com.asmita.movieTicket.entity.Theater;

@Entity
@Table(name="show_info")
public class ShowInfoImpl implements ShowInfo {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "idshow_info")
	private int showId;
	
	@Column(name="show_time")
	private String time;
	
	@Column(name="book_ticket")
	private int bookTicket;
	
	@Column(name="movie_Name")
	String movieName;
	
	@ManyToOne(fetch = FetchType.EAGER,targetEntity =AuditoriumImpl.class)
	@JoinColumn(name="idaudi")
	private Auditorium audi;
	
	@ManyToOne(fetch = FetchType.EAGER,targetEntity =TheaterImpl.class)
	@JoinColumn(name="theaterId")
	private Theater theaterId;
	

	@Override
	public int getShowId() {
		return showId;
	}
	
	@Override
	public String getTime() {
		return time;
	}

	@Override
	public int getbookTicket() {
		return bookTicket;
	}
		
	@Override
	public void setbookTicket(int numticket) {
		bookTicket += numticket;
		
	}
	@Override
	public String getMovieName() {
		
		return movieName;
	}

	@Override
	public Auditorium getAudiId() {
		return audi;
	}

	@Override
	public Theater getTheaterId() {
		return theaterId;
	}

	@Override
	public Auditorium getAudi() {
		return audi;
	}

	@Override
	public void setAudi(Auditorium audi) {
		this.audi = audi;
	}

	@Override
	public void setShowId(int showId) {
		this.showId = showId;
	}

	@Override
	public void setTime(String time) {
		this.time = time;
	}

	@Override
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	@Override
	public void setTheaterId(Theater theaterId) {
		this.theaterId = theaterId;
	}
	
	

}
