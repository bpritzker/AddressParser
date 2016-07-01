package org.benp.addressparser;

/**
 * Use this class if you want to manually test what happens when you try to parse an address.
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

		String address = "742 Evergreen Terrace Springfield MA";
		addressParser.parseAddress(address);
		
		
		
	}

}
