package org.benp.addressparser.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.benp.addressparser.AddressParserConfig;
import org.benp.addressparser.ApException;
import org.benp.addressparser.component.City;
import org.benp.addressparser.data.Split;
import org.benp.addressparser.data.normalize.Mapper;
import org.benp.addressparser.data.normalize.MappingValue;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;

public class CityParser extends ParserBase {
	
	private Mapper mapper = null;
	

	public CityParser(Mapper inMapper, AddressParserConfig inConfig) {
		super(inConfig);
		mapper = inMapper;
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
				
				String normalizedCityName = normalize(currValueIndex.getValue());
				
				cityNameToFindBuilder.append(prefix).append(normalizedCityName);
				prefix = " ";
			}
			String cityNameToFind =  cityNameToFindBuilder.toString();
			
			
			// When debugging city, put breakpoint on line below.
			City resultCity = null;
			
//			MappingValue tempCityStates = mapper.getCity().getMappings().get(cityNameToFind);
			MappingValue tempCityStates = findCityMappingValue(cityNameToFind);
			
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





//	private MappingValue findCityMappingValue(String cityNameToFind) {
//	
//		mapper.getCity().getMappings()
//		
//		
//		
//	}


	protected MappingValue findCityMappingValue(String cityNameToFind) throws ApException {

		MappingValue resultCityStates = mapper.getCity().getMappings().get(cityNameToFind);

		// If we did not find the city name in the normal mapping see if we can find it 
		//     in a business mapping
		// Also assume that the string passed in is semi normalized and is all upper case and 
		//    only has 1 space between the values
		if (resultCityStates == null) {
			List<String> splits = Splitter.on(" ").splitToList(cityNameToFind);
			
			resultCityStates = findBusinessMatch(null, splits);
			
//			for (int currSplitsIndex = 0; currSplitsIndex < splits.size(); currSplitsIndex++) {
//				
//				// This will get us a set of all the possible business words that match the current split index
//				String currSplitValue = splits.get(currSplitsIndex);
//				Set<String> tryValues =  mapper.getBusinessWord().getMappings().get(currSplitValue).getValues();
//				
//				// Loop through all the business words and put 
//				for (String currTryValue : tryValues) {
//					
//					
//					
//					
//					List<String> tempOtherVals = getValuesMinusFirst(splits);
//					String tempCityNameToFind = currTryValue + " " + Joiner.on(" ").join(tempOtherVals);
//					MappingValue tempMappingValue = findCityMappingValueSecondValue(tempCityNameToFind);
//					if (tempMappingValue != null) {
//						return tempMappingValue;
//					}
//				}
//			}
		}
		
		return resultCityStates;
	}
	
//	
//	
//	protected MappingValue findCityMappingValueSecondValue(String cityNameToFind) throws ApException {
//		
//		MappingValue resultCityStates = mapper.getCity().getMappings().get(cityNameToFind);
//
//		if (resultCityStates == null) {
//			List<String> splits = Splitter.on(" ").splitToList(cityNameToFind);
//			
//			for (String currSplits : splits) {
//				
//				Set<String> tryValues =  mapper.getBusinessWord().getMappings().get(currSplits).getValues();
//				if (tryValues.size() == 1) {
//					return null;
//				} else {
//					
//				for (String currTryValue : tryValues) {
//					
//
//					
//					List<String> tempOtherVals = getValuesMinusFirst(splits);
//					String tempCityNameToFind = currTryValue + " " + Joiner.on(" ").join(tempOtherVals);
//					MappingValue tempMappingValue = findCityMappingValueSecondValue(tempCityNameToFind);
//					if (tempMappingValue != null) {
//						return tempMappingValue;
//					}
//				}
//			}
//	}
//	
//	
//	
//	
	
	
	
	

//
//	private MappingValue findBusinessMatch(List<String> splits) throws ApException {
//		
//		if (splits.size() == 0) {
//			return null;
//		}
//
//		String firstSplitValue = splits.get(0);
//		
//		// This will get us a set of all the possible business words that match the current split index
//		Set<String> tryValues =  mapper.getBusinessWord().getMappings().get(firstSplitValue).getValues();
//		
//		// This is a newly created list of all the values from the splits minus the first element.
//		//  Ex: ["One","Two", "Three"] is splits then this would be ["Two", "Three"]
//		List<String> splitsMinusFirstVal = new ArrayList<>(splits);
//		splitsMinusFirstVal.remove(0);
//		
//		// Loop through all the business words and put a temp city name together. 
//		// Want to test ALL the try values before going to recursion.
//		// This will keep as many raw values as possible
//		for (String currTryValue : tryValues) {
//			
//			String tempCityToFind = currTryValue + " " + Joiner.on(" ").join(splitsMinusFirstVal);
//			MappingValue resultCityStates = mapper.getCity().getMappings().get(tempCityToFind);
//			if (resultCityStates != null) {
//				return resultCityStates;
//			}
// 		}
//		
//		// If we did not find a match in the first loop, go deeper
//		for (String currTryValue : tryValues) {
//			MappingValue resultCityStates = findBusinessMatch(currTryValue, splitsMinusFirstVal);
//			if (resultCityStates != null) {
//				return resultCityStates;
//			}
//		}
//		return null;
//	}		
//		
//		





	protected MappingValue findBusinessMatch(String firstPart, List<String> splits) throws ApException {

		if (splits.size() == 0) {
			return null;
		}

		String firstSplitValue = splits.get(0);
		
		// This will get us a set of all the possible business words that match the current split index
		
		
//		Set<String> tryValues = mapper.getBusinessWord().getMappings().get(firstSplitValue).getValues();
		MappingValue tempMappingValue = mapper.getBusinessWord().getMappings().get(firstSplitValue);
		
		// If we don't find any mappings then return null
		if (tempMappingValue == null) {
			return null;
		}
		
		Set<String> tryValues = tempMappingValue.getValues();
		// This is a newly created list of all the values from the splits minus the first element.
		//  Ex: ["One","Two", "Three"] is splits then this would be ["Two", "Three"]
		List<String> splitsMinusFirstVal = new ArrayList<>(splits);
		splitsMinusFirstVal.remove(0);
		
		// Loop through all the business words and put a temp city name together. 
		// Want to test ALL the try values before going to recursion.
		// This will keep as many raw values as possible
		for (String currTryValue : tryValues) {
			
			String tempCityToFind;
			if (firstPart == null) {
				tempCityToFind = currTryValue + " " + Joiner.on(" ").join(splitsMinusFirstVal);
			} else {
				tempCityToFind = firstPart + " " + currTryValue + " " + Joiner.on(" ").join(splitsMinusFirstVal);
			}
			
			
			MappingValue resultCityStates = mapper.getCity().getMappings().get(tempCityToFind);
			if (resultCityStates != null) {
				return resultCityStates;
			}
 		}
		
		// If we did not find a match in the first loop, go deeper
		for (String currTryValue : tryValues) {
			String firstAndSecondPart;
			if (firstPart == null) {
				firstAndSecondPart = currTryValue;
			} else {
				firstAndSecondPart = firstPart + " " + currTryValue;
			}
			MappingValue resultCityStates = findBusinessMatch(firstAndSecondPart, splitsMinusFirstVal);
			if (resultCityStates != null) {
				return resultCityStates;
			}
		}
		return null;
		
		
		
}


	private String normalize(String rawCityNameToFind) throws ApException {
		
		if (rawCityNameToFind == null) {
			return null;
		} else {
			return rawCityNameToFind.toUpperCase();
		}
		
//		Mapping businessWordMapping = mapper.getBusinessWord();
//		
//		String upperCaseName = rawCityNameToFind.toUpperCase();
//		MappingValue mappingValue = businessWordMapping.getMappings().get(upperCaseName);
//		
//		if (mappingValue == null) {
//			return rawCityNameToFind;
//		}
//		mappingValue.getValues();
//		
//		String resultNormalizedName;
//		if (mappingValue == null) {
//			resultNormalizedName = rawCityNameToFind.toUpperCase();
//		} else {
//			resultNormalizedName = mappingValue.getDefualtValue();
//		}
//		return resultNormalizedName;
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
