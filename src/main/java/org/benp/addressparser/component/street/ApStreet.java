package org.benp.addressparser.component.street;

import org.benp.addressparser.component.ApComponentMultiPart;

/**
 * This is based off the document:
 * https://www.fgdc.gov/standards/projects/FGDC-standards-projects/street-address/05-11.2ndDraft.CompleteDoc.pdf
 * 
 * @author Ben P
 *
 */
public class ApStreet extends ApComponentMultiPart {

	private ApStreetAddressNumber addressNumber;
    private ApStreetStreetName streetName; // Street Name
    private ApStreetSuffix streetSuffix;
//    private ApStreetPostOther streetPostOther;
//    private ApStreetOther other;
//    private ApDirectionalEnum postDirection;
    
    
    
	public ApStreetAddressNumber getAddressNumber() {
		return addressNumber;
	}
	public void setAddressNumber(ApStreetAddressNumber addressNumber) {
		this.addressNumber = addressNumber;
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
		
		if (addressNumber != null && addressNumber.isValid()) {
			resultSb.append(addressNumber.getValue());
			separatorPrefix = " ";
		}
		
		if (streetName != null && streetName.isValid()) {
			resultSb.append(separatorPrefix).append(streetName.getValue());
			separatorPrefix = " ";
		}
		
		if (streetSuffix != null && streetSuffix.isValid()) {
			resultSb.append(separatorPrefix).append(streetSuffix.getValue());
			separatorPrefix = " ";
		}
		
		return resultSb.toString();
	}
	@Override
	public boolean isComplete() {
		if (addressNumber.isValid() && streetName.isValid() && streetSuffix.isValid()) {
			return true;
		} else {
			return false;
		}
	}
	@Override
	public boolean isPartial() {
		if (addressNumber.isValid() || streetName.isValid() || streetSuffix.isValid()) {
			return true;
		} else {
			return false;
		}
	}
	
	
	/**
	 * This is the value that will match with the GeoCoding DB.
	 * In the future you can customize this to different DBs but for now
	 * it works off the Site: http://www.opengeocode.org/download.php
	 * @return
	 */
	public String getNormalizedValue() {

		StringBuilder resultSb = new StringBuilder();
//		private ApStreetAddressNumber addressNumber;
//	    private ApStreetStreetName streetName; // Street Name
//	    private ApStreetSuffix streetSuffix;
		
		String appendSplitter = "";
		
		if (addressNumber != null && addressNumber.isValid()) {
			resultSb.append(addressNumber.getAddressNumber());
			appendSplitter = " ";
		}
		
		if (streetName != null && streetName.isValid()) {
			resultSb.append(appendSplitter).append(streetName.getNormalizedValue());
			appendSplitter = " ";
		}
		
		if (streetSuffix != null && streetSuffix.isValid()) {
			resultSb.append(appendSplitter).append(streetSuffix.getNormalizedValue());
		}
		
		return resultSb.toString();
	}
    

	
}
