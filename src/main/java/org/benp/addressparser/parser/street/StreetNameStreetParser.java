package org.benp.addressparser.parser.street;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.benp.addressparser.AddressParserConfig;
import org.benp.addressparser.ApException;
import org.benp.addressparser.component.Directional;
import org.benp.addressparser.component.street.StreetNameStreet;
import org.benp.addressparser.data.DirectionalEnum;
import org.benp.addressparser.data.Split;
import org.benp.addressparser.parser.ParserBase;
import org.benp.addressparser.parser.ApSplitter;

public class StreetNameStreetParser extends ParserBase {

	public StreetNameStreetParser(AddressParserConfig config) {
		super(config);
	}

	public StreetNameStreet parse(ApSplitter splitter) throws ApException {
		StreetNameStreet resultStreetName = new StreetNameStreet();
		List<Split> remaingingSplits = splitter.getRemainingSplits();
		
		StringBuilder streetNameBuilder = new StringBuilder();
		
		// Now for the tricky part... 
		// Handle the case where the we have more than one part 
		// and check if the first part is a street pre-directional 
		// If the size is 1 then it's the street
		boolean firstSplitUsed = false;
		if (remaingingSplits.size() > 1) {
			String firstValue = remaingingSplits.get(0).getValue();
			DirectionalEnum tempDirectionalEnum = DirectionalEnum.fromMapping(firstValue);
			if (tempDirectionalEnum != null) {
				firstSplitUsed = true;
				Directional tempDirectional = new Directional();
				tempDirectional.setDirectional(tempDirectionalEnum);
				tempDirectional.addSplitterIndex(remaingingSplits.get(0));
				tempDirectional.setValid(true);
				resultStreetName.setPreDirectional(tempDirectional);
			}
			
		} 
		
		String prefixVal = "";
		for (int i =0; i < remaingingSplits.size(); i++) {
			if (i != 0 || ! firstSplitUsed) {
				streetNameBuilder.append(prefixVal).append(remaingingSplits.get(i).getValue());
				prefixVal = " ";
			}
		}
		String streetName =  streetNameBuilder.toString();
		
		
		if (StringUtils.isNotBlank(streetName)) {
			resultStreetName.setName(streetName);
			resultStreetName.setSplitterIndecies(remaingingSplits);
			resultStreetName.setValid(true);
			splitter.addUsedSplits(remaingingSplits);
		}
		return resultStreetName;
	}

}
