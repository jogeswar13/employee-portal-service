package com.employee.portal.employeeportalservice.service;

import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;

import com.employee.portal.employeeportalservice.bean.EmployeeBean;
import com.employee.portal.employeeportalservice.core.bean.DataTablePagination;
import com.employee.portal.employeeportalservice.exception.InternalServerError;

/**
 * 
 * @author jogeswar.sahu
 *
 */
/**
 * The Class EmployeeService.
 */
public interface EmployeeService {

	/**
	 * 
	 * @param dataTablePagination
	 * @param sort
	 * @return ResponseEntity<Object>
	 * @throws InternalServerError
	 */
	public ResponseEntity<Object> getAllEmployees(DataTablePagination dataTablePagination, Sort sort)
			throws InternalServerError;

	/**
	 * 
	 * @param id
	 * @return ResponseEntity<Object>
	 * @throws InternalServerError
	 */
	public ResponseEntity<Object> getEmployeeById(Long id) throws InternalServerError;

	/**
	 * 
	 * @param employeeBean
	 * @return ResponseEntity<Object>
	 * @throws InternalServerError
	 */
	public ResponseEntity<Object> createEmployee(EmployeeBean employeeBean) throws InternalServerError;

	/**
	 * 
	 * @param employeeBean
	 * @return ResponseEntity<Object>
	 * @throws InternalServerError
	 */
	public ResponseEntity<Object> updateEmployee(EmployeeBean employeeBean) throws InternalServerError;

	/**
	 * 
	 * @param id
	 * @return ResponseEntity<Object>
	 * @throws InternalServerError
	 */
	public ResponseEntity<Object> deleteEmployeeById(Long id) throws InternalServerError;

}