package org.benp.addressparser;

public class ApAddressParserManualRunner {

	public static void main(String[] args) {
		ApAddressParserManualRunner addressParserManualRunner = new ApAddressParserManualRunner();
		try {
			addressParserManualRunner.run();
		} catch (ApException ape) {
			ape.printStackTrace();
		}

	}

	private void run() throws ApException {
		ApAddressParser addressParser = new ApAddressParser();

		String address = "742 Evergreen Terrace Springfield MA";
		addressParser.parseAddress(address);
		
		
		
	}

}
