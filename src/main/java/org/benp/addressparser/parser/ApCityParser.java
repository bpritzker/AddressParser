package org.benp.addressparser.parser;

import java.util.ArrayList;
import java.util.List;

import org.benp.addressparser.ApAddressParserConfig;
import org.benp.addressparser.ApException;
import org.benp.addressparser.component.ApCity;
import org.benp.addressparser.data.ApCityValue;
import org.benp.addressparser.data.ApCityValues;
import org.benp.addressparser.data.ApSplit;

import com.google.common.collect.Lists;

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
		List<List<ApSplit>> tryValues = buildTryValues(splitter, valuesToTry);
		
		// We want to try with i = 0
		for (int i=tryValues.size()-1; i > -1 ; i--) {
			
			StringBuilder cityNameToFindBuilder = new StringBuilder();
			String prefix = "";
			for (ApSplit currValueIndex : tryValues.get(i)) {
				cityNameToFindBuilder.append(prefix).append(currValueIndex.getValue().toUpperCase());
				prefix = " ";
			}
			String cityNameToFind = cityNameToFindBuilder.toString();
			
			// When debugging city, put breakpoint on line below.
			ApCity resultCity = null;
			ApCityValue tempCityValue = getCityValues().fromName(cityNameToFind);
			if (tempCityValue != null) {
				resultCity = new ApCity();
				resultCity.setCityValue(tempCityValue);
				resultCity.addSplitterIndecies(tryValues.get(i));
				resultCity.setValid(true);
				splitter.addUsedSplits(tryValues.get(i));
				return resultCity;
			}
			
		}
		return null;
	}





	/**
	 * This method will build a list of values to try and match for the city names.
	 * </BR>
	 * It will start from the right most and get up to as many "valuesToTry" as it can.
	 * </BR>
	 * See the test method for examples on how this works.
	 */
	protected List<List<ApSplit>> buildTryValues(ApSplitter splitter, int valuesToTry) {

		
		// It's easier to get the values smallest to largest and then reverse it.
		List<List<ApSplit>> resultTryValues = new ArrayList<>();
		for (int i=0; i < valuesToTry; i++) {
			
			List<ApSplit> loopValues = new ArrayList<>();
			for (int j=0; j < (i + 1); j++) {
				ApSplit tempValueIndex = splitter.getNextRightValue(j);
				// If the temp value is null then there are no more "next right" values. 
				// so break out of the loop, we are done.
				if (tempValueIndex == null) {
					break;
				}
				loopValues.add(tempValueIndex);
			}
			
			// If there were no values then we are done done
			if (loopValues.size() == 0 || loopValues.size() < (i+1)) {
				break;
			} else {
				// we want the reverse order we pulled the elements off.
				resultTryValues.add(Lists.reverse(loopValues));
			}
		}
		return resultTryValues;
	}



}
