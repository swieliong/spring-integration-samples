package com.javainuse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.javainuse.exceptionhandling.ResourceNotFoundException;
import com.javainuse.model.Employee;
import com.javainuse.service.EmployeeService;
import com.javainuse.service.EmployeeServiceException;

@RestController
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	@RequestMapping(value = "/employee", method = RequestMethod.GET)
	public Employee getEmpl() throws EmployeeServiceException {

		Employee emp = employeeService.getEmployee();

		if (emp == null) {
			throw new ResourceNotFoundException("Employee not found");
		}
		return emp;
	}

	@RequestMapping(value = "/employee2", method = RequestMethod.GET)
	public Employee getEmp2() throws EmployeeServiceException {

		Employee emp = employeeService.getEmployeeNull();
		if (emp == null) {
			throw new ResourceNotFoundException("Employee not found");
		}

		return emp;
	}

	@RequestMapping(value = "/employee3", method = RequestMethod.GET)
	public Employee getEmp3() throws ResourceNotFoundException, EmployeeServiceException {
		try {
			Employee emp = employeeService.getEmployeeException();
			if (emp == null) {
				throw new ResourceNotFoundException("Employee not found");
			}
			return emp;
		} catch (EmployeeServiceException e) {
			throw new EmployeeServiceException("Internal Server Exception while getting exception");
		}
	}
}
