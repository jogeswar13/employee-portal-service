package com.employee.portal.employeeportalservice.bootstrap;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.employee.portal.employeeportalservice.core.enums.Gender;
import com.employee.portal.employeeportalservice.entity.Employee;
import com.employee.portal.employeeportalservice.repository.EmployeeRepository;

/**
 * 
 * @author jogeswar.sahu
 *
 */
/**
 * The Class SpringJpaBootstrap.
 */
@Component
public class SpringJpaBootstrap implements ApplicationListener<ContextRefreshedEvent> {

	private EmployeeRepository employeeRepository;

	private Logger log = LogManager.getLogger(SpringJpaBootstrap.class);

	@Autowired
	public void setProductRepository(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		loadProducts();
	}

	private void loadProducts() {

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Employee employee = new Employee();
		String sDate = "1988-05-28";

		try {
			employee.setFirstName("Jogeswar");
			employee.setLastName("Sahu");
			employee.setGender(Gender.MALE);
			employee.setDateOfBirth(formatter.parse(sDate));
			employee.setDepartment("Product development");
			employeeRepository.save(employee);

			log.info("Saved Employee-1 - id: " + employee.getId());

		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

}
