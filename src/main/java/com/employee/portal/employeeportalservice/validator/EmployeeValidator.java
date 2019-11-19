package com.employee.portal.employeeportalservice.validator;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.employee.portal.employeeportalservice.bean.EmployeeBean;
import com.employee.portal.employeeportalservice.entity.Employee;
import com.employee.portal.employeeportalservice.repository.EmployeeRepository;
import com.employee.portal.employeeportalservice.util.CommonUtil;
import com.employee.portal.employeeportalservice.util.Messages;

/**
 * 
 * @author jogeswar.sahu
 *
 */
/**
 * The Class EmployeeValidator.
 */
@Component
public class EmployeeValidator implements Validator {

	@Autowired
	EmployeeRepository repository;

	@Autowired
	CommonUtil commonUtil;

	@Autowired
	Messages messages;

	@Override
	public boolean supports(Class<?> clazz) {
		return EmployeeValidator.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		EmployeeBean employee = (EmployeeBean) target;

		commonValidation(employee, errors);

	}

	public void updateEmployeeValidation(EmployeeBean employee, Errors errors) {

		commonValidation(employee, errors);

		employeeIdValidation(employee, errors, null);

	}

	private void commonValidation(EmployeeBean employee, Errors err) {

		ValidationUtils.rejectIfEmptyOrWhitespace(err, "firstName", messages.get("employee.first.name.empty"));
		ValidationUtils.rejectIfEmptyOrWhitespace(err, "dateOfBirth", messages.get("employee.dateofbirth.empty"));
		ValidationUtils.rejectIfEmptyOrWhitespace(err, "department", messages.get("employee.department.empty"));

		// regular expression for alphabet validation
		if (null != employee.getFirstName() && !CommonUtil.isStringOnlyAlphabet(employee.getFirstName())) {
			err.rejectValue("firstName", messages.get("employee.first.name.alphabet"));
		}
		if (null != employee.getLastName() && !CommonUtil.isStringOnlyAlphabet(employee.getLastName())) {
			err.rejectValue("lastName", messages.get("employee.last.name.alphabet"));
		}
		if (null != employee.getDepartment() && !CommonUtil.isStringOnlyAlphabet(employee.getDepartment())) {
			err.rejectValue("department", messages.get("employee.department.alphabet"));
		}

	}

	public void employeeIdValidation(EmployeeBean employee, Errors err, List<Map<String, String>> errors) {

		if (null != employee.getId()) {
			Optional<Employee> employeeOpt = repository.findById(employee.getId());

			if (!employeeOpt.isPresent()) {
				if (null != err) {
					err.rejectValue("id", messages.get("employee.id.not.exist"));
				} else {
					commonUtil.setErrorMsg(errors, "id", messages.get("employee.id.not.exist"));
				}
			}
		} else {
			if (null != err) {
				err.rejectValue("id", messages.get("employee.id.empty"));
			} else {
				commonUtil.setErrorMsg(errors, "id", messages.get("employee.id.empty"));
			}
		}

	}

}
