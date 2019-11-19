package com.employee.portal.employeeportalservice.core.bean;

import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author jogeswar.sahu
 *
 */
/**
 * The Class DataTablePagination.
 */
@Getter
@Setter
public class DataTablePagination {

	@ApiModelProperty(notes = "Current page number, DEFAULT value is 0")
	@ApiParam(required = true, defaultValue = "0")
	private int pageIndex;

	@ApiModelProperty(notes = "The search result size per page, DEFAULT value is 10")
	@ApiParam(required = true, defaultValue = "10")
	private int pageSize = 10;

	@ApiModelProperty(notes = "This contains the total number of records in Database", readOnly = true)
	@ApiParam(hidden = true, readOnly = true)
	private long totalElements;

	@ApiModelProperty(notes = "items contains the result values", readOnly = true)
	@ApiParam(hidden = true, readOnly = true)
	private List<Object> items = new ArrayList<>();

}
