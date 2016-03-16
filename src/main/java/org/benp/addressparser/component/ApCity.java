package org.benp.addressparser.component;

import org.benp.addressparser.data.ApCityValue;

public class ApCity extends ApComponentBase {
	
	private ApCityValue cityValue;
	
	public ApCity() {
		super.setValid(false);
	}

	public ApCityValue getCityValue() {
		return cityValue;
	}

	public void setCityValue(ApCityValue cityValue) {
		this.cityValue = cityValue;
	}



}
