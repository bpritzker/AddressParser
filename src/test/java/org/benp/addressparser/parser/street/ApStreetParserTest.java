package org.benp.addressparser.parser.street;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.benp.addressparser.ApAddressParserConfig;
import org.benp.addressparser.component.ApDirectional;
import org.benp.addressparser.component.ApStreet;
import org.benp.addressparser.data.ApDirectionalEnum;
import org.benp.addressparser.data.ApStreetSuffixEnum;
import org.benp.addressparser.parser.ApSplitter;
import org.benp.addressparser.parser.street.ApStreetParser;
import org.junit.Before;
import org.junit.Test;


public class ApStreetParserTest {
	
	private ApStreetParser parser;

	
	@Before
	public void before() {
		ApAddressParserConfig addressParserConfig = new ApAddressParserConfig();
		parser = new ApStreetParser(addressParserConfig);
	}
	
	/**
	 * Super simple parse test
	 * @throws Exception
	 */
	@Test
	public void parse() throws Exception {
		ApSplitter splitter;
		ApStreet actualStreet;

		// basic simple standard
//		splitter = new ApSplitter("742 Evergreen Terrace");
//		actualStreet = parser.parse(splitter);
//		assertEquals(742, actualStreet.getAddressNumber().getAddressNumber());
//		assertEquals("Evergreen", actualStreet.getStreetName().getName());
//		assertEquals(ApStreetSuffixEnum.TERRACE, actualStreet.getStreetSuffix().getStreetSuffix());
//		assertTrue(actualStreet.isValid());
		
		// No suffix should still be valid
		splitter = new ApSplitter("742 Evergreen");
		actualStreet = parser.parse(splitter);
		assertTrue(actualStreet.isValid());
		
	}
	
	@Test
	public void parseComplex() throws Exception {
		
		ApSplitter splitter;
		ApStreet actualStreet;
		
		// Test single letter stree name
		splitter = new ApSplitter("F Street");
		actualStreet = parser.parse(splitter);
		assertEquals("F STREET", actualStreet.getValue());
		
		
		splitter = new ApSplitter("South Princeton Circle");
		actualStreet = parser.parse(splitter);
		assertEquals(ApDirectionalEnum.SOUTH, actualStreet.getStreetName().getPreDirectional().getDirectional());
		assertEquals("SOUTH Princeton CIRCLE", actualStreet.getValue());
//		
//		
//		splitter = new ApSplitter("3521 West Highway");
//		actualStreet = parser.parse(splitter);
//		assertTrue(actualStreet.isValid());
//		
//		// This was causing null pointer exception
//		splitter = new ApSplitter("N6W2 3001 Bluemound Road");
//		actualStreet = parser.parse(splitter);
//		assertTrue(actualStreet.isValid());
//		
//		
//		// This was causing null pointer exception
//		splitter = new ApSplitter("1643015 Dent Place");
//		actualStreet = parser.parse(splitter);
//		assertTrue(actualStreet.isValid());
//		
//		splitter = new ApSplitter("1643015 Dent NOTFOUND");
//		actualStreet = parser.parse(splitter);
//		assertTrue(actualStreet.isValid());
		
		
//		splitter = new ApSplitter("742 Evergreen Terrace S");
//		actualStreet = parser.parse(splitter);
//		assertTrue(actualStreet.isValid());
//		assertEquals("E", actualStreet.getPostDirection().getValue());
	}
	
	

	

}
