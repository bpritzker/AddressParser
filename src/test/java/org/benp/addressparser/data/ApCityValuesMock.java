package org.benp.addressparser.data;

import java.util.HashSet;
import java.util.Set;

public class ApCityValuesMock extends ApCityValues {
	
	
	@Override
	protected Set<ApCityValue> loadValues() {

		Set<ApCityValue> resultValues = new HashSet<>();
		ApCityValue tempCityValue = new ApCityValue("BOSTON", "");
		resultValues.add(tempCityValue);
		tempCityValue = new ApCityValue("SOUTH BOSTON", "");
		resultValues.add(tempCityValue);
		tempCityValue = new ApCityValue("SPRINGFIELD", "");
		resultValues.add(tempCityValue);
		tempCityValue = new ApCityValue("WALTHAM", "");
		resultValues.add(tempCityValue);
		tempCityValue = new ApCityValue("WASHINGTON", "");
		resultValues.add(tempCityValue);
		tempCityValue = new ApCityValue("ARLINGTON", "");
		resultValues.add(tempCityValue);
		tempCityValue = new ApCityValue("ALEXANDRIA", "");
		resultValues.add(tempCityValue);
		return resultValues;		
	}

}
