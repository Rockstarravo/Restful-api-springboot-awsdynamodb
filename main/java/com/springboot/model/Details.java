package com.springboot.model;

import java.io.Serializable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;

@DynamoDBDocument
public class Details implements Serializable {

	private static final long serialVersionUID = 1L;

	private String address;
	private String state;
	private String city;
	private String zipCode;

	@DynamoDBAttribute
	public String getAddressLine1() {
		return address;
	}

	public void setAddressLine1(String addressl) {
		this.address = addressl;
	}

	@DynamoDBAttribute
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@DynamoDBAttribute
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@DynamoDBAttribute
	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

}