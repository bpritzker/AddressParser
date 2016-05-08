package org.benp.addressparser.data;

import java.util.Set;

public class CityValue {

	private final String name;
	private final Set<String> stateCode;
	

	public CityValue(String cityName, Set<String> stateCode) {
		this.name = cityName;
		this.stateCode = stateCode;
	}
	
	public Set<String> getStateCode() {
		return stateCode;
	}

	public String getName() {
		return name;
	}

	
}
