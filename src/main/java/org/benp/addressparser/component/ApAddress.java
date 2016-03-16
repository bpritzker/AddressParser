package org.benp.addressparser.component;

import org.benp.addressparser.ApErrorCode;

public class ApAddress extends ApComponentBase {
	
	
	
	private ApStreet street;
	private ApCity city;
	private ApState state;
	private ApZipCode zipCode;
	
	private ApErrorCode errorCode;
	
	
	public ApState getState() {
		return state;
	}

	public void setState(ApState state) {
		this.state = state;
	}

	public ApCity getCity() {
		return city;
	}

	public void setCity(ApCity city) {
		this.city = city;
	}

	public ApStreet getStreet() {
		return street;
	}

	public void setStreet(ApStreet street) {
		this.street = street;
	}

	public ApZipCode getZipCode() {
		return zipCode;
	}

	public void setZipCode(ApZipCode zipCode) {
		this.zipCode = zipCode;
	}
	
	public ApErrorCode getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(ApErrorCode errorCode) {
		this.errorCode = errorCode;
	}

}
