package org.benp.addressparser.parser;

import org.benp.addressparser.ApAddressParserConfig;
import org.benp.addressparser.ApException;
import org.benp.addressparser.component.ApCity;
import org.benp.addressparser.data.ApCityValue;
import org.benp.addressparser.data.ApCityValues;
import org.benp.addressparser.data.ApValueIndex;

import com.google.common.base.Joiner;

public class ApCityParser extends ApParserBase {
	
	private ApCityValues cityValues;
	
	
	/**
	 * 
	 * @param stateAbbreviations - see the class description, if null it will use a default mapping.
	 * @throws ApException 
	 */
	public ApCityParser(ApAddressParserConfig config) {
		super(config);
	}
	
	



	protected ApCityValues getCityValues() throws ApException {
		if (cityValues == null) {
			ApCityValues tempCityValues = new ApCityValues();
			tempCityValues.init();
			cityValues = tempCityValues;
		}
		return cityValues;
	}





	public ApCity parse(ApSplitter splitter) throws ApException {
	
		ApCity resultCity = getApCity(splitter, 3);

		if (resultCity == null) {
			resultCity = new ApCity();
		}
		return resultCity;
	}
	
	
	
	/**
	 * For cities, the city name can be multiple words at a time. 
	 * What we want to do is try multiple combinations
	 * We need to try the max number of names FIRST so we can catch something like..
	 * "South Boston" before "Boston"
	 * 
	 * @param splitter
	 * @param valuesToTry
	 * @return
	 * @throws ApException 
	 */
	private ApCity getApCity(ApSplitter splitter, int valuesToTry) throws ApException {
		
		
		for (int i=valuesToTry; i > 0; i--) { // This is the number of values to try and match on
			int[] splitterIndices = new int[i];
			String[] valuesArray = new String[i];
			for (int j=0; j < i; j++) {
				
				ApValueIndex tempRightMost = splitter.getNextRightValue(j);
				if (tempRightMost == null) {
					return null;
				} else {
					// need to put them in reverse order
					splitterIndices[i - j - 1] = tempRightMost.getIndex();
					valuesArray[i - j - 1] = tempRightMost.getValue();
				}
			}

			String cityNameToFind = Joiner.on(' ').join(valuesArray).toUpperCase();
			// When debugging city, put breakpoint on line below.
			ApCity resultCity = null;
			ApCityValue tempCityValue = getCityValues().fromName(cityNameToFind);
			if (tempCityValue != null) {
				resultCity = new ApCity();
				resultCity.setCityValue(tempCityValue);
				resultCity.addSplitterIndecies(splitterIndices);
				resultCity.setValid(true);
				splitter.addUsedSplits(splitterIndices);
				return resultCity;
			}
			
		}
		return null;
	}



}
