package com.employee.portal.employeeportalservice.core.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.employee.portal.employeeportalservice.core.enums.ErrorType;

import lombok.Setter;

import lombok.Getter;

/**
 * 
 * @author jogeswar.sahu
 *
 */
/**
 * The Class ErrorResponse.
 */
@Getter
@Setter
public class ErrorResponse {

	private Date timestamp;
	private String message;
	private ErrorType errorType;
	private List<Map<String, String>> errors;

	public ErrorResponse() {
		// Default implementation is ignored.
	}

	public ErrorResponse(ErrorType errorType, BindingResult result) {
		super();
		this.timestamp = new Date();
		this.message = "failure";
		this.errorType = errorType;

		this.setErrors(new ArrayList<>());

		if (result.hasErrors()) {

			List<FieldError> fieldErrors = result.getFieldErrors();
			Map<String, String> errorMap;

			for (FieldError fieldError : fieldErrors) {
				errorMap = new LinkedHashMap<>();
				errorMap.put("field", fieldError.getField());
				errorMap.put("message", fieldError.getCode());

				this.getErrors().add(errorMap);
			}
		}
	}

	public ErrorResponse(ErrorType errorType, List<Map<String, String>> errors) {
		super();
		this.timestamp = new Date();
		this.message = "failure";
		this.errorType = errorType;
		this.errors = errors;
	}

}
