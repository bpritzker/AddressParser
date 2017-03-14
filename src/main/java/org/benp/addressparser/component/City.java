package org.benp.addressparser.component;

import org.apache.commons.lang3.text.WordUtils;
import org.benp.addressparser.data.normalize.MappingValue;

public class City extends ComponentBase {
	
	private MappingValue stateValues;
	private String cityName;
	

	public City() {
		super.setValid(false);
	}


	@Override
	public String getValueNormalized() {
		return cityName.toUpperCase();
	}
	
	@Override
	public String getValueDefault() {
		return WordUtils.capitalizeFully(cityName);
	}
	
	@Override
	public boolean equalsNormalized(ComponentBase inComponent) {
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
	
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///              BEGIN GETTERS AND SETTERS
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////	
	
	
	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	
	
	
	public MappingValue getStateValues() {
		return stateValues;
	}

	public void setStateValues(MappingValue inStateValues) {
		stateValues = inStateValues;
	}
	

}
