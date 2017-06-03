package org.benp.addressparser.component.street;

import org.benp.addressparser.component.ComponentBase;


/**
 * This class holds all the values that are NOT part of the basic street address.
 * It includes things like apartment and building numbers.
 * This will need a lot of work to normalize correctly. 
 * 
 * 
 * 
 * @author Ben P
 *
 */

public class StreetNamePart2 extends ComponentBase {
	
    private StreetNamePostOther streetPostOther;  // This is also known as Street 2

	
	
	@Override
	public String getValueNormalized() {
		if (streetPostOther == null) {
			return "";
		}
		return streetPostOther.getValueNormalized();
	}
	

	@Override
	public String getValueDefault() {
		if (streetPostOther == null) {
			return "";
		}
		return streetPostOther.getValueNormalized();
	}

	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///              BEGIN GETTERS AND SETTERS
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////	
    
	public StreetNamePostOther getStreetPostOther() {
		return streetPostOther;
	}
	public void setStreetPostOther(StreetNamePostOther streetPostOther) {
		this.streetPostOther = streetPostOther;
	}


}
