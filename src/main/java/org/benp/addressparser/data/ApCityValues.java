package org.benp.addressparser.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.benp.addressparser.ApException;

import com.google.common.base.Splitter;

public class ApCityValues {
	
	
	
	private Map<String, ApCityValue> values = new HashMap<>();
	
	public void init() throws ApException {
		values = loadValues();
	}
	
	
//	public Set<ApCityValue> getValues() {
//		return values;
//	}
	
	public ApCityValue fromName(String cityName) {
		if (cityName == null) {
			return null;
		}
		
		return values.get(cityName);
		
//		for (ApCityValue currValue : values) {
//			if (currValue.getName().equals(inValue)) {
//				return currValue;
//			}
//		}
//		return null;
	}
	
	
	
	protected Map<String, ApCityValue> loadValues() throws ApException {

		Map<String, ApCityValue> resultCityNameToApCityValue = new HashMap<>();
		
		final File inputCityFile = new File(ApCityValues.class.getProtectionDomain().getCodeSource().getLocation().getPath().toString() 
				+ "org/benp/addressparser/data/AddressParser_UsCity_Default.csv");

		try (BufferedReader reader = new BufferedReader(new FileReader(inputCityFile))) {
			
			String currLine = reader.readLine();
			// First group all the cities with their states
			while (currLine != null) {
				
				List<String> splits = Splitter.on(",").splitToList(currLine);
				String cityName = splits.get(0);
				String stateName = splits.get(1);
				ApCityValue tempCityValue = resultCityNameToApCityValue.get(cityName);
				if (tempCityValue == null) {
					tempCityValue = new ApCityValue(cityName, new HashSet<String>());
//					stateCodes = new HashSet<>();
				}
				Set<String> stateCodes = tempCityValue.getStateCode();
				stateCodes.add(stateName);
				resultCityNameToApCityValue.put(cityName, tempCityValue);
//				resultCityNameToApCityValue.put(cityName, stateCodes);
//				ApCityValue tempValue = new ApCityValue(splits.get(0), splits.get(1));
//				resultValues.add(tempValue);
				currLine = reader.readLine();
			}
			
		} catch (FileNotFoundException fnfe) {
			throw new ApException("Could not find default Us City's file.", fnfe, 
					"inputCityFile", inputCityFile.getAbsolutePath());
		} catch (IOException ioe) {
			throw new ApException("Error reading default US Ciry's file.", ioe, 
					"inputCityFile", inputCityFile.getAbsolutePath());
		}
		
//		// now we have all the cities with their states, build the city values
//		Set<ApCityValue> resultValues = new HashSet<>();
//
//		for (Entry<String, Set<String>> cityStateToNameEntry: cityNameToStateCodes.entrySet()) {
//			
//			String cityName = cityStateToNameEntry.getKey();
//			Set<String> stateCodes = cityStateToNameEntry.getValue();
//			ApCityValue tempCityValue = new ApCityValue(cityName, stateCodes);
//			resultValues.add(tempCityValue);
//		}
		
		return resultCityNameToApCityValue;
	}
}
