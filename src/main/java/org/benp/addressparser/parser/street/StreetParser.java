package org.benp.addressparser.parser.street;

import java.util.List;

import org.benp.addressparser.AddressParserConfig;
import org.benp.addressparser.ApException;
import org.benp.addressparser.component.Directional;
import org.benp.addressparser.component.street.Street;
import org.benp.addressparser.component.street.StreetNameNumber;
import org.benp.addressparser.component.street.StreetNamePostOther;
import org.benp.addressparser.component.street.StreetNamePostType;
import org.benp.addressparser.component.street.StreetNameStreet;
import org.benp.addressparser.data.DirectionalEnum;
import org.benp.addressparser.data.Split;
import org.benp.addressparser.data.StreetPostTypeEnum;
import org.benp.addressparser.data.mapping.Mapper;
import org.benp.addressparser.parser.ApSplitter;
import org.benp.addressparser.parser.ParserBase;

public class StreetParser extends ParserBase {
	
	private StreetNameNumberParser streetNumberParser;
	private StreetNameStreetParser streetNameParser;
	private Mapper mapper;
	
	
	public StreetParser(Mapper inMapper, AddressParserConfig inConfig) throws ApException {
		super(inConfig);
		if (inMapper != null) {
			mapper = inMapper;
		} else {
			// If the mapper passed in is null then init a new one
			mapper = new Mapper(inConfig);
		}
		streetNumberParser = new StreetNameNumberParser(inConfig);
		streetNameParser = new StreetNameStreetParser(mapper, inConfig);
	}
	

	public Street parse(ApSplitter inSplitter) throws ApException {
		Street resultStreet = new Street();
		
		
		// Order here is important!
		// First look for a post type cause they are the most standard. 
		// we know what they look like, well, not always but we can make the best guess on them. 
		StreetNamePostType addressPostType = getPostType(inSplitter);
		resultStreet.setStreetPostType(addressPostType);
		
		
		// Now that we have the post type anything after it is "Other"
		StreetNamePostOther postOther = getPostOther(inSplitter, addressPostType);
		resultStreet.setStreetPostOther(postOther);
		
		// Next, get the address number, we know this needs to be number so that is more to 
		// go on than the "name" that can be anything
		StreetNameNumber addressNumber = streetNumberParser.parse(inSplitter);
		resultStreet.setAddressNumber(addressNumber);
		
		StreetNameStreet streetName = streetNameParser.parse(inSplitter);
		resultStreet.setStreetName(streetName);
		
		
		

		// We don't need a street suffix to be valid. Might want to change that some other time
		if (addressNumber != null && streetName != null) {
			resultStreet.setValid(true);
		}
		return resultStreet;
	}


	private StreetNamePostOther getPostOther(ApSplitter inSplitter, StreetNamePostType inAddressPostType) throws ApException {

		// easy case nothing to do.
		if (inAddressPostType == null) {
			return null;
		}
		
		List<Split> postTypeSplits = inAddressPostType.getSplitterIndecies();
		if (postTypeSplits.size() == 0) {
			return null;
		}
		
		Split lastPostTypeIndex = postTypeSplits.get(postTypeSplits.size()-1);
		List<Split> otherSplits = inSplitter.getValues(lastPostTypeIndex.getSplitIndex(), -1);
		if (otherSplits == null || otherSplits.size() == 0) {
			return null;
		}
		
		StreetNamePostOther resultPostOther = new StreetNamePostOther();
		resultPostOther.setSplitterIndecies(otherSplits);
		inSplitter.addUsedSplits(otherSplits);
		resultPostOther.setValid(true);
		return resultPostOther;
		
		
	}

	protected StreetNamePostType getPostType(ApSplitter inSplitter) throws ApException {

		StreetNamePostType resultStreetNamePostType = new StreetNamePostType();
		int rightValueOffset = 0;
		Split firstRightValue = inSplitter.getNextRightValue();
		while (firstRightValue != null) {
			String tempStreetSteetNamePostTypeValue = firstRightValue.getValue().toUpperCase();
			StreetPostTypeEnum tempStreetNamePostType = StreetPostTypeEnum.fromCommonAbbreviation(tempStreetSteetNamePostTypeValue);
			
			if (tempStreetNamePostType != null) {
				inSplitter.addUsedSplit(firstRightValue);
				resultStreetNamePostType.addSplitterIndecies(firstRightValue);
				resultStreetNamePostType.setStreetPostType(tempStreetNamePostType);
				resultStreetNamePostType.setValid(true);
				
				// Now look for a street name post type directional
				// Yes, you want the next LEFT value (with offset)!
				Split postDirectionalSplit = inSplitter.getNextLeftValue(firstRightValue.getSplitIndex());
				if (postDirectionalSplit != null) {
					String postDirectionalValue = postDirectionalSplit.getValue();
					DirectionalEnum tempDirectionalEnum = DirectionalEnum.fromMapping(postDirectionalValue);
					if (tempDirectionalEnum != null) {
						Directional tempDirectional = new Directional();
						tempDirectional.setDirectional(tempDirectionalEnum);
						tempDirectional.addSplitterIndecies(postDirectionalSplit);
						tempDirectional.setValid(true);
						resultStreetNamePostType.setStreetNamePostTypeDirectional(tempDirectional);
						inSplitter.addUsedSplit(postDirectionalSplit);
					}
				}
				break;
			}
			rightValueOffset++;
			firstRightValue = inSplitter.getNextRightValue(rightValueOffset);
		}
		return resultStreetNamePostType;
	}

	

}
