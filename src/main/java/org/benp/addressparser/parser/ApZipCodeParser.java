package org.benp.addressparser.parser;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.benp.addressparser.ApAddressParserConfig;
import org.benp.addressparser.ApException;
import org.benp.addressparser.component.ApZipCode;
import org.benp.addressparser.data.ApValueIndex;

public class ApZipCodeParser extends ApParserBase {
	
	private static final Logger	logger	=  LogManager.getLogger(ApZipCodeParser.class.getName());
	
	public ApZipCodeParser(ApAddressParserConfig addressParserConfig) {
		super(addressParserConfig);
	}
	
	
	
	/**
	 * This method parses the zip code from the address (the splitter)
	 * @throws ApException 
	 */
	public ApZipCode parse(ApSplitter splitter) throws ApException {
		ApZipCode resultZipCode = new ApZipCode();
		
		
		// Check up to 3 values to the right (this will compensate for bad data at the end ) 
		// It also takes longer to process.
		for (int i=0; i < 3 && (!resultZipCode.isValid()); i++) {
//			ApValueIndex rightMost = splitter.getNextRightValue();
			ApValueIndex rightMost = splitter.getNextRightValue(i);
			if (rightMost == null) {
				return new ApZipCode();
			}
			String rightMostValue = rightMost.getValue();
			
			// If the right most is 5 digits then it's just a 5 digit zip code
			if (StringUtils.isNumeric(rightMostValue) && rightMostValue.length() == 5) {
				resultZipCode.setZipCode(rightMostValue);
				resultZipCode.addIndicies(rightMost.getIndex());
				resultZipCode.setValid(true);
				splitter.addUsedSplitsAllRight(rightMost.getIndex());
				
			// If there are a 5 digit followed by 4 digit then we have a Plus 4 zip code
			} else if (StringUtils.isNumeric(rightMostValue) && rightMostValue.length() == 4) {
				ApValueIndex rightSecondMost = splitter.getNextRightValue(i + 1);
				if (rightSecondMost != null) {
					String rightSecondMostValue = rightSecondMost.getValue();
					if (StringUtils.isNumeric(rightSecondMostValue) && rightSecondMostValue.length()  == 5) {
						resultZipCode.setPlus4Code(rightMostValue);
						resultZipCode.setZipCode(rightSecondMostValue);
						resultZipCode.addIndicies(rightMost.getIndex(), rightSecondMost.getIndex());
						splitter.addUsedSplitsAllRight( rightSecondMost.getIndex());
						resultZipCode.setValid(true);
					}
				}
			} 
		}
		
		logger.trace("Zip Code:: Valid: <{}>, ZipCode: <{}>, Plus4Code: <{}>",
				resultZipCode.isValid(), resultZipCode.getZipCode(), resultZipCode.getPlus4Code());
		return resultZipCode;
	}

	
}
