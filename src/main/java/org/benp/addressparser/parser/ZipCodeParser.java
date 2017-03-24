package org.benp.addressparser.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.benp.addressparser.AddressParserConfig;
import org.benp.addressparser.ApException;
import org.benp.addressparser.component.ZipCode;
import org.benp.addressparser.data.Split;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Splitter;

public class ZipCodeParser extends ParserBase {
	
	Logger logger = LoggerFactory.getLogger(ZipCodeParser.class);
	Pattern splitPattern = Pattern.compile("\\D");
	
	
	public ZipCodeParser(AddressParserConfig inAddressParserConfig) {
		super(inAddressParserConfig);
	}
	
	
	/**

	 */
	public ZipCode parse(ApSplitter inSplitter) throws ApException {
		ZipCode resultZipCode = new ZipCode();
	
		
		// need to have 3  for things list "12345 - 6789  " it's 3 parts
		List<String> possibleZips = new ArrayList<>();
		List<Split> usedSplits = new ArrayList<>();
		for (int i=0; i < 3; i++) {

			// if the temp split is null then previous attempts failed and no more to
			//  try so just return
			Split tempSplit = inSplitter.getNextRightValue(i);
			if (tempSplit == null) {
				return resultZipCode;
			}
			
			// get the next split items and add them to the top of the list
			//   
			usedSplits.add(tempSplit);
			List<String> zipSplits = getSplits(tempSplit.getValue());
			possibleZips.addAll(0,zipSplits);
			List<String> zipCodeValues = getZipCodeParts(possibleZips);
			
			if (zipCodeValues.size() > 0) {
				resultZipCode.setZipCode(zipCodeValues.get(0));
				if (zipCodeValues.size() > 1) {
					resultZipCode.setPlus4Code(zipCodeValues.get(1));
				}
				resultZipCode.setValid(true);
				inSplitter.addUsedSplits(usedSplits);
				resultZipCode.addSplitterIndecies(usedSplits);
				break;
			}
		}
		
		logger.trace("Zip Code:: Valid: <{}>, ZipCode: <{}>, Plus4Code: <{}>",
				resultZipCode.isValid(), resultZipCode.getZipCode(), resultZipCode.getPlus4Code());
		return resultZipCode;
		
		
	}


	private List<String> getSplits(String inValue) {
		List<String> resultSplits = 
				Splitter
				.on(splitPattern)
				.omitEmptyStrings()
				.trimResults()
				.splitToList(inValue);
		return resultSplits;
	}

	/**
	 * Most common zip codes look like:
	 * 12345  
	 * 12345-1234
	 * 12345 1234
	 */
	protected List<String> getZipCodeParts(List<String> inSplits) {

		List<String> resultZipCodeParts = new ArrayList<>();
		
		for (int i=0; i < inSplits.size(); i++) {
			
			String zip5 = parse5Digits(inSplits.get(i));
			if (zip5 != null) {
				resultZipCodeParts.add(zip5);
				if ((i + 1) < inSplits.size()) {
					String zip4 = parse4Digits(inSplits.get(i+1));
					if (zip4 != null) {
						resultZipCodeParts.add(zip4);
					}
				}
				break;
			}
		}
		return resultZipCodeParts;
	}




	private String parse4Digits(String inString) {
		String cleanString = inString.replaceAll("[^A-Za-z0-9 ]", "");
		if (cleanString.length() == 4 && StringUtils.isNumeric(cleanString)) {
			return cleanString;
		} else {
			return null;
		}
	}


	private String parse5Digits(String inString) {
		String cleanString = inString.replaceAll("[^A-Za-z0-9 ]", "");
		if (cleanString.length() == 5 && StringUtils.isNumeric(cleanString)) {
			return cleanString;
		} else {
			return null;
		}
	}

}
