package com.asmita.movieTicket.service.exception;


@SuppressWarnings("serial")
public class MovieTicketException extends RuntimeException{
	private ErrorCode errorCode;

	public MovieTicketException(ErrorCode code, String message, Throwable throwable) {
		super(message, throwable);
		this.errorCode = code;
	}
	
	public MovieTicketException(ErrorCode code, String message) {
		super(message);
		this.errorCode = code;
	}

	public ErrorCode getErrorCode() {
		return errorCode;
	}


}
