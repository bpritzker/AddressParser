package org.benp.addressparser.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.benp.addressparser.ApAddressParserConfig;
import org.benp.addressparser.ApException;
import org.benp.addressparser.component.ApZipCode;
import org.benp.addressparser.data.ApSplit;

import com.google.common.base.Splitter;

public class ApZipCodeParser extends ApParserBase {
	
	private static final Logger	logger	=  LogManager.getLogger(ApZipCodeParser.class.getName());
	Pattern splitPattern = Pattern.compile("\\D");
	
	
	public ApZipCodeParser(ApAddressParserConfig addressParserConfig) {
		super(addressParserConfig);
	}
	
	
	/**

	 */
	public ApZipCode parse(ApSplitter splitter) throws ApException {
		ApZipCode resultZipCode = new ApZipCode();
	
		
		// need to have 3  for things list "12345 - 6789  " it's 3 parts
		List<String> possibleZips = new ArrayList<>();
		List<ApSplit> usedSplits = new ArrayList<>();
		for (int i=0; i < 3; i++) {

			// if the temp split is null then previous attempts failed and no more to
			//  try so just return
			ApSplit tempSplit = splitter.getNextRightValue(i);
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
				splitter.addUsedSplits(ApSplitter.getValues(usedSplits));
				resultZipCode.addSplitterIndecies(usedSplits);
				break;
			}
		}
		
		logger.trace("Zip Code:: Valid: <{}>, ZipCode: <{}>, Plus4Code: <{}>",
				resultZipCode.isValid(), resultZipCode.getZipCode(), resultZipCode.getPlus4Code());
		return resultZipCode;
		
		
	}


	private List<String> getSplits(String value) {
		List<String> resultSplits = 
				Splitter
				.on(splitPattern)
				.omitEmptyStrings()
				.trimResults()
				.splitToList(value);
		return resultSplits;
	}

	/**
	 * Most common zip codes look like:
	 * 12345  
	 * 12345-1234
	 * 12345 1234
	 */
	protected List<String> getZipCodeParts(List<String> splits) {

		List<String> resultZipCodeParts = new ArrayList<>();
		
		for (int i=0; i < splits.size(); i++) {
			
			String zip5 = parse5Digits(splits.get(i));
			if (zip5 != null) {
				resultZipCodeParts.add(zip5);
				if ((i + 1) < splits.size()) {
					String zip4 = parse4Digits(splits.get(i+1));
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
