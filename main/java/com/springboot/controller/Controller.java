package com.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.springboot.model.Employee;
import com.springboot.repository.Repositorydb;

@RestController
@RequestMapping("/submitvalues")
public class Controller {

	@Autowired
	private Repositorydb repository;

	@PostMapping
	public String insertIntoDynamoDB(@RequestBody Employee employee)
	{
		repository.placingindynamodb(employee);
		return "Inserted into the row";
	}

	@GetMapping
	public ResponseEntity<Employee> getOneStudentDetails(@RequestParam String employeeId, @RequestParam String employeelastName) {

		Employee employee = repository.fetchingemployeedetails(employeeId, employeelastName);
		return new ResponseEntity<Employee>(employee, HttpStatus.OK);	
	}

	@PutMapping
	public void updateEmployeeDetails(@RequestBody Employee employee) {
		repository.updateEmployeeDetails(employee);
	}

	
	
	
	@DeleteMapping(value = "{employeeId}/{employeelastName}")
	public void deleteStudentDetails(@PathVariable("employeeId") String employeeId,
			@PathVariable("employeelastName") String employeelastName) {
		Employee employee = new Employee();
		employee.setEmployeeid(employeeId);
		employee.setEmployeelastName(employeelastName);
		repository.deleteEmployeeDetails(employee);
	}
	
	
	
	
	
}