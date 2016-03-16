package org.benp.addressparser.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.benp.addressparser.ApException;

import com.google.common.base.Splitter;

public class ApCityValues {
	
	
	
	private static Set<ApCityValue> values = new HashSet<>();
	
	public void init() throws ApException {
		values = loadValues();
	}
	
	
	public Set<ApCityValue> getValues() {
		return values;
	}
	
	public ApCityValue fromName(String inValue) {
		if (inValue == null) {
			return null;
		}
		
		for (ApCityValue currValue : values) {
			if (currValue.getName().equals(inValue)) {
				return currValue;
			}
		}
		return null;
	}
	
	
	
	protected Set<ApCityValue> loadValues() throws ApException {

		Set<ApCityValue> resultValues = new HashSet<>();
		
		final File inputCityFile = new File(ApCityValues.class.getProtectionDomain().getCodeSource().getLocation().getPath().toString() 
				+ "org/benp/addressparser/data/AddressParser_UsCity_Default.csv");

		try (BufferedReader reader = new BufferedReader(new FileReader(inputCityFile))) {
			
			String currLine = reader.readLine();
			while (currLine != null) {
				
				List<String> splits = Splitter.on(",").splitToList(currLine);
				ApCityValue tempValue = new ApCityValue(splits.get(0), splits.get(1));
				resultValues.add(tempValue);
				currLine = reader.readLine();
			}
			
		} catch (FileNotFoundException fnfe) {
			throw new ApException("Could not find default Us City's file.", fnfe, 
					"inputCityFile", inputCityFile.getAbsolutePath());
		} catch (IOException ioe) {
			throw new ApException("Error reading default US Ciry's file.", ioe, 
					"inputCityFile", inputCityFile.getAbsolutePath());
		}
		
		return resultValues;
	}
}
