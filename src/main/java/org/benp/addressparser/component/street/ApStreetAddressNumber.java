package org.benp.addressparser.component.street;

import org.benp.addressparser.component.ApComponentBase;

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
 * @author Ben
 *
 */
public class ApStreetAddressNumber extends ApComponentBase {
	
	public static int INVALID_ADDRESS_NUMBER = -1;

	private String addressNumberPrefix;

	// Address Numbers should be numbers, like yea I know
	// They should also be string to keep track of the full value.
	//  Example: 194-03 address number is 03 or 3
	private int addressNumber;
	private String addressNumberString;

	public String getAddressNumberString() {
		return addressNumberString;
	}

	public void setAddressNumberString(String addressNumberString) {
		this.addressNumberString = addressNumberString;
	}

	private String addressNumberSuffix;
	
	
	public String getAddressNumberSuffix() {
		return addressNumberSuffix;
	}

	public void setAddressNumberSuffix(String addressNumberSuffix) {
		this.addressNumberSuffix = addressNumberSuffix;
	}

	public String getAddressNumberPrefix() {
		return addressNumberPrefix;
	}

	public void setAddressNumberPrefix(String addressNumberPrefix) {
		this.addressNumberPrefix = addressNumberPrefix;
	}

	public int getAddressNumber() {
		return addressNumber;
	}

	public void setAddressNumber(int addressNumber) {
		this.addressNumber = addressNumber;
	}

	@Override
	public String getValue() {
		
		StringBuilder resultSb = new StringBuilder();

		String separatorPrefix = "";
		
		if (addressNumberPrefix != null) {
			resultSb.append(addressNumberPrefix);
			separatorPrefix = " ";
		}
		
		if (addressNumber != 0) {
			resultSb.append(separatorPrefix).append(addressNumber);
			separatorPrefix = " ";
		}
		
		return resultSb.toString();
	}
}
