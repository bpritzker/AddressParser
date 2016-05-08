package org.benp.addressparser.component;

import org.benp.addressparser.ErrorCode;
import org.benp.addressparser.component.street.Street;

public class Address extends ComponentMultiPart {
	
	private String origString;


	private Street street;
	private City city;
	private State state;
	private ZipCode zipCode;
	
	private ErrorCode errorCode;
	
	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public Street getStreet() {
		return street;
	}

	public void setStreet(Street street) {
		this.street = street;
	}

	public ZipCode getZipCode() {
		return zipCode;
	}

	public void setZipCode(ZipCode zipCode) {
		this.zipCode = zipCode;
	}
	
	public ErrorCode getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(ErrorCode errorCode) {
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
		StringBuilder resultSb = new StringBuilder();
		
		String joinString = "";
		
		if (street != null && street.isValid()) {
			resultSb.append(street.getValue());
			joinString = " ";
		}
		
		if (city != null && city.isValid()) {
			resultSb.append(joinString).append(city.getValue());
			joinString = " ";
		}
		
		if (state != null && state.isValid()) {
			resultSb.append(joinString).append(state.getValue());
			joinString = " ";
		}
		
//		private Street street;
//		private City city;
//		private State state;
//		private ZipCode zipCode;
		
		return resultSb.toString();
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
