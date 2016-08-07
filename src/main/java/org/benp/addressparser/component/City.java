package org.benp.addressparser.component;

import org.apache.commons.lang3.text.WordUtils;
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
	public String getValueNormalized() {
		return cityName;
	}
	
	@Override
	public String getDefaultValue() {
		return WordUtils.capitalizeFully(cityName);
	}
	
	@Override
	public boolean equals(ComponentBase inComponent) {
		if (inComponent == null) {
			return false;
		}
		
		if (!inComponent.getClass().equals(this.getClass())) {
			return false;
		}
		
		City tempCity = (City) inComponent;
		if (tempCity.getCityName() == null && cityName == null) {
			return true;
		}
		
		if (tempCity.getCityName() == null || cityName == null) {
			return false;
		}
		 
		if (!tempCity.getCityName().equals(cityName)) {
			return false;
		}
		
		return true;
	}

}
