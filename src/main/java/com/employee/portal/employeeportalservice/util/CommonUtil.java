package com.employee.portal.employeeportalservice.util;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class CommonUtil {

	private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	private String strTimeStamp = "timestamp";
	private String strMessage = "message";

	public Map<String, Object> getSuccessMessage(Object object) {

		Map<String, Object> json = new LinkedHashMap<>();

		try {
			json.put(strTimeStamp, new Date());
			json.put(strMessage, "success");
			json.put("data", object);
		} catch (Exception e) {
			logger.error("", e);
		}
		return json;
	}

	public void setErrorMsg(List<Map<String, String>> errors, String field, String message) {
		Map<String, String> map = new HashMap<>();
		map.put("field", field);
		map.put("message", message);

		errors.add(map);
	}

	// Function to check String for only Alphabets
	public static boolean isStringOnlyAlphabet(String str) {
		return ((str != null) && (!str.equals("")) && (str.matches("^[a-zA-Z\\s]*$")));
	}

}
