package com.asmita.movieTicket.http.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "show")
public class httpShow {
	
	@XmlElement
	public String movieName;
	
	@XmlElement
	public String theaterName;
	
	@XmlElement
	public String time;
	
	@XmlElement
	public int noOfSeat;
	
	@XmlElement
	public boolean seatSatus;
	
	protected httpShow(){}
	
	public httpShow(boolean seatSatus, int numSeat){
		//this.seatSatus = seatSatus;
		if (seatSatus == true) {
			this.noOfSeat = numSeat;
		}
	}

	/*public httpShow(boolean seatFull){
		this.seatAvail = seatAvail;
	}*/
	
	public httpShow(String movieName, String theaterName, String time, String noOfSeat) {
		
		this.movieName = movieName;
		this.theaterName = theaterName;
		this.time = time;
		this.noOfSeat = Integer.parseInt(noOfSeat);
	}
	
	
	

}
