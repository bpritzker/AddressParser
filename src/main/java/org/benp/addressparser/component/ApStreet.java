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
    private String postDirection;
    
    
    
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
	public String getPostDirection() {
		return postDirection;
	}
	public void setPostDirection(String postDirection) {
		this.postDirection = postDirection;
	}
    

	
}
