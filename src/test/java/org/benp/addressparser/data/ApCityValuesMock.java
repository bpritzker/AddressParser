package org.benp.addressparser.data;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class ApCityValuesMock extends ApCityValues {
	
	
	@Override
	protected Map<String, ApCityValue> loadValues() {

		Map<String, ApCityValue> resultValues = new HashMap<>();
		String cityName = "BOSTON";
		ApCityValue tempCityValue = new ApCityValue(cityName, new HashSet<>(Arrays.asList("")));
		resultValues.put(cityName, tempCityValue);
		
		cityName = "SOUTH BOSTON";
		tempCityValue = new ApCityValue(cityName, new HashSet<>(Arrays.asList("")));
		resultValues.put(cityName, tempCityValue);
		
		
		cityName = "SPRINGFIELD";
		tempCityValue = new ApCityValue(cityName, new HashSet<>(Arrays.asList("")));
		resultValues.put(cityName, tempCityValue);
		
		
		cityName = "WALTHAM";
		tempCityValue = new ApCityValue(cityName, new HashSet<>(Arrays.asList("")));
		resultValues.put(cityName, tempCityValue);
		
		cityName = "WASHINGTON";
		tempCityValue = new ApCityValue(cityName, new HashSet<>(Arrays.asList("")));
		resultValues.put(cityName, tempCityValue);
		
		cityName = "ARLINGTON";
		tempCityValue = new ApCityValue(cityName, new HashSet<>(Arrays.asList("")));
		resultValues.put(cityName, tempCityValue);
		
		cityName = "ALEXANDRIA";
		tempCityValue = new ApCityValue(cityName, new HashSet<>(Arrays.asList("")));
		resultValues.put(cityName, tempCityValue);
		return resultValues;		
	}

}
