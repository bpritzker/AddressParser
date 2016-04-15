package org.benp.addressparser.parser;

import org.apache.commons.lang3.math.NumberUtils;
import org.benp.addressparser.ApAddressParserConfig;
import org.benp.addressparser.ApException;
import org.benp.addressparser.component.street.ApStreetAddressNumber;
import org.benp.addressparser.data.ApSplit;

public class ApStreetNumberParser extends ApParserBase {

	public ApStreetNumberParser(ApAddressParserConfig config) {
		super(config);
	}


	public ApStreetAddressNumber parse(ApSplitter splitter) throws ApException {
		return getAddressNumber(splitter);
	}
	
	
	
	/**
	 * Assume the suffix has already been take off.
	 */
	protected ApStreetAddressNumber getAddressNumber(ApSplitter splitter) throws ApException {
		ApSplit nextLeft = splitter.getNextLeftValue();
		
		ApStreetAddressNumber resultStreetNumber = new ApStreetAddressNumber();

		
		if (nextLeft == null) {
			return new ApStreetAddressNumber();
		}
		
		int addressNumber = ApStreetAddressNumber.INVALID_ADDRESS_NUMBER;
		
		// the value is a number then we have the address number.
		if (NumberUtils.isDigits(nextLeft.getValue())) {
			
			addressNumber = Integer.parseInt(nextLeft.getValue());
			splitter.addUsedSplits(nextLeft.getIndex());
			resultStreetNumber.addIndicies(nextLeft.getIndex());
		}
		
		String addressNumberPrefix = null;
		if (addressNumber == ApStreetAddressNumber.INVALID_ADDRESS_NUMBER) {
			ApSplit secondLeft = splitter.getNextLeftValue(1);
			if (secondLeft != null) {
				if (NumberUtils.isNumber(secondLeft.getValue())) {
					addressNumber = Integer.parseInt(secondLeft.getValue());
					addressNumberPrefix = nextLeft.getValue();
					splitter.addUsedSplits(nextLeft.getIndex());
					splitter.addUsedSplits(secondLeft.getIndex());
					resultStreetNumber.addIndicies(nextLeft.getIndex());
					resultStreetNumber.addIndicies(secondLeft.getIndex());
				}
			}
		}
		

		String addressNumberSuffix = getAddressNumberSuffix(splitter);
		
		

		if (addressNumber != ApStreetAddressNumber.INVALID_ADDRESS_NUMBER) {
			resultStreetNumber.setAddressNumber(addressNumber);
			resultStreetNumber.setAddressNumberPrefix(addressNumberPrefix);
			resultStreetNumber.setAddressNumberSuffix(addressNumberSuffix);
			resultStreetNumber.setValid(true);
		}
		
		return resultStreetNumber;
	}

	
	
	// !Assume we already stripped off the address suffix
	// For the address number Suffix......
	// If we have an address number then look at all the items between the 
	// address number and the suffix. 
	// If we have something for the address name then anything that is less than a number of characters (1 or 2)
	// will be part of the suffix as long as there is at least one thing left over for the address
	// See the unit tests for examples.
	private String getAddressNumberSuffix(ApSplitter splitter) throws ApException {

		ApSplit nextLeft = splitter.getNextLeftValue();
		if (nextLeft != null) {
			// based on what I have seen there can only be 2 values that would qualify as suffix
//			ApValueIndex nextLeft = splitter.getNextLeftValue();
//			ApValueIndex nextLeftOffset = splitter.getNextLeftValue(1);
			if (nextLeft.getValue().length() < 3) {
				splitter.addUsedSplits(nextLeft.getIndex());
				return nextLeft.getValue();
			}
//			System.out.println("FIXME" + nextLeft + nextLeftOffset);
			
		}
		
		return null;
		
	}
	
	

}
