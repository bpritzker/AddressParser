package org.benp.addressparser.component;

import org.benp.addressparser.data.mapping.MappingValue;

public class City extends ComponentBase {
	
	private MappingValue stateValues;
	private String cityName;
	
	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public City() {
		super.setValid(false);
	}

	public MappingValue getStateValues() {
		return stateValues;
	}

	public void setStateValues(MappingValue inStateValues) {
		stateValues = inStateValues;
	}

	@Override
	public String getValue() {
		return cityName;
	}

}
