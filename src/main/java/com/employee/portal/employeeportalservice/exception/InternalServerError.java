package com.employee.portal.employeeportalservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 
 * @author jogeswar.sahu
 *
 */
/**
 * The Class InternalServerError.
 */
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalServerError extends Exception {

	private static final long serialVersionUID = 1L;

	static String erorrMsg = "Something went wrong. Try after sometime!";

	public InternalServerError() {
		super(erorrMsg);
	}

	public InternalServerError(String message) {
		super(message);
	}

	public InternalServerError(Throwable t) {
		super(erorrMsg, t);
	}

	public InternalServerError(String message, Throwable t) {
		super(message, t);
	}

}
