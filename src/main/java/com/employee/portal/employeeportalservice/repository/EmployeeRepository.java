package com.employee.portal.employeeportalservice.repository;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import com.employee.portal.employeeportalservice.entity.Employee;

/**
 * 
 * @author jogeswar.sahu
 *
 */
/**
 * The Class EmployeeRepository.
 */
@Repository
public interface EmployeeRepository extends JpaRepositoryImplementation<Employee, Long> {

}
