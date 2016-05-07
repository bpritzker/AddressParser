package org.benp.addressparser.parser.street;

import org.benp.addressparser.ApAddressParserConfig;
import org.benp.addressparser.ApException;
import org.benp.addressparser.component.ApDirectional;
import org.benp.addressparser.component.street.ApStreet;
import org.benp.addressparser.component.street.ApStreetAddressNumber;
import org.benp.addressparser.component.street.ApStreetStreetName;
import org.benp.addressparser.component.street.StreetNamePostType;
import org.benp.addressparser.data.ApDirectionalEnum;
import org.benp.addressparser.data.ApSplit;
import org.benp.addressparser.data.ApStreetPostTypeEnum;
import org.benp.addressparser.parser.ApParserBase;
import org.benp.addressparser.parser.ApSplitter;

public class ApStreetParser extends ApParserBase {
	
	private ApStreetNumberParser streetNumberParser;
	private ApStreetNameParser streetNameParser;
	
	
	public ApStreetParser(ApAddressParserConfig config) {
		super(config);
		streetNumberParser = new ApStreetNumberParser(config);
		streetNameParser = new ApStreetNameParser(config);
	}
	
	public ApStreet parse(ApSplitter splitter) throws ApException {
		ApStreet resultStreet = new ApStreet();
		
		
		// Order here is important!
		// First look for a post type cause they are the most standard. 
		// we know what they look like, well, not always but we can make the best guess on them. 
		StreetNamePostType addressPostType = getPostType(splitter);
		resultStreet.setStreetPostType(addressPostType);
		
		// Next, get the address number, we know this needs to be number so that is more to 
		// go on than the "name" that can be anything
		ApStreetAddressNumber addressNumber = streetNumberParser.parse(splitter);
		resultStreet.setAddressNumber(addressNumber);
		
		ApStreetStreetName streetName = streetNameParser.parse(splitter);
		resultStreet.setStreetName(streetName);
		
		
		

		// We don't need a street suffix to be valid. Might want to change that some other time
		if (addressNumber != null && streetName != null) {
			resultStreet.setValid(true);
		}
		return resultStreet;
	}


	protected StreetNamePostType getPostType(ApSplitter splitter) throws ApException {

		StreetNamePostType resultStreetNamePostType = new StreetNamePostType();
		int rightValueOffset = 0;
		ApSplit rightValue = splitter.getNextRightValue();
		while (rightValue != null) {
			String tempStreetSteetNamePostTypeValue = rightValue.getValue().toUpperCase();
			ApStreetPostTypeEnum tempStreetNamePostType = ApStreetPostTypeEnum.fromCommonAbbreviation(tempStreetSteetNamePostTypeValue);
			
			if (tempStreetNamePostType != null) {
				splitter.addUsedSplit(rightValue);
				resultStreetNamePostType.addSplitterIndecies(rightValue);
				resultStreetNamePostType.setStreetPostType(tempStreetNamePostType);
				resultStreetNamePostType.setValid(true);
				
				// Now look for a street name post type directional
				ApSplit postDirectionalSplit = splitter.getNextRightValue();
				String postDirectionalValue = postDirectionalSplit.getValue();
				ApDirectionalEnum tempDirectionalEnum = ApDirectionalEnum.fromMapping(postDirectionalValue);
				if (tempDirectionalEnum != null) {
					ApDirectional tempDirectional = new ApDirectional();
					tempDirectional.setDirectional(tempDirectionalEnum);
					tempDirectional.addSplitterIndecies(postDirectionalSplit);
					tempDirectional.setValid(true);
					resultStreetNamePostType.setStreetNamePostTypeDirectional(tempDirectional);
					splitter.addUsedSplit(postDirectionalSplit);
				}
				
				break;
			}
			rightValueOffset++;
			rightValue = splitter.getNextRightValue(rightValueOffset);
		}
		return resultStreetNamePostType;
	}

	
	
	

	
	
	
	
	
	
	


}
