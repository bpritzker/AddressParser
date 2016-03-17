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

	private String addressNumberPrefix;
	/**
	 * This should be a string in case we have a number starting with a 0.
	 * Not sure that is a valid case but you never know.
	 */
	private String addressNumber;

	
	
	
	
	
	public String getAddressNumberPrefix() {
		return addressNumberPrefix;
	}

	public void setAddressNumberPrefix(String addressNumberPrefix) {
		this.addressNumberPrefix = addressNumberPrefix;
	}

	public String getAddressNumber() {
		return addressNumber;
	}

	public void setAddressNumber(String number) {
		this.addressNumber = number;
	}

	@Override
	public String getValue() {
		
		StringBuilder resultSb = new StringBuilder();

		String separatorPrefix = "";
		
		if (addressNumberPrefix != null) {
			resultSb.append(addressNumberPrefix);
			separatorPrefix = " ";
		}
		
		if (addressNumber != null) {
			resultSb.append(separatorPrefix).append(addressNumber);
			separatorPrefix = " ";
		}
		
		return resultSb.toString();
	}
}
