package org.benp.addressparser.component;

import org.benp.addressparser.ApErrorCode;

public class ApAddress extends ApComponentMultiPart {
	
	private String origString;


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
	
	public String getOrigString() {
		return origString;
	}

	public void setOrigString(String origString) {
		this.origString = origString;
	}

	@Override
	public String getValue() {
		// TODO: need to implement and test this
		return null;
//		StringBuilder resultSb = new StringBuilder();
//
//		private ApStreet street;
//		private ApCity city;
//		private ApState state;
//		private ApZipCode zipCode;
		
	}

	/**
	 * Complete means we have a complete street and valid city, state and zip
	 */
	@Override
	public boolean isComplete() {

		if (street.isComplete() && city.isValid() && state.isValid() && zipCode.isValid()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * If any part if valid (or complete) then this is a partial address.
	 */
	@Override
	public boolean isPartial() {

		if (street.isComplete() || city.isValid() || state.isValid() || zipCode.isValid()) {
			return true;
		} else {
			return false;
		}
	}

}
