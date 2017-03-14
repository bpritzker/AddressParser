package org.benp.addressparser.parser.street;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.benp.addressparser.AddressParserConfig;
import org.benp.addressparser.ApException;
import org.benp.addressparser.component.Directional;
import org.benp.addressparser.component.street.StreetNameStreet;
import org.benp.addressparser.data.DirectionalEnum;
import org.benp.addressparser.data.Split;
import org.benp.addressparser.data.normalize.Mapper;
import org.benp.addressparser.parser.ApSplitter;
import org.benp.addressparser.parser.ParserBase;

import com.google.common.base.Splitter;

public class StreetNameStreetParser extends ParserBase {
	private Mapper mapper;

	public StreetNameStreetParser(Mapper inMapper, AddressParserConfig inConfig) {
		super(inConfig);
		mapper = inMapper;
	}

	public StreetNameStreet parse(ApSplitter inSplitter) throws ApException {
		StreetNameStreet resultStreetName = new StreetNameStreet();
		List<Split> remaingingSplits = inSplitter.getRemainingSplits();
		
		StringBuilder streetNameBuilder = new StringBuilder();
		
		// Now for the tricky part... 
		// Handle the case where the we have more than one part 
		// and check if the first part is a street pre-directional 
		// If the size is 1 then it's the street
		boolean firstSplitUsed = false;
		if (remaingingSplits.size() > 1) {
			String firstValue = remaingingSplits.get(0).getValue();
			DirectionalEnum tempDirectionalEnum = DirectionalEnum.fromMapping(firstValue);
			if (tempDirectionalEnum != null) {
				firstSplitUsed = true;
				Directional tempDirectional = new Directional();
				tempDirectional.setDirectional(tempDirectionalEnum);
				tempDirectional.addSplitterIndex(remaingingSplits.get(0));
				tempDirectional.setValid(true);
				resultStreetName.setPreDirectional(tempDirectional);
			}
			
		} 
		
		String prefixVal = "";
		for (int i =0; i < remaingingSplits.size(); i++) {
			if (i != 0 || ! firstSplitUsed) {
				streetNameBuilder.append(prefixVal).append(remaingingSplits.get(i).getValue());
				prefixVal = " ";
			}
		}
		String streetName =  streetNameBuilder.toString();
		
		
		if (StringUtils.isNotBlank(streetName)) {
			// we now have a full street name
			// Look for business abbreviations we can normalize
			List<String> streetNameSplits = Splitter.on(" ").trimResults().splitToList(streetName);
			String tempStreetName = buildStreetName(streetNameSplits);
			
			resultStreetName.setName(tempStreetName);
			resultStreetName.setSplitterIndecies(remaingingSplits);
			resultStreetName.setValid(true);
			inSplitter.addUsedSplits(remaingingSplits);
		}
		return resultStreetName;
	}

	/**
	 * There are a lot of special rules associated with street name so break into it's own method.
	 * @throws ApException 
	 */
	protected String buildStreetName(List<String> inStreetNameSplits) throws ApException {
		List<Integer> potentialAbbrivs = getPotentialAbbrivations(inStreetNameSplits);
		StringBuilder resultStreetName = new StringBuilder();
		String concatString = "";

		if (
				potentialAbbrivs.size() > 0 
				&& (
				inStreetNameSplits.size() == 2 
				&& potentialAbbrivs.size() != 1)) {
			for (int i=0; i< inStreetNameSplits.size(); i++) {
				String tempConcatVal = inStreetNameSplits.get(i);
				if (i == potentialAbbrivs.get(0)) {
					resultStreetName.append(concatString).append(getBusinessValue(tempConcatVal));
					
				} else {
					resultStreetName.append(concatString).append(tempConcatVal);
				}
				concatString = " ";
			}
		} else { // if there are more than 2 abbreviations, just look at the first one
			for (String currStreetNameSplit : inStreetNameSplits) {
				resultStreetName.append(concatString).append(currStreetNameSplit);
				concatString = " ";
			}
			
			

		}

		return resultStreetName.toString();
	}

	private List<Integer> getPotentialAbbrivations(List<String> inStreetNameSplits) throws ApException {

		List<Integer> resultPotentialAbbrivs = new ArrayList<>();
		for (int i=0; i < inStreetNameSplits.size(); i++) {
			String tempPotentialAbbriv = inStreetNameSplits.get(i).toUpperCase();
			if (mapper.getBusinessWord().fromValue(tempPotentialAbbriv) != null)  {
				resultPotentialAbbrivs.add(i);
			}
		}
		
		return resultPotentialAbbrivs;
	}

	/**
	 * This method is just used to normalize the address. 
	 * Eventually we might make the results configurable
	 */
	private String getBusinessValue(String inBusinessValue) {
		String resultvalue = inBusinessValue;
//		if (businessAbbreviationValues != null) {
//			AbbreviationValue  tempAbbrivValue = businessAbbreviationValues.fromName(inBusinessValue.toUpperCase());
//			if (tempAbbrivValue != null) {
//				// If there are more than one abbreviation???? for now just skip it.
//				if (tempAbbrivValue.getAbbreviations().size() == 1) {
//				// If we have just one abbreviation then get the first element. This is a hack
//				String businessAbbriv = new ArrayList<>(tempAbbrivValue.getAbbreviations()).get(0);
//				if (businessAbbriv != null) {
//					resultvalue = WordUtils.capitalizeFully(businessAbbriv);
//				}
//				}
//			}
//		}
		return resultvalue;
	}

}
