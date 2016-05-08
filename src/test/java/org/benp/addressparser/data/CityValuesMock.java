package org.benp.addressparser.data;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class CityValuesMock extends CityValues {
	
	
	@Override
	protected Map<String, CityValue> loadValues() {

		Map<String, CityValue> resultValues = new HashMap<>();
		String cityName = "BOSTON";
		CityValue tempCityValue = new CityValue(cityName, new HashSet<>(Arrays.asList("")));
		resultValues.put(cityName, tempCityValue);
		
		cityName = "SOUTH BOSTON";
		tempCityValue = new CityValue(cityName, new HashSet<>(Arrays.asList("")));
		resultValues.put(cityName, tempCityValue);
		
		
		cityName = "SPRINGFIELD";
		tempCityValue = new CityValue(cityName, new HashSet<>(Arrays.asList("")));
		resultValues.put(cityName, tempCityValue);
		
		
		cityName = "WALTHAM";
		tempCityValue = new CityValue(cityName, new HashSet<>(Arrays.asList("")));
		resultValues.put(cityName, tempCityValue);
		
		cityName = "WASHINGTON";
		tempCityValue = new CityValue(cityName, new HashSet<>(Arrays.asList("")));
		resultValues.put(cityName, tempCityValue);
		
		cityName = "ARLINGTON";
		tempCityValue = new CityValue(cityName, new HashSet<>(Arrays.asList("")));
		resultValues.put(cityName, tempCityValue);
		
		cityName = "ALEXANDRIA";
		tempCityValue = new CityValue(cityName, new HashSet<>(Arrays.asList("")));
		resultValues.put(cityName, tempCityValue);
		return resultValues;		
	}

}
