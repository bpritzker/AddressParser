package org.benp.addressparser.parser.street;

import org.apache.commons.lang3.math.NumberUtils;
import org.benp.addressparser.AddressParserConfig;
import org.benp.addressparser.ApException;
import org.benp.addressparser.component.street.StreetNameNumber;
import org.benp.addressparser.data.Split;
import org.benp.addressparser.parser.ParserBase;
import org.benp.addressparser.parser.ApSplitter;

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
		
		// the value is a number then we have the address number.
		if (NumberUtils.isDigits(nextLeft.getValue())) {
			
			addressNumber = Integer.parseInt(nextLeft.getValue());
			inSplitter.addUsedSplit(nextLeft);
			resultStreetNumber.addSplitterIndex(nextLeft);
		}
		
		if (addressNumber == StreetNameNumber.INVALID_ADDRESS_NUMBER) {
			Split secondLeft = inSplitter.getNextLeftValue(1);
			if (secondLeft != null) {
				if (NumberUtils.isNumber(secondLeft.getValue())) {
					addressNumber = Integer.parseInt(secondLeft.getValue());
					inSplitter.addUsedSplit(nextLeft);
					inSplitter.addUsedSplit(secondLeft);
					resultStreetNumber.addSplitterIndex(nextLeft);
					resultStreetNumber.addSplitterIndex(secondLeft);
				}
			}
		}

		if (addressNumber != StreetNameNumber.INVALID_ADDRESS_NUMBER) {
			resultStreetNumber.setAddressNumber(addressNumber);
			resultStreetNumber.setValid(true);
		}
		
		return resultStreetNumber;
	}


}
