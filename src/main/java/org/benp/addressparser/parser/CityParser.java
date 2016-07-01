package org.benp.addressparser.parser;

import java.util.ArrayList;
import java.util.List;

import org.benp.addressparser.AddressParserConfig;
import org.benp.addressparser.ApException;
import org.benp.addressparser.component.City;
import org.benp.addressparser.data.Split;
import org.benp.addressparser.data.mapping.Mapper;
import org.benp.addressparser.data.mapping.MappingValue;

import com.google.common.collect.Lists;

public class CityParser extends ParserBase {
	
	private Mapper mapper;
	

	public CityParser(Mapper inMapper, AddressParserConfig inConfig) {
		super(inConfig);
		if (mapper == null) {
			mapper = new Mapper(inConfig);
		}
	}


	public City parse(ApSplitter splitter) throws ApException {
	
		City resultCity = getApCity(splitter, 3);

		if (resultCity == null) {
			resultCity = new City();
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
	private City getApCity(ApSplitter splitter, int valuesToTry) throws ApException {
		List<List<Split>> tryValues = buildTryValues(splitter, valuesToTry);
		
		// We want to try with i = 0
		for (int i=tryValues.size()-1; i > -1 ; i--) {
			
			StringBuilder cityNameToFindBuilder = new StringBuilder();
			String prefix = "";
			for (Split currValueIndex : tryValues.get(i)) {
				cityNameToFindBuilder.append(prefix).append(currValueIndex.getValue().toUpperCase());
				prefix = " ";
			}
			String cityNameToFind = cityNameToFindBuilder.toString();
			
			// When debugging city, put breakpoint on line below.
			City resultCity = null;
			MappingValue tempCityStates = mapper.getCity().getMappings().get(cityNameToFind);
			if (tempCityStates != null) {
				resultCity = new City();
				resultCity.setCityName(cityNameToFind);
				resultCity.setStateValues(tempCityStates);
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
	protected List<List<Split>> buildTryValues(ApSplitter splitter, int valuesToTry) {

		
		// It's easier to get the values smallest to largest and then reverse it.
		List<List<Split>> resultTryValues = new ArrayList<>();
		for (int i=0; i < valuesToTry; i++) {
			
			List<Split> loopValues = new ArrayList<>();
			for (int j=0; j < (i + 1); j++) {
				Split tempValueIndex = splitter.getNextRightValue(j);
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
