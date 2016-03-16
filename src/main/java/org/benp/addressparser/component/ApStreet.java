package org.benp.addressparser.component;

import org.benp.addressparser.component.street.ApStreetAddressNumber;
import org.benp.addressparser.component.street.ApStreetStreetName;
import org.benp.addressparser.component.street.ApStreetSuffix;

/**
 * This is based off the document:
 * https://www.fgdc.gov/standards/projects/FGDC-standards-projects/street-address/05-11.2ndDraft.CompleteDoc.pdf
 * 
 * @author Ben P
 *
 */
public class ApStreet extends ApComponentBase {

	private ApStreetAddressNumber primaryNumber;
	
    private ApStreetStreetName streetName; // Street Name
    private ApStreetSuffix streetSuffix;
//    private ApDirectionalEnum postDirection;
    
    
    
	public ApStreetAddressNumber getPrimaryNumber() {
		return primaryNumber;
	}
	public void setPrimaryNumber(ApStreetAddressNumber primaryNumber) {
		this.primaryNumber = primaryNumber;
	}

	public ApStreetStreetName getStreetName() {
		return streetName;
	}
	public void setStreetName(ApStreetStreetName streetName) {
		this.streetName = streetName;
	}
	public ApStreetSuffix getStreetSuffix() {
		return streetSuffix;
	}
	public void setStreetSuffix(ApStreetSuffix streetSuffix) {
		this.streetSuffix = streetSuffix;
	}

	
	@Override
	public String getValue() {

		StringBuilder resultSb = new StringBuilder();

		String separatorPrefix = "";
		
		if (primaryNumber != null) {
			resultSb.append(primaryNumber.getValue());
			separatorPrefix = " ";
		}
		
		if (streetName != null) {
			resultSb.append(separatorPrefix).append(streetName.getValue());
			separatorPrefix = " ";
		}
		
		if (streetSuffix != null) {
			resultSb.append(separatorPrefix).append(streetSuffix.getValue());
			separatorPrefix = " ";
		}
		
		return resultSb.toString();
	}
    

	
}
