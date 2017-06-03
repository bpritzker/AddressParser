package org.benp.addressparser;

import org.benp.addressparser.common.ApException;
import org.benp.addressparser.component.Address;
import org.benp.addressparser.component.SimpleAddress;

public class AddressComparator {

	private AddressParser addressParser;
	
	public AddressComparator() throws ApException {
		addressParser = new AddressParser();
	}
	
	
	public boolean isSameSimple(String address1, String address2) throws ApException {
		Address parsedAddress1 = addressParser.parseAddress(address1);
		Address parsedAddress2 = addressParser.parseAddress(address2);
		return isSame(parsedAddress1, parsedAddress2);
		
	}
	
	public boolean isSame(Address address1, Address address2) {
		String addressNormalized1 = address1.getValueNormalized();
		String addressNormalized2 = address2.getValueNormalized();
		
		if (addressNormalized1.equals(addressNormalized2)) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isSameSimple(SimpleAddress address1, SimpleAddress address2) {
		String addressNormalized1 = address1.getNormalized();
		String addressNormalized2 = address2.getNormalized();
		
		if (addressNormalized1.equals(addressNormalized2)) {
			return true;
		} else {
			return false;
		}
	}
	
	
}
