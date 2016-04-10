package org.benp.addressparser.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.benp.addressparser.ApAddressParserConfig;
import org.benp.addressparser.component.ApStreet;
import org.benp.addressparser.data.ApStreetSuffixEnum;
import org.junit.Before;
import org.junit.Test;


public class ApStreetParserTest {
	
	private ApStreetParser parser;

	
	@Before
	public void before() {
		ApAddressParserConfig addressParserConfig = new ApAddressParserConfig();
		parser = new ApStreetParser(addressParserConfig);
	}
	
	@Test
	public void parse() throws Exception {
		ApSplitter splitter;
		ApStreet actualStreet;

		// basic simple standard
		splitter = new ApSplitter("742 Evergreen Terrace");
		actualStreet = parser.parse(splitter);
		assertEquals(742, actualStreet.getAddressNumber().getAddressNumber());
		assertEquals("Evergreen", actualStreet.getStreetName().getName());
		assertEquals(ApStreetSuffixEnum.TERRACE, actualStreet.getStreetSuffix().getStreetSuffix());
		assertTrue(actualStreet.isValid());
		
		// No suffix should still be valid
		splitter = new ApSplitter("742 Evergreen");
		actualStreet = parser.parse(splitter);
		assertTrue(actualStreet.isValid());
		
	}
	
	@Test
	public void parseComplex() throws Exception {
		
		ApSplitter splitter;
		ApStreet actualStreet;
		
		
		// TODO: These should work
//		splitter = new ApSplitter("50 south drive");
//		actualStreet = parser.parse(splitter);
//		assertTrue(actualStreet.isValid());
//		assertTrue(actualStreet.isComplete());
		
		
		splitter = new ApSplitter("3521 West Highway");
		actualStreet = parser.parse(splitter);
		assertTrue(actualStreet.isValid());
		
		// This was causing null pointer exception
		splitter = new ApSplitter("N6W2 3001 Bluemound Road");
		actualStreet = parser.parse(splitter);
		assertTrue(actualStreet.isValid());
		
		
		// This was causing null pointer exception
		splitter = new ApSplitter("1643015 Dent Place");
		actualStreet = parser.parse(splitter);
		assertTrue(actualStreet.isValid());
		
		splitter = new ApSplitter("1643015 Dent NOTFOUND");
		actualStreet = parser.parse(splitter);
		assertTrue(actualStreet.isValid());
		

		
		

		
		
//		splitter = new ApSplitter("742 Evergreen Terrace S");
//		actualStreet = parser.parse(splitter);
//		assertTrue(actualStreet.isValid());
//		assertEquals("E", actualStreet.getPostDirection().getValue());
	}
	
	

	

}
