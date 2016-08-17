package org.benp.addressparser;

import static org.junit.Assert.assertEquals;

import org.benp.addressparser.component.SimpleAddress;
import org.junit.Test;

/*
 * 
 * These are just simple tests that should work.
 * They are test to make sure the application is easy to use
 * A lot of them are just strings and make sure you run the application 
 * easily and get the correct result.
 * 
 * Most people that use this parser should use cases like this.
 * 
 */
public class AddressParserSimpleTest {

	@Test
	public void testSimpleExamples() throws Exception {
		String addressString = "123 Fake St. Springfield MA 01101";
		AddressParser addressParser = new AddressParser();
		SimpleAddress resultAddress = addressParser.parseAddressSimple(addressString);
		assertEquals("123 Fake Street", resultAddress.getStreetAddress1());
		assertEquals("Springfield", resultAddress.getCity());
		assertEquals("MA", resultAddress.getState());
		assertEquals("01101", resultAddress.getZip());
	}
	
	
	
	
	
	
	
	
}
