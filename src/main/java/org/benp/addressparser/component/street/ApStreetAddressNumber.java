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

	/**
	 * This should be a string in case we have a number starting with a 0.
	 * Not sure that is a valid case but you never know.
	 */
	private String number;

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
}
