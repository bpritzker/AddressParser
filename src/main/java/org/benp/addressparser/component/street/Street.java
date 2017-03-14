package org.benp.addressparser.component.street;

import org.benp.addressparser.component.ComponentMultiPart;

/**
 * This is based off the document:
 * https://www.fgdc.gov/standards/projects/FGDC-standards-projects/street-address/05-11.2ndDraft.CompleteDoc.pdf
 * </B>
 * NOTE: A street address only needs the first part so when it comes to complete and partial we only care about street1.
 * Street2 is total side thought.
 * 
 * @author Ben P
 *
 */
public class Street extends ComponentMultiPart {
	
	private StreetNamePart1 street1;
	private StreetNamePart2 street2;
	
	
	@Override
	public boolean isComplete() {
		if (street1 == null) {
			return false;
		}
		
		return street1.isComplete();
	}
	@Override
	public boolean isPartial() {
		if (street1 == null) {
			return false;
		}
		return street1.isComplete();
	}

	
	@Override
	public String getValueNormalized() {
		StringBuilder resultSb = new StringBuilder();
		
		if (street1 != null) {
			resultSb.append(street1.getValueNormalized());
			
			if (street2 != null && street2.isValid()) {
				resultSb.append(" ").append(street2.getValueNormalized());
			}
		}
		return resultSb.toString();
	}

	@Override
	public String getValueDefault() {
		StringBuilder resultSb = new StringBuilder();
		
		if (street1 != null) {
			resultSb.append(street1.getValueDefault());
			
			if (street2 != null && street2.isValid()) {
				resultSb.append(" ").append(street2.getValueDefault());
			}
		}
		return resultSb.toString();
	}


	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///              BEGIN GETTERS AND SETTERS
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	public StreetNamePart1 getStreet1() {
		return street1;
	}
	public void setStreet1(StreetNamePart1 street1) {
		this.street1 = street1;
	}
	public StreetNamePart2 getStreet2() {
		return street2;
	}
	public void setStreet2(StreetNamePart2 street2) {
		this.street2 = street2;
	}
	
	
//	/**
//	 * This is the value that will match with the GeoCoding DB.
//	 * In the future you can customize this to different DBs but for now
//	 * it works off the Site: http://www.opengeocode.org/download.php
//	 * @return
//	 */
//	public String getNormalizedValue() {
//
//		StringBuilder resultSb = new StringBuilder();
//		String appendSplitter = "";
//		
//		if (addressNumber != null && addressNumber.isValid()) {
//			resultSb.append(addressNumber.getAddressNumber());
//			appendSplitter = " ";
//		}
//		
//		if (streetName != null && streetName.isValid()) {
//			resultSb.append(appendSplitter).append(streetName.getNormalizedValue());
//			appendSplitter = " ";
//		}
//		
//		if (streetPostType != null && streetPostType.isValid()) {
//			resultSb.append(appendSplitter).append(streetPostType.getNormalizedValue());
//			appendSplitter = " ";
//		}
//		
//		if (streetPostOther != null && streetPostOther.isValid()) {
//			resultSb.append(appendSplitter).append(streetPostOther.getNormalizedValue());
//		}
//		
//		return resultSb.toString();
//	}
    

	
}
