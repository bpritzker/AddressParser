package org.benp.addressparser.parser.street;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.benp.addressparser.ApAddressParserConfig;
import org.benp.addressparser.ApException;
import org.benp.addressparser.component.ApDirectional;
import org.benp.addressparser.component.street.ApStreetStreetName;
import org.benp.addressparser.data.ApDirectionalEnum;
import org.benp.addressparser.data.ApSplit;
import org.benp.addressparser.parser.ApParserBase;
import org.benp.addressparser.parser.ApSplitter;

public class ApStreetNameParser extends ApParserBase {

	public ApStreetNameParser(ApAddressParserConfig config) {
		super(config);
	}

	public ApStreetStreetName parse(ApSplitter splitter) throws ApException {
		ApStreetStreetName resultStreetName = new ApStreetStreetName();
		List<ApSplit> remaingingSplits = splitter.getRemainingSplits();
		
		StringBuilder streetNameBuilder = new StringBuilder();
		
		// Now for the tricky part... 
		// Handle the case where the we have more than one part 
		// and check if the first part is a street pre-directional 
		// If the size is 1 then it's the street
		boolean firstSplitUsed = false;
		if (remaingingSplits.size() > 1) {
			String firstValue = remaingingSplits.get(0).getValue();
			ApDirectionalEnum tempDirectionalEnum = ApDirectionalEnum.fromMapping(firstValue);
			if (tempDirectionalEnum != null) {
				firstSplitUsed = true;
				ApDirectional tempDirectional = new ApDirectional();
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
			resultStreetName.setName(streetName);
			resultStreetName.setSplitterIndecies(remaingingSplits);
			resultStreetName.setValid(true);
			splitter.addUsedSplits(remaingingSplits);
		}
//		
//		List<ApSplit> streetNameValues = splitter.getValues(streetNameLeftSplit);
//		
//		// TODO: If there is only one value then it's not a predirecional, it's the street name
//		ApDirectional preDirectional = getDirectional(streetNameValues, splitter);
//		resultStreetName.setPreDirectional(preDirectional); // We can set a non-null as long as we check validaity
//
//		List<ApSplit> streetNameValuesAfterPreDirectional = new ArrayList<>();
//		for (ApSplit currValueIndex : streetNameValues) {
//			if (! preDirectional.getSplitterIndecies().contains(currValueIndex.getIndex())) {
//				streetNameValuesAfterPreDirectional.add(currValueIndex);
//			}
//		}
//		
//		String streetName = ApSplit.joinValues(streetNameValuesAfterPreDirectional);
//		if (StringUtils.isNoneBlank(streetName)) {
//			splitter.addUsedSplits(streetNameValuesAfterPreDirectional);
////			resultStreetName.addIndicies(ApSplit.getIndices(streetNameValuesAfterPreDirectional));
//			resultStreetName.addSplitterIndecies(streetNameValuesAfterPreDirectional);
//			resultStreetName.setName(streetName);
//			resultStreetName.setValid(true);
//		}
//		
		return resultStreetName;
	}

}
