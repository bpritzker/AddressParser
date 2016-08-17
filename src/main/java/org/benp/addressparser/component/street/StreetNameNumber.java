package org.benp.addressparser.component.street;

import org.apache.commons.lang3.StringUtils;
import org.benp.addressparser.component.ComponentBase;

/**
 * Created 
 * 
 * Based off: 1.7.1.5 Complex Element: Complete Address Number
 * { Address Number Prefix } +
 * { Separator Element } +
 * { Address Number *} +
 * { Separator Element } +
 * {Address Number Suffix }
 * 
 * @author Ben P
 *
 */
public class StreetNameNumber extends ComponentBase {
	
	public static int INVALID_ADDRESS_NUMBER = -1;

	private String addressNumberPrefix;

	// Address Numbers should be numbers
	private int addressNumber;

	
	// They should also be string to keep track of the full value.
	//  Example: 194-03 address number is 03 or 3
	// If an address starts off with numbers but then has other chars... this is the other chars
	private String addressNumberSecondPart;



	@Override
	public String getValueNormalized() {
		
		StringBuilder resultSb = new StringBuilder();

		String separatorPrefix = "";
		
		if (addressNumberPrefix != null) {
			resultSb.append(addressNumberPrefix.toUpperCase());
			separatorPrefix = " ";
		}
		
		
		
		if (addressNumber != INVALID_ADDRESS_NUMBER) {

			// If the address number second part exits, there is NO space so just add it.
			resultSb.append(separatorPrefix).append(addressNumber + StringUtils.defaultString(addressNumberSecondPart).toUpperCase());
			separatorPrefix = " ";
		}
		
		return resultSb.toString();
	}
	
	@Override
	public String getDefaultValue() {
		// For a number they are the same
		return getValueNormalized();
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///              BEGIN GETTERS AND SETTERS
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////	
	
	
	public String getAddressNumberSecondPart() {
		return addressNumberSecondPart;
	}

	public void setAddressNumberSecondPart(String addressNumberSecondPart) {
		this.addressNumberSecondPart = addressNumberSecondPart;
	}

	public int getAddressNumber() {
		return addressNumber;
	}

	public void setAddressNumber(int addressNumber) {
		this.addressNumber = addressNumber;
	}
	
	
	
	
	
}
