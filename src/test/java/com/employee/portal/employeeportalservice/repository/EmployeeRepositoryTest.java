package com.employee.portal.employeeportalservice.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.employee.portal.employeeportalservice.config.RepositoryConfiguration;
import com.employee.portal.employeeportalservice.core.enums.Gender;
import com.employee.portal.employeeportalservice.entity.Employee;

/**
 * 
 * @author jogeswar.sahu
 *
 */
/**
 * The Class EmployeeRepositoryTest.
 */
@SpringBootTest(classes = { RepositoryConfiguration.class })
public class EmployeeRepositoryTest {

	@Autowired
	EmployeeRepository employeeRepository;

	@Test
	public void testSaveEmployee() throws ParseException {

		// save employee
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Employee employee = new Employee();
		String sDate = "1988-05-28";

		employee.setFirstName("Jogeswar");
		employee.setLastName("Sahu");
		employee.setGender(Gender.MALE);
		employee.setDateOfBirth(formatter.parse(sDate));
		employee.setDepartment("Product development");

		// 1. save employee, verify has ID value after save
		assertNull(employee.getId()); // null before save
		employeeRepository.save(employee);
		assertNotNull(employee.getId()); // not null after save

		// 2. fetch from DB
		Employee fetchedEmployee = employeeRepository.findById(employee.getId()).orElse(null);
		// should not be null
		assertNotNull(fetchedEmployee);
		// should equal
		assertEquals(employee.getId(), fetchedEmployee.getId());
		assertEquals(employee.getFirstName(), fetchedEmployee.getFirstName());

		// 3. update description and save
		fetchedEmployee.setFirstName("Yogi");
		employeeRepository.save(fetchedEmployee);

		// 4. get from DB, should be updated
		Employee fetchedUpdatedEmployee = employeeRepository.findById(fetchedEmployee.getId()).orElse(null);
		assertEquals(fetchedEmployee.getFirstName(), fetchedUpdatedEmployee.getFirstName());

		// 5. verify count of products in DB
		long productCount = employeeRepository.count();
		assertEquals(productCount, 1);

		// 6. get all products, list should only have one
		Iterable<Employee> employees = employeeRepository.findAll();
		int count = 0;
		for (Employee e : employees) {
			// should not be null
			assertNotNull(e);
			count++;
		}
		assertEquals(count, 1);
	}
}
