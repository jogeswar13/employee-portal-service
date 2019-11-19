package com.employee.portal.employeeportalservice.service.impl;

import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.employee.portal.employeeportalservice.bean.EmployeeBean;
import com.employee.portal.employeeportalservice.core.bean.DataTablePagination;
import com.employee.portal.employeeportalservice.entity.Employee;
import com.employee.portal.employeeportalservice.exception.InternalServerError;
import com.employee.portal.employeeportalservice.repository.EmployeeRepository;
import com.employee.portal.employeeportalservice.service.EmployeeService;
import com.employee.portal.employeeportalservice.util.CommonUtil;

/**
 * 
 * @author jogeswar.sahu
 *
 */
/**
 * The Class EmployeeServiceImpl.
 */
@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@Autowired
	EmployeeRepository employeeRepository;

//	public void setEmployeeRepository(EmployeeRepository employeeRepository) {
//		this.employeeRepository = employeeRepository;
//	}

	@Autowired
	CommonUtil commonUtil;

	/**
	 * 
	 * @param dataTablePagination
	 * @param sort
	 * @return ResponseEntity<Object>
	 * @throws InternalServerError
	 */
	@Override
	public ResponseEntity<Object> getAllEmployees(DataTablePagination dataTablePagination, Sort sort)
			throws InternalServerError {

		Pageable pageable = null;
		Page<Employee> pages = null;

		try {
			pageable = PageRequest.of(dataTablePagination.getPageIndex(), dataTablePagination.getPageSize(), sort);

			pages = employeeRepository.findAll(pageable);

			if (!pages.isEmpty()) {
				dataTablePagination.setTotalElements(pages.getTotalElements());

				dataTablePagination.setItems(pages.getContent().stream().map(mapper -> {
					EmployeeBean employeeBean = new EmployeeBean();
					employeeBean.setId(mapper.getId());
					employeeBean.setFirstName(mapper.getFirstName());
					employeeBean.setLastName(mapper.getLastName());
					employeeBean.setGender(mapper.getGender());
					employeeBean.setDateOfBirth(mapper.getDateOfBirth());
					employeeBean.setDepartment(mapper.getDepartment());
					return employeeBean;
				}).collect(Collectors.toList()));
			}
		} catch (Exception e) {
			logger.error("", e);
			throw new InternalServerError(e);
		}

		return new ResponseEntity<>(dataTablePagination, HttpStatus.OK);
	}

	/**
	 * 
	 * @param id
	 * @return ResponseEntity<Object>
	 * @throws InternalServerError
	 */
	@Override
	public ResponseEntity<Object> getEmployeeById(Long id) throws InternalServerError {

		EmployeeBean employeeBean = null;
		Employee employee = null;

		try {
			Optional<Employee> employeeOpt = employeeRepository.findById(id);

			if (employeeOpt.isPresent()) {
				employeeBean = new EmployeeBean();
				employee = employeeOpt.get();
				employeeBean.setId(employee.getId());
				employeeBean.setFirstName(employee.getFirstName());
				employeeBean.setLastName(employee.getLastName());
				employeeBean.setGender(employee.getGender());
				employeeBean.setDateOfBirth(employee.getDateOfBirth());
				employeeBean.setDepartment(employee.getDepartment());
			}
		} catch (Exception e) {
			logger.error("", e);
			throw new InternalServerError(e);
		}

		return new ResponseEntity<>(employeeBean, HttpStatus.OK);
	}

	/**
	 * 
	 * @param employeeBean
	 * @return ResponseEntity<Object>
	 * @throws InternalServerError
	 */
	@Override
	public ResponseEntity<Object> createEmployee(EmployeeBean employeeBean) throws InternalServerError {

		Employee employee = new Employee();

		try {
			saveOrUpdate(employee, employeeBean);

			employeeBean.setId(employee.getId());

			return new ResponseEntity<>(commonUtil.getSuccessMessage(employeeBean), HttpStatus.OK);

		} catch (Exception e) {
			logger.error("", e);
			throw new InternalServerError(e);
		}
	}

	/**
	 * 
	 * @param employeeBean
	 * @return ResponseEntity<Object>
	 * @throws InternalServerError
	 */
	@Override
	public ResponseEntity<Object> updateEmployee(EmployeeBean employeeBean) throws InternalServerError {

		Employee employee;

		try {
			employee = employeeRepository.findById(employeeBean.getId()).orElse(null);

			if (null != employee) {
				saveOrUpdate(employee, employeeBean);
			}
		} catch (Exception e) {
			logger.error("", e);
			throw new InternalServerError(e);
		}

		return new ResponseEntity<>(commonUtil.getSuccessMessage(employeeBean), HttpStatus.OK);
	}

	private void saveOrUpdate(Employee employee, EmployeeBean employeeBean) {

		employee.setFirstName(employeeBean.getFirstName());
		employee.setLastName(employeeBean.getLastName());
		employee.setGender(employeeBean.getGender());
		employee.setDateOfBirth(employeeBean.getDateOfBirth());
		employee.setDepartment(employeeBean.getDepartment());

		employeeRepository.save(employee);

	}

	/**
	 * 
	 * @param id
	 * @return ResponseEntity<Object>
	 * @throws InternalServerError
	 */
	@Override
	public ResponseEntity<Object> deleteEmployeeById(Long id) throws InternalServerError {

		try {
			employeeRepository.deleteById(id);
		} catch (Exception e) {
			logger.error("", e);
			throw new InternalServerError(e);
		}

		return new ResponseEntity<>(commonUtil.getSuccessMessage("Employee " + id + " is deleted successful."),
				HttpStatus.OK);
	}

}