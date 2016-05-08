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

public class CityValues {
	
	
	
	private Map<String, CityValue> values = new HashMap<>();
	
	public void init() throws ApException {
		values = loadValues();
	}
	
	public CityValue fromName(String cityName) {
		if (cityName == null) {
			return null;
		}
		
		return values.get(cityName);
	}
	
	
	
	protected Map<String, CityValue> loadValues() throws ApException {

		Map<String, CityValue> resultCityNameToApCityValue = new HashMap<>();
		
		final File inputCityFile = new File(CityValues.class.getProtectionDomain().getCodeSource().getLocation().getPath().toString() 
				+ "org/benp/addressparser/data/AddressParser_UsCity_Default.csv");

		try (BufferedReader reader = new BufferedReader(new FileReader(inputCityFile))) {
			
			String currLine = reader.readLine();
			// First group all the cities with their states
			while (currLine != null) {
				
				List<String> splits = Splitter.on(",").splitToList(currLine);
				String cityName = splits.get(0);
				String stateName = splits.get(1);
				CityValue tempCityValue = resultCityNameToApCityValue.get(cityName);
				if (tempCityValue == null) {
					tempCityValue = new CityValue(cityName, new HashSet<String>());
				}
				Set<String> stateCodes = tempCityValue.getStateCode();
				stateCodes.add(stateName);
				resultCityNameToApCityValue.put(cityName, tempCityValue);
				currLine = reader.readLine();
			}
			
		} catch (FileNotFoundException fnfe) {
			throw new ApException("Could not find default Us City's file.", fnfe, 
					"inputCityFile", inputCityFile.getAbsolutePath());
		} catch (IOException ioe) {
			throw new ApException("Error reading default US Ciry's file.", ioe, 
					"inputCityFile", inputCityFile.getAbsolutePath());
		}
		
		return resultCityNameToApCityValue;
	}
}
