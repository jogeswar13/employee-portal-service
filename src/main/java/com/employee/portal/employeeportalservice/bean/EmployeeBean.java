package com.employee.portal.employeeportalservice.bean;

import java.util.Date;

import com.employee.portal.employeeportalservice.core.enums.Gender;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author jogeswar.sahu
 *
 */
/**
 * The Class EmployeeBean.
 */
@Getter
@Setter
public class EmployeeBean {

	/** The id */
	@ApiModelProperty(notes = "The database generated employee ID", allowEmptyValue = false)
	private Long id;

	/** The first name. */
	@ApiModelProperty(notes = "The employee first name", allowEmptyValue = false)
	private String firstName;

	/** The last name. */
	@ApiModelProperty(notes = "The employee last name", allowEmptyValue = true)
	private String lastName;

	/** The gender. */
	@ApiModelProperty(notes = "The employee gender.", allowableValues = "MALE, FEMALE, OTHER", allowEmptyValue = false)
	@ApiParam(required = true, allowableValues = "MALE, FEMALE, OTHER")
	private Gender gender;

	/** The date of birth. */
	@ApiModelProperty(notes = "The employee date of birth. Format : yyyy-MM-dd", allowEmptyValue = false)
	@JsonFormat(pattern = "yyyy-MM-dd", shape = Shape.STRING)
	private Date dateOfBirth;

	/** The department. */
	@ApiModelProperty(notes = "The employee department", allowEmptyValue = false)
	private String department;

}