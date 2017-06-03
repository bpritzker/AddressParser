package org.benp.addressparser;

import org.benp.addressparser.common.ApException;
import org.benp.addressparser.component.Address;

/**
 * Use this class if you want to manually test what happens when you try to parse an address.
 * Basically it's used for manually running/testing, hence the name.
 * 
 * @author Ben P
 *
 */
public class AddressParserManualRunner {

	public static void main(String[] args) {
		AddressParserManualRunner addressParserManualRunner = new AddressParserManualRunner();
		try {
			addressParserManualRunner.run();
		} catch (ApException ape) {
			ape.printStackTrace();
		}

	}

	private void run() throws ApException {
		AddressParser addressParser = new AddressParser();

		String address = "742 Evergreen Terrace Springfield MA 02021";
		Address actualAddress =addressParser.parseAddress(address);
		System.out.println("Street : " + actualAddress.getStreeAddressDefault());
		System.out.println("City   : " + actualAddress.getCityDefault());
		System.out.println("State  : " + actualAddress.getStateDefault());
		System.out.println("Zip    : " + actualAddress.getZipDefault());
	}

}
