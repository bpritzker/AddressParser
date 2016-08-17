package org.benp.addressparser.component;



/**
 * This class is pretty much the same as the Address class but hides 
 * most of the functionality to make it easier to use.
 * 
 * 
 * @author Ben P
 *
 */
public class SimpleAddress {
	
	private Address address;
	
	public SimpleAddress(Address inAddress) {
		address = inAddress;
	}
	
	
	public String getStreetAddress() {
		return address.getStreeAddressDefault();
	}
	
	public String getCity() {
		return address.getCityDefault();
	}
	
	public String getState() {
		return address.getStateDefault();
	}
	
	public String getZip() {
		return address.getZipDefault();
	}

}
