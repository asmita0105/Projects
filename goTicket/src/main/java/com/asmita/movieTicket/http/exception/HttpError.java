package com.asmita.movieTicket.http.exception;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.asmita.movieTicket.service.exception.MovieTicketException;


@XmlRootElement(name = "error")
public class HttpError {
	@XmlElement
	private int status;
	
	@XmlElement
	private String code;
	
	@XmlElement
	private String message;
	
	@XmlElement
	private String debug;

	protected HttpError(){}
	
	public HttpError(MovieTicketException ex) {
		status=409;
		code=ex.getErrorCode()==null?"":ex.getErrorCode().name();
		message=ex.getMessage();
		debug=ex.getCause()==null?"":"caused by"+ex.getCause().getMessage();		
	}

}
