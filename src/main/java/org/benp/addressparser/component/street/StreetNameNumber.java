package org.benp.addressparser.component.street;

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

	// Address Numbers should be numbers, like yea I know
	// They should also be string to keep track of the full value.
	//  Example: 194-03 address number is 03 or 3
	private int addressNumber;


	public int getAddressNumber() {
		return addressNumber;
	}

	public void setAddressNumber(int addressNumber) {
		this.addressNumber = addressNumber;
	}

	@Override
	public String getValueNormalized() {
		
		StringBuilder resultSb = new StringBuilder();

		String separatorPrefix = "";
		
		if (addressNumberPrefix != null) {
			resultSb.append(addressNumberPrefix);
			separatorPrefix = " ";
		}
		
		if (addressNumber != INVALID_ADDRESS_NUMBER) {
			resultSb.append(separatorPrefix).append(addressNumber);
			separatorPrefix = " ";
		}
		
		return resultSb.toString();
	}
	
	@Override
	public String getDefaultValue() {
		// For a number they are the same
		return getValueNormalized();
	}
}
