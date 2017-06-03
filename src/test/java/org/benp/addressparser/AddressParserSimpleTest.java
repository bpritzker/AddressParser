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
		String addressString = "123 Fake Street Springfield MA 01101";
		AddressParser addressParser = new AddressParser();
		SimpleAddress resultAddress = addressParser.parseAddressSimple(addressString);
		assertEquals("123 Fake Street", resultAddress.getStreetAddress1());
		assertEquals("Springfield", resultAddress.getCity());
		assertEquals("MA", resultAddress.getState());
		assertEquals("01101", resultAddress.getZip());
	}
	
	
	
	@Test
	public void testNormalizeAddress() throws Exception {

		String addressString;
		AddressParser addressParser = new AddressParser();
		SimpleAddress resultAddress;

		addressString = "123 Fake Street Springfield MA 01101";
		resultAddress = addressParser.parseAddressSimple(addressString);
		assertEquals("123 FAKE STREET SPRINGFIELD MA 01101", resultAddress.getNormalized());

		addressString = "123 Fake st Springfield Mass 01101";
		resultAddress = addressParser.parseAddressSimple(addressString);
		assertEquals("123 FAKE STREET SPRINGFIELD MA 01101", resultAddress.getNormalized());
	
		// FIXME: How to we normalize these   St and Saint need to compare to be the same!?????
		addressString = "591 W Bianca Cir  Saint Augustine  FL  32086";
		resultAddress = addressParser.parseAddressSimple(addressString);
		assertEquals("591 WEST BIANCA CIRCLE SAINT AUGUSTINE FL 32086", resultAddress.getNormalized());

		
		addressString = "591 W Bianca Cir  St Augustine  FL  32086";
		resultAddress = addressParser.parseAddressSimple(addressString);
		assertEquals("591 WEST BIANCA CIRCLE ST AUGUSTINE FL 32086", resultAddress.getNormalized());

	}
	
	
	
	
}
