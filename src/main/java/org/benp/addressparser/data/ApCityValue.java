package org.benp.addressparser.data;

public class ApCityValue {

	private final String name;
	private final String stateCode;
	

	public ApCityValue(String cityName, String stateCode) {
		this.name = cityName;
		this.stateCode = stateCode;
	}
	
	public String getStateCode() {
		return stateCode;
	}

	public String getName() {
		return name;
	}

	
}
