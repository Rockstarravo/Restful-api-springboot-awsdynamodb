package com.springboot.repository;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
import com.amazonaws.services.dynamodbv2.model.ConditionalCheckFailedException;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.springboot.model.Employee;

@Repository
public class Repositorydb {

	private static final Logger LOGGER = LoggerFactory.getLogger(Repositorydb.class);

	@Autowired
	private DynamoDBMapper mapper;

	public void placingindynamodb(Employee employee) {
		mapper.save(employee);
	}

	public Employee fetchingemployeedetails(String employeeid, String employeelastName) {
		return mapper.load(Employee.class, employeeid, employeelastName);
	}

	public void updateEmployeeDetails(Employee employee) {
		try {
			mapper.save(employee, dynamoDBSaveExpression(employee));
		} catch (ConditionalCheckFailedException exception) {
			
			LOGGER.error("invalid data - " + exception.getMessage());
		}
	}

	public void deleteEmployeeDetails(Employee employee) {
		mapper.delete(employee);
	}

	public DynamoDBSaveExpression dynamoDBSaveExpression(Employee employee) {
		
		
		
		DynamoDBSaveExpression saveExpression = new DynamoDBSaveExpression();
		
		
		Map<String, ExpectedAttributeValue> expected = new HashMap<>();

		
		
		expected.put("employeeId", new ExpectedAttributeValue(new AttributeValue(employee.getEmployeeid()))
				.withComparisonOperator(ComparisonOperator.EQ));
		
		
		saveExpression.setExpected(expected);
		
		
		return saveExpression;
	}
}