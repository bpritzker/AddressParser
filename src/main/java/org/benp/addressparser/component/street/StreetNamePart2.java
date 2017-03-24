package org.benp.addressparser.component.street;

import org.benp.addressparser.component.ComponentBase;

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
