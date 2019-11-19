package com.employee.portal.employeeportalservice.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.employee.portal.employeeportalservice.bean.EmployeeBean;
import com.employee.portal.employeeportalservice.core.bean.DataTablePagination;
import com.employee.portal.employeeportalservice.core.bean.ErrorResponse;
import com.employee.portal.employeeportalservice.core.bean.PagingSortingErrorResponse;
import com.employee.portal.employeeportalservice.core.enums.ErrorType;
import com.employee.portal.employeeportalservice.exception.InternalServerError;
import com.employee.portal.employeeportalservice.exception.PaginationSortingException;
import com.employee.portal.employeeportalservice.service.EmployeeService;
import com.employee.portal.employeeportalservice.util.CommonUtil;
import com.employee.portal.employeeportalservice.validator.EmployeeValidator;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * 
 * @author jogeswar.sahu
 *
 */
/**
 * The Class EmployeeController.
 */
@Api(value = "employeeportal", description = "Operations pertaining to employees in Online Employee Portal")
@RestController
@RequestMapping("/portal")
public class EmployeeController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@Autowired
	EmployeeService service;

	@Autowired
	EmployeeValidator employeeValidator;

	@Autowired
	CommonUtil commonUtil;

	/**
	 * 
	 * @param dataTablePagination
	 * @param sort
	 * @return
	 * @throws InternalServerError
	 */
	@ApiOperation(value = "View a list of available employees", response = DataTablePagination.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 400, message = "Provide the proper request"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden") })
	@GetMapping("/v1/employees")
	public ResponseEntity<Object> getAllEmployees(@ModelAttribute DataTablePagination dataTablePagination,
			@RequestParam("orderBy") @ApiParam(required = true, allowableValues = "id,firstName,lastName,gender,dateOfBirth,department") String orderBy,
			@RequestParam("direction") @ApiParam(required = true, allowableValues = "ASC,DESC") String direction)
			throws InternalServerError {

		Sort sort = null;

		try {
			if (!(direction.toUpperCase().equals(Direction.ASC.name())
					|| direction.toUpperCase().equals(Direction.DESC.name()))) {
				throw new PaginationSortingException("Invalid sort direction");
			}

			sort = Sort.by(Direction.fromString(direction.toUpperCase()), orderBy);

			// Sort sort = Sort.by("firstName");
			return service.getAllEmployees(dataTablePagination, sort);
		} catch (Exception e) {
			logger.error("", e);
			throw new InternalServerError(e);
		}

	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws InternalServerError
	 */
	@ApiOperation(value = "Search a employee with an ID", response = ResponseEntity.class)
	@GetMapping("/v1/employees/{id}")
	public ResponseEntity<Object> getEmployeeById(@PathVariable("id") Long id) throws InternalServerError {

		List<Map<String, String>> errors = new ArrayList<>();
		EmployeeBean employee = new EmployeeBean();

		try {
			employee.setId(id);
			employeeValidator.employeeIdValidation(employee, null, errors);

			if (!errors.isEmpty()) {
				return new ResponseEntity<>(new ErrorResponse(ErrorType.VALIDATION, errors), HttpStatus.BAD_REQUEST);
			}

			return service.getEmployeeById(id);

		} catch (Exception e) {
			logger.error("", e);
			throw new InternalServerError(e);
		}

	}

	/**
	 * 
	 * @param employee
	 * @param result
	 * @return
	 * @throws InternalServerError
	 */
	@ApiOperation(value = "Add an employee")
	@PostMapping("/v1/employees")
	public ResponseEntity<Object> createEmployee(@RequestBody EmployeeBean employee, BindingResult result)
			throws InternalServerError {

		try {
			employeeValidator.validate(employee, result);

			if (result.hasErrors()) {
				return new ResponseEntity<>(new ErrorResponse(ErrorType.VALIDATION, result), HttpStatus.BAD_REQUEST);
			}

			return service.createEmployee(employee);

		} catch (Exception e) {
			logger.error("", e);
			throw new InternalServerError(e);
		}
	}

	/**
	 * 
	 * @param employee
	 * @param result
	 * @return
	 * @throws InternalServerError
	 */
	@ApiOperation(value = "Update an employee")
	@PutMapping("/v1/employees")
	public ResponseEntity<Object> updateEmployee(@RequestBody EmployeeBean employee, BindingResult result)
			throws InternalServerError {

		try {
			employeeValidator.updateEmployeeValidation(employee, result);

			if (result.hasErrors()) {
				return new ResponseEntity<>(new ErrorResponse(ErrorType.VALIDATION, result), HttpStatus.BAD_REQUEST);
			}

			return service.updateEmployee(employee);

		} catch (Exception e) {
			logger.error("", e);
			throw new InternalServerError(e);
		}
	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws InternalServerError
	 */
	@ApiOperation(value = "Delete an employee")
	@DeleteMapping("/v1/employees/{id}")
	public ResponseEntity<Object> deleteEmployeeById(@PathVariable("id") Long id) throws InternalServerError {

		List<Map<String, String>> errors = new ArrayList<>();
		EmployeeBean employee = new EmployeeBean();

		try {
			employee.setId(id);
			employeeValidator.employeeIdValidation(employee, null, errors);

			if (!errors.isEmpty()) {
				return new ResponseEntity<>(new ErrorResponse(ErrorType.VALIDATION, errors), HttpStatus.BAD_REQUEST);
			}

			return service.deleteEmployeeById(id);

		} catch (Exception e) {
			logger.error("", e);
			throw new InternalServerError(e);
		}
	}

	@ExceptionHandler(PaginationSortingException.class)
	public ResponseEntity<PagingSortingErrorResponse> exceptionHandler(Exception ex) {
		PagingSortingErrorResponse pagingSortingErrorResponse = new PagingSortingErrorResponse();
		pagingSortingErrorResponse.setErrorCode(HttpStatus.BAD_REQUEST.value());
		pagingSortingErrorResponse.setMessage(ex.getMessage());
		return new ResponseEntity<>(pagingSortingErrorResponse, HttpStatus.BAD_REQUEST);
	}

}