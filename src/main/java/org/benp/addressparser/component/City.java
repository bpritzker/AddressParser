package org.benp.addressparser.component;

import org.benp.addressparser.data.CityValue;

public class City extends ComponentBase {
	
	private CityValue cityValue;
	
	public City() {
		super.setValid(false);
	}

	public CityValue getCityValue() {
		return cityValue;
	}

	public void setCityValue(CityValue cityValue) {
		this.cityValue = cityValue;
	}

	@Override
	public String getValue() {
		if (cityValue == null) {
			return "";
		}
		return cityValue.getName();
	}

}
