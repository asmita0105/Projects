package com.asmita.movieTicket.entity.impl;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.stereotype.Service;

import com.asmita.movieTicket.entity.Theater;

@Service
@Entity
@Table(name="Theater")
public class TheaterImpl implements Theater{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name ="theaterId")
	int theaterId;
	
	@Column(name ="Theater_Name")
	String theaterName;
	
	@OneToMany(mappedBy = "theater", targetEntity=AuditoriumImpl.class, cascade=CascadeType.ALL)	
    private List<AuditoriumImpl> audtList;
	
	@OneToMany(mappedBy = "theaterId", targetEntity=ShowInfoImpl.class, cascade=CascadeType.ALL)	
    private List<ShowInfoImpl> showInfoList;
	
	@Override
	public List<AuditoriumImpl> getAudtList() {
		return audtList;
	}
	
	@Override
	public List<ShowInfoImpl> getShowInfoList() {
		return showInfoList;
	}
	@Override
	public int getTheaterId() {
		return theaterId;
	}
	@Override
	public String getTheaterName() {
		return theaterName;
		
	}
	
	@Override
	public void SetTheaterId(int theaterid){
		this.theaterId = theaterid;
	}
	
}
