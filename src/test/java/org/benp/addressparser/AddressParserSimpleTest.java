package org.benp.addressparser;

import static org.junit.Assert.assertEquals;

import org.benp.addressparser.component.Address;
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
		Address resultAddress = addressParser.parseAddress(addressString);
		assertEquals("123 Fake ST", resultAddress.getStreeAddressDefault());
		assertEquals("Springfield", resultAddress.getCityDefault());
		assertEquals("MA", resultAddress.getStateDefault());
		assertEquals("01101", resultAddress.getZipDefault());
		
	}
	
	
	
	
	
	
	
	
}
