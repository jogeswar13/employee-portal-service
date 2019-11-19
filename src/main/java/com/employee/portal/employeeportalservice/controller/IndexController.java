package com.employee.portal.employeeportalservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @author jogeswar.sahu
 *
 */
/**
 * The Class IndexController.
 */
@RestController
@RequestMapping("/")
public class IndexController {

	@GetMapping
	public String index() {
		return "use \"http://localhost:<port_no>/swagger-ui.html\" to know more details";
	}

}
