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
	

	
	@Override
	public String getValueNormalized() {
		return getValue(true);
	}
	
	@Override
	public String getValueDefault() {
		return getValue(false);
	}

	/**
	 * Local method to build the value string to be returned
	 * @param inIsNormalized - false is proper value
	 */
			
	private String getValue(boolean inIsNormalized) {
		StringBuilder resultSb = new StringBuilder();
		
		String joinString = "";
		
		if (street != null && street.isValid()) {
			if (inIsNormalized) {
				resultSb.append(street.getValueNormalized());
			} else {
				resultSb.append(street.getValueDefault());
			}
			joinString = " ";
		}
		
		// This does not change based on the isNormalized
		if (city != null && city.isValid()) {
			resultSb.append(joinString).append(city.getValueNormalized());
			joinString = " ";
		}
		
		// This does not change based on the isNormalized
		if (state != null && state.isValid()) {
			resultSb.append(joinString).append(state.getValueNormalized());
			joinString = " ";
		}
		
		
		// This does not change based on the isNormalized
		if (zipCode != null && zipCode.isValid()) {
			resultSb.append(joinString).append(zipCode.getValueNormalized());
			joinString = " ";
		}

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

	@Override
	public boolean equalsNormalized(ComponentBase inComponent) {
		if (! inComponent.getClass().equals(this.getClass())) {
			return false;
		}
		return true;
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////
	///                     shortcut methods
	//////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////
	
	public String getStreeAddressDefault() {
		if (street == null) {
			return null;
		}
		return street.getValueDefault();
	}
	
//	public String getStreeAddressOther() {
//		if (street == null) {
//			return null;
//		}
//		return street.get
//	}
	
	public String getCityDefault() {
		if (city == null) {
			return null;
		}
		return city.getValueDefault();
	}
	
	public String getStateDefault() {
		if (state == null) {
			return null;
		}
		return state.getValueDefault();
	}
	
	public String getZipDefault() {
		if (zipCode == null) {
			return null;
		}
		return zipCode.getValueDefault();
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///              BEGIN GETTERS AND SETTERS
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////	
	
	
	
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
	
	
	
	
	
	
	
	
	
	
	
	
	

}
