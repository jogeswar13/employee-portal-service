package com.employee.portal.employeeportalservice.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.employee.portal.employeeportalservice.core.enums.Gender;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author jogeswar.sahu
 *
 */
/**
 * The Class Employee.
 */
@Getter
@Setter
@Entity
@Table(name = "SG_EMPLOYEES")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	/** The first name. */
	@Column(name = "first_name", nullable = false, length = 30)
	private String firstName;

	/** The last name. */
	@Column(name = "last_name", nullable = false, length = 30)
	private String lastName;

	/** The gender. */
	@Column(name = "gender", nullable = false)
	@Enumerated(EnumType.STRING)
	private Gender gender;

	/** The date of birth. */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_of_birth", nullable = false)
	private Date dateOfBirth;

	/** The department. */
	@Column(name = "department", nullable = false, length = 30)
	private String department;

}