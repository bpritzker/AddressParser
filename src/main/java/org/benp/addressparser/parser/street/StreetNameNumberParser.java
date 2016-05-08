package org.benp.addressparser.parser.street;

import org.apache.commons.lang3.math.NumberUtils;
import org.benp.addressparser.AddressParserConfig;
import org.benp.addressparser.ApException;
import org.benp.addressparser.component.street.StreetNameNumber;
import org.benp.addressparser.data.Split;
import org.benp.addressparser.parser.ParserBase;
import org.benp.addressparser.parser.ApSplitter;

public class StreetNameNumberParser extends ParserBase {

	public StreetNameNumberParser(AddressParserConfig config) {
		super(config);
	}


	public StreetNameNumber parse(ApSplitter splitter) throws ApException {
		return getAddressNumber(splitter);
	}
	
	
	
	/**
	 * Assume the suffix has already been take off.
	 */
	protected StreetNameNumber getAddressNumber(ApSplitter splitter) throws ApException {
		Split nextLeft = splitter.getNextLeftValue();
		
		StreetNameNumber resultStreetNumber = new StreetNameNumber();

		
		if (nextLeft == null) {
			return new StreetNameNumber();
		}
		
		int addressNumber = StreetNameNumber.INVALID_ADDRESS_NUMBER;
		
		// the value is a number then we have the address number.
		if (NumberUtils.isDigits(nextLeft.getValue())) {
			
			addressNumber = Integer.parseInt(nextLeft.getValue());
			splitter.addUsedSplit(nextLeft);
			resultStreetNumber.addSplitterIndex(nextLeft);
		}
		
		if (addressNumber == StreetNameNumber.INVALID_ADDRESS_NUMBER) {
			Split secondLeft = splitter.getNextLeftValue(1);
			if (secondLeft != null) {
				if (NumberUtils.isNumber(secondLeft.getValue())) {
					addressNumber = Integer.parseInt(secondLeft.getValue());
					splitter.addUsedSplit(nextLeft);
					splitter.addUsedSplit(secondLeft);
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
