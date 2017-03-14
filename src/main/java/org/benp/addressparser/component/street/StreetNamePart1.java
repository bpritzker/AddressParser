package org.benp.addressparser.component.street;

import org.apache.commons.lang3.text.WordUtils;
import org.benp.addressparser.component.ComponentMultiPart;

public class StreetNamePart1 extends ComponentMultiPart {
	
	private StreetNameNumber addressNumber;
    private StreetNameStreet streetName; // Street Name
    private StreetNamePostType streetPostType;
    
    
	@Override
	public String getValueNormalized() {
		return getValue(true);
	}
	
	@Override
	public String getValueDefault() {
		return getValue(false);
	}
	
	
	private String getValue(boolean inNormalize) {

		StringBuilder resultSb = new StringBuilder();

		String separatorPrefix = "";
		
		if (addressNumber != null && addressNumber.isValid()) {
			resultSb.append(addressNumber.getValueNormalized());
			separatorPrefix = " ";
		}
		
		if (streetName != null && streetName.isValid()) {
			if (inNormalize) {
				resultSb.append(separatorPrefix).append(streetName.getValueNormalized());
			} else {
				resultSb.append(separatorPrefix).append(streetName.getValueDefault());
			}
			separatorPrefix = " ";
		}
		
		if (streetPostType != null && streetPostType.isValid()) {
			if (inNormalize) {
				resultSb.append(separatorPrefix).append(streetPostType.getValueNormalized());
			} else {
				resultSb.append(separatorPrefix).append(WordUtils.capitalizeFully(streetPostType.getValueNormalized()));
			}

			separatorPrefix = " ";
		}
		
//		if (streetPostOther != null && streetPostOther.isValid()) {
//			resultSb.append(separatorPrefix).append(streetPostOther.getValueNormalized());
//			separatorPrefix = " ";
//			
//		}
		
		return resultSb.toString();
	}
	
	
	@Override
	public boolean isComplete() {
		if (addressNumber.isValid() && streetName.isValid() && streetPostType.isValid()) {
			return true;
		} else {
			return false;
		}
	}
	
	
	@Override
	public boolean isPartial() {
		if (addressNumber.isValid() || streetName.isValid() || streetPostType.isValid()) {
			return true;
		} else {
			return false;
		}
	}
	
	
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///              BEGIN GETTERS AND SETTERS
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////	
	public StreetNameNumber getAddressNumber() {
		return addressNumber;
	}
	public void setAddressNumber(StreetNameNumber addressNumber) {
		this.addressNumber = addressNumber;
	}

	public StreetNameStreet getStreetName() {
		return streetName;
	}
	public void setStreetName(StreetNameStreet streetName) {
		this.streetName = streetName;
	}
	public StreetNamePostType getStreetPostType() {
		return streetPostType;
	}
	public void setStreetPostType(StreetNamePostType streetPostType) {
		this.streetPostType = streetPostType;
	}
}
