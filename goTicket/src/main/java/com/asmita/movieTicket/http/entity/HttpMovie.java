package com.asmita.movieTicket.http.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "movie")
public class HttpMovie {
	
	@XmlElement
	String movie;
	
	int tid;
	
	@XmlElement
	String theaterName;

	public HttpMovie(){
	}
	
	public HttpMovie(String theaterName,int tid){
		this.theaterName = theaterName;
	}
	public HttpMovie(String movie){
		this.movie = movie;
		
	}
	
	public HttpMovie(String movie, String theaterName) {
		this.movie = movie;
		this.theaterName = theaterName;
	}
	
	

}
