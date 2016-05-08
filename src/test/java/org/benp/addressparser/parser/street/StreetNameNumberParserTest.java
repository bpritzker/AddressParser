package org.benp.addressparser.parser.street;

import static org.junit.Assert.assertEquals;

import org.benp.addressparser.AddressParserConfig;
import org.benp.addressparser.component.street.StreetNameNumber;
import org.benp.addressparser.parser.ApSplitter;
import org.benp.addressparser.parser.street.StreetNameNumberParser;
import org.junit.Before;
import org.junit.Test;

/**
 * Street Numbers are complex enough to get their own parser lol
 * See 2.2.1.2
 * @author Ben P
 *
 */
public class StreetNameNumberParserTest extends StreetNameNumberParser {
	
	private StreetNameNumberParser parser;

	
	@Before
	public void before() {
		AddressParserConfig addressParserConfig = new AddressParserConfig();
		parser = new StreetNameNumberParser(addressParserConfig);
	}

	public StreetNameNumberParserTest() {
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
		StreetNameNumber actualStreetAddressNumber;
		
		
		splitter = new ApSplitter("123 A Main");
		actualStreetAddressNumber = parser.getAddressNumber(splitter);
		assertEquals(123, actualStreetAddressNumber.getAddressNumber());
//		assertEquals("A", actualStreetAddressNumber.getAddressNumberSuffix());
		
		
//		splitter = new ApSplitter("194-03 1/2 50th");
//		actualStreetAddressNumber = parser.getAddressNumber(splitter);
//		assertEquals("E", actualStreetAddressNumber.getAddressNumberSuffix());
		

		splitter = new ApSplitter("742 E Evergreen Terrace");
		actualStreetAddressNumber = parser.getAddressNumber(splitter);
//		assertEquals("E", actualStreetAddressNumber.getAddressNumberSuffix());
		
		
//		splitter = new ApSplitter("742 1/2 Evergreen Terrace");
//		actualStreet = parser.parse(splitter);
//		assertTrue(actualStreet.isValid());
//		assertEquals("1/2", actualStreet.getAddressNumber().getAddressNumberSuffix());
		
		

		
		splitter = new ApSplitter("742 Evergreen");
		actualStreetAddressNumber = parser.getAddressNumber(splitter);
		assertEquals(742, actualStreetAddressNumber.getAddressNumber());
		
		
	}

}
