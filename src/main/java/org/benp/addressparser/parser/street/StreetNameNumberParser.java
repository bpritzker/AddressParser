package org.benp.addressparser.parser.street;

import org.apache.commons.lang3.math.NumberUtils;
import org.benp.addressparser.common.AddressParserConfig;
import org.benp.addressparser.common.ApException;
import org.benp.addressparser.component.street.StreetNameNumber;
import org.benp.addressparser.component.street.StreetNameNumberPair;
import org.benp.addressparser.data.Split;
import org.benp.addressparser.parser.ApSplitter;
import org.benp.addressparser.parser.ParserBase;

public class StreetNameNumberParser extends ParserBase {

	public StreetNameNumberParser(AddressParserConfig inConfig) {
		super(inConfig);
	}


	public StreetNameNumber parse(ApSplitter inSplitter) throws ApException {
		return getAddressNumber(inSplitter);
	}
	
	
	
	/**
	 * Assume the suffix has already been take off.
	 */
	protected StreetNameNumber getAddressNumber(ApSplitter inSplitter) throws ApException {
		Split nextLeft = inSplitter.getNextLeftValue();
		
		StreetNameNumber resultStreetNumber = new StreetNameNumber();

		
		if (nextLeft == null) {
			return new StreetNameNumber();
		}
		
		int addressNumber = StreetNameNumber.INVALID_ADDRESS_NUMBER;
		String addressNumberSecondPart = null;
		
		String potentialStreetNumber = nextLeft.getValue();
		
		// the value is a number then we have the address number.
		if (NumberUtils.isDigits(potentialStreetNumber)) {
			addressNumber = Integer.parseInt(nextLeft.getValue());
			inSplitter.addUsedSplit(nextLeft);
			resultStreetNumber.addSplitterIndex(nextLeft);
		} else {  
			// Doesn't start with digits.. could contain digits and have special chars between
			// Look at the first 2 chars, if they are digits then the rest until we get a space is the second part of the street number
			StreetNameNumberPair streetNumberNamePair = buildStreetNumberPair(potentialStreetNumber);
			if (streetNumberNamePair != null) {
				addressNumber = streetNumberNamePair.getStreetNumber();
				addressNumberSecondPart = streetNumberNamePair.getStreetNumberSecondPart();
				inSplitter.addUsedSplit(nextLeft);
				resultStreetNumber.addSplitterIndex(nextLeft);
			}
		}
		
//		if (addressNumber == StreetNameNumber.INVALID_ADDRESS_NUMBER) {
//			Split secondLeft = inSplitter.getNextLeftValue(1);
//			if (secondLeft != null) {
//				if (NumberUtils.isNumber(secondLeft.getValue())) {
//					addressNumber = Integer.parseInt(secondLeft.getValue());
//					inSplitter.addUsedSplit(nextLeft);
//					inSplitter.addUsedSplit(secondLeft);
//					resultStreetNumber.addSplitterIndex(nextLeft);
//					resultStreetNumber.addSplitterIndex(secondLeft);
//				}
//			}
//		}

		if (addressNumber != StreetNameNumber.INVALID_ADDRESS_NUMBER) {
			resultStreetNumber.setAddressNumber(addressNumber);
			resultStreetNumber.setAddressNumberSecondPart(addressNumberSecondPart);
			resultStreetNumber.setValid(true);
		}
		
		return resultStreetNumber;
	}


	/**
	 * If the first char is a digit then we assume we have a number, and everything after the number
	 * is the second part. 
	 * Let the splitter worry about the value after 
	 */
	protected StreetNameNumberPair buildStreetNumberPair(String potentialStreetNumber) {
		
		if (potentialStreetNumber == null || potentialStreetNumber.length() < 1) {
			return null;
		}
		
		// If the first char is NOT a digit then return null. 
		// This might need to change if we find address numbers starting with a letter?
		if (! Character.isDigit(potentialStreetNumber.charAt(0))) {
			return null;
		}
		
		int lastNumberIndex = -1;
		for (int i=0; i < potentialStreetNumber.length(); i++) {
			char currChar = potentialStreetNumber.charAt(i);
			if (Character.isDigit(currChar)) {
				lastNumberIndex = i;
			} else {
				break;
			}
		}
		
		// if here, we know that at least the first char is digit and we have a split index so build the result object
		int streetNumber = Integer.parseInt(potentialStreetNumber.substring(0,  lastNumberIndex+1));
		String streetNumberSecondPart = potentialStreetNumber.substring(lastNumberIndex+1); 
		StreetNameNumberPair resultPair = new StreetNameNumberPair(streetNumber, streetNumberSecondPart);
		return resultPair;
		
	}


}
