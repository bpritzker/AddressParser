package org.benp.addressparser.parser;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.benp.addressparser.ApAddressParserConfig;
import org.benp.addressparser.ApException;
import org.benp.addressparser.component.ApDirectional;
import org.benp.addressparser.component.ApStreet;
import org.benp.addressparser.component.street.ApStreetAddressNumber;
import org.benp.addressparser.component.street.ApStreetStreetName;
import org.benp.addressparser.component.street.ApStreetSuffix;
import org.benp.addressparser.data.ApDirectionalEnum;
import org.benp.addressparser.data.ApStreetSuffixEnum;
import org.benp.addressparser.data.ApValueIndex;

public class ApStreetParser extends ApParserBase {

	public ApStreetParser(ApAddressParserConfig config) {
		super(config);
	}
	
	public ApStreet parse(ApSplitter splitter) throws ApException {
		ApStreet resultStreet = new ApStreet();
		
		ApStreetAddressNumber primaryNumber = getPrimaryNumber(splitter);
		resultStreet.setPrimaryNumber(primaryNumber);
		
		ApStreetSuffix addressSuffix = getSuffix(splitter);
		resultStreet.setStreetSuffix(addressSuffix);
		
		ApStreetStreetName streetName = getStreetName(splitter, primaryNumber, addressSuffix);
		resultStreet.setStreetName(streetName);

		// We don't need a street suffix to be valid. Might want to change that some other time
//		if (primaryNumber != null && addressSuffix != null && streetName != null) {
		if (primaryNumber != null && streetName != null) {
			resultStreet.setValid(true);
		}
		return resultStreet;
	}

	private ApStreetStreetName getStreetName(
			ApSplitter splitter, 
			ApStreetAddressNumber primaryNumber, 
			ApStreetSuffix suffix) throws ApException {
		
		ApStreetStreetName resultStreetName = null;
		
		int streetNameLeftIndex = 0;
		if (primaryNumber != null) {
			if (primaryNumber.getSplitterIndecies().size() == 0) {
				return resultStreetName;
			}
			streetNameLeftIndex = primaryNumber.getSplitterIndecies().get(0);
		}
		
		ApValueIndex nextRightIndex = splitter.getNextRightValue();
		if (nextRightIndex != null) {
			int streetNameRightIndex = nextRightIndex.getIndex();
			if (suffix.isValid()) {
				streetNameRightIndex = suffix.getSplitterIndecies().get(0);
			}
			resultStreetName = buildStreetName(splitter, streetNameLeftIndex, streetNameRightIndex);
		}
		
		
		return resultStreetName;
		
	}

	private ApStreetStreetName buildStreetName(
			ApSplitter splitter, 
			int streetNameLeftIndex, 
			int streetNameRightIndex) throws ApException {
		
		ApStreetStreetName resultStreetName = new ApStreetStreetName();
		
		List<ApValueIndex> streetNameValues = splitter.getValues(streetNameLeftIndex, streetNameRightIndex);
		
		ApDirectional preDirectional = getDirectional(streetNameValues, splitter);
		resultStreetName.setPreDirectional(preDirectional); // We can set a non-null as long as we check validaity

		List<ApValueIndex> streetNameValuesAfterPreDirectional = new ArrayList<>();
		for (ApValueIndex currValueIndex : streetNameValues) {
			if (! preDirectional.getSplitterIndecies().contains(currValueIndex.getIndex())) {
				streetNameValuesAfterPreDirectional.add(currValueIndex);
			}
		}
		
		String streetName = ApValueIndex.joinValues(streetNameValuesAfterPreDirectional);
		if (StringUtils.isNoneBlank(streetName)) {
			splitter.addUsedSplits(ApValueIndex.getIndices(streetNameValuesAfterPreDirectional));
			resultStreetName.setName(streetName);
			resultStreetName.setValid(true);
		}
		
		return resultStreetName;
	}

	private ApDirectional getDirectional(
			List<ApValueIndex> streetNameValues,
			ApSplitter splitter) throws ApException {

		ApDirectional resultDirectional = new ApDirectional();

		ApDirectionalEnum tempDirectionalEnum = null;
		
		// First we want to try 2 values
		if (streetNameValues.size() >= 2) {
			String tempPredirectionalString = 
					streetNameValues.get(0).getValue() + " " + streetNameValues.get(1).getValue();
			tempDirectionalEnum = ApDirectionalEnum.fromMapping(tempPredirectionalString.toUpperCase());
			if (tempDirectionalEnum != null) {
				resultDirectional.setDirectional(tempDirectionalEnum);
				resultDirectional.setIndicies(streetNameValues.get(0).getIndex(),streetNameValues.get(1).getIndex());
				splitter.addUsedSplits(streetNameValues.get(0).getIndex(), streetNameValues.get(1).getIndex());
				resultDirectional.setValid(true);
			}
		} 
		
		if (tempDirectionalEnum == null && streetNameValues.size() >= 1) { // If we didn't have a 2 part, then try just 1 part
			String tempPredirectionalString = streetNameValues.get(0).getValue();
			tempDirectionalEnum = ApDirectionalEnum.fromMapping(tempPredirectionalString.toUpperCase());
			if (tempDirectionalEnum != null) {
				resultDirectional.setDirectional(tempDirectionalEnum);
				resultDirectional.setIndicies(streetNameValues.get(0).getIndex());
				splitter.addUsedSplits(streetNameValues.get(0).getIndex());
				resultDirectional.setValid(true);
			}
		}
		return resultDirectional;
		
	}

	private ApStreetSuffix getSuffix(ApSplitter splitter) throws ApException {

		ApStreetSuffix resultStreetSuffix = new ApStreetSuffix();
		int rightValueOffset = 0;
		ApValueIndex rightValue = splitter.getNextRightValue();
		while (rightValue != null) {
			String tempSuffixValue = rightValue.getValue().toUpperCase();
			ApStreetSuffixEnum tempStreetSuffix = ApStreetSuffixEnum.fromCommonAbbreviation(tempSuffixValue);
			
			if (tempStreetSuffix != null) {
				splitter.addUsedSplits(rightValue.getIndex());
				resultStreetSuffix.setIndicies(rightValue.getIndex());
				resultStreetSuffix.setStreetSuffix(tempStreetSuffix);
				resultStreetSuffix.setValid(true);
				break;
			}
			rightValueOffset++;
			rightValue = splitter.getNextRightValue(rightValueOffset);
		}
		return resultStreetSuffix;
	}

	private ApStreetAddressNumber getPrimaryNumber(ApSplitter splitter) throws ApException {
		ApValueIndex nextLeft = splitter.getNextLeftValue();
		
		if (nextLeft == null) {
			return new ApStreetAddressNumber();
		}
		
		ApStreetAddressNumber resultStreetNumber = null;
			resultStreetNumber = new ApStreetAddressNumber();
			if (NumberUtils.isNumber(nextLeft.getValue())) {
				resultStreetNumber.setNumber(nextLeft.getValue());
				resultStreetNumber.setIndicies(nextLeft.getIndex());
				resultStreetNumber.setValid(true);
				splitter.addUsedSplits(nextLeft.getIndex());
			}
		return resultStreetNumber;
	}

}
