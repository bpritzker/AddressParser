package org.benp.addressparser.parser;

import static org.junit.Assert.assertEquals;

import org.benp.addressparser.ApAddressParserConfig;
import org.benp.addressparser.component.street.ApStreetAddressNumber;
import org.junit.Before;
import org.junit.Test;

/**
 * Street Numbers are complex enough to get their own parser lol
 * See 2.2.1.2
 * @author Ben P
 *
 */
public class ApStreetNumberParserTest extends ApStreetNumberParser {
	
	private ApStreetNumberParser parser;

	
	@Before
	public void before() {
		ApAddressParserConfig addressParserConfig = new ApAddressParserConfig();
		parser = new ApStreetNumberParser(addressParserConfig);
	}

	public ApStreetNumberParserTest() {
//		ApAddressParserConfig config = new ApAddressParserConfig();Eber02bp
		super(null);
	}
	
	@Test
	/**
	 * Assume the 
	 * @throws Exception
	 */
	public void streetAddressNumber() throws Exception {
		
		ApSplitter splitter;
		ApStreetAddressNumber actualStreetAddressNumber;
		
		
		splitter = new ApSplitter("123 A Main");
		actualStreetAddressNumber = parser.getAddressNumber(splitter);
		assertEquals(123, actualStreetAddressNumber.getAddressNumber());
		assertEquals("A", actualStreetAddressNumber.getAddressNumberSuffix());
		
		
//		splitter = new ApSplitter("194-03 1/2 50th");
//		actualStreetAddressNumber = parser.getAddressNumber(splitter);
//		assertEquals("E", actualStreetAddressNumber.getAddressNumberSuffix());
		

		splitter = new ApSplitter("742 E Evergreen Terrace");
		actualStreetAddressNumber = parser.getAddressNumber(splitter);
		assertEquals("E", actualStreetAddressNumber.getAddressNumberSuffix());
//		
//		
//		splitter = new ApSplitter("742 1/2 Evergreen Terrace");
//		actualStreet = parser.parse(splitter);
//		assertTrue(actualStreet.isValid());
//		assertEquals("1/2", actualStreet.getAddressNumber().getAddressNumberSuffix());
//		
//		

		
		splitter = new ApSplitter("742 Evergreen");
		actualStreetAddressNumber = parser.getAddressNumber(splitter);
		assertEquals(742, actualStreetAddressNumber.getAddressNumber());
		
		
	}

}
