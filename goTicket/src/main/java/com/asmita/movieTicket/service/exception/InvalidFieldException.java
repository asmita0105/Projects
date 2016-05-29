package com.asmita.movieTicket.service.exception;


@SuppressWarnings("serial")
public class InvalidFieldException extends MovieTicketException {
	

	public InvalidFieldException(String message, Throwable throwable) {
		super(ErrorCode.INVALID_FIELD, message, throwable);
	}
	
	public InvalidFieldException(String message) {
		super(ErrorCode.INVALID_FIELD, message);
	}
	

}
