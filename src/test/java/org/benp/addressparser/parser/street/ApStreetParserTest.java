package org.benp.addressparser.parser.street;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.benp.addressparser.component.street.ApStreet;
import org.benp.addressparser.component.street.StreetNamePostType;
import org.benp.addressparser.data.ApStreetPostTypeEnum;
import org.benp.addressparser.parser.ApSplitter;
import org.junit.Test;


public class ApStreetParserTest extends ApStreetParser {
	
	public ApStreetParserTest() {
		super(null);
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
		splitter = new ApSplitter("742 Evergreen Terrace");
		actualStreet = parse(splitter);
		assertEquals(742, actualStreet.getAddressNumber().getAddressNumber());
		assertEquals("Evergreen", actualStreet.getStreetName().getName());
		assertEquals(ApStreetPostTypeEnum.TERRACE, 
				actualStreet.getStreetPostType().getStreetPostType());
		assertTrue(actualStreet.isValid());
		
		// No suffix should still be valid
		splitter = new ApSplitter("742 Evergreen");
		actualStreet = parse(splitter);
		assertTrue(actualStreet.isValid());
		
		
		// In this case we have a post directional after the street enum
		splitter = new ApSplitter("580 Green Dolphin Drive South");
		actualStreet = parse(splitter);
		assertTrue(actualStreet.isValid());
		assertTrue(actualStreet.isValid());
		assertTrue(actualStreet.getAddressNumber().isValid());
		assertTrue(actualStreet.getStreetPostType().isValid());
		assertTrue(actualStreet.getStreetPostType().getStreetNamePostTypeDirectional().isValid());
		
		
		
//		splitter = new ApSplitter("452 South Drive");
//		actualStreet = parse(splitter);
//		assertTrue(actualStreet.isValid());
	}

	
	
	
	@Test
	public void StreetNamePostType() throws Exception {
		
		String streetString = "742 Evergreen Terrace South";
		ApSplitter splitter = new ApSplitter(streetString);
		
		StreetNamePostType actualStreetNamePostType = getPostType(splitter);
		assertEquals("TERRACE SOUTH", actualStreetNamePostType.getValue());
		
		
		
//		splitter = new ApSplitter("888 Lakewoods Village Mhp");
//		actualStreetNamePostType = getPostType(splitter);
//		assertEquals("Village Mhp", actualStreetNamePostType.getNormalizedValue());
//		assertTrue(actualStreetNamePostType.isValid());
		
		
		
		splitter = new ApSplitter("888 West Rarponn Boulevard Northwest");
		actualStreetNamePostType = getPostType(splitter);
		assertEquals("Boulevard Northwest", actualStreetNamePostType.getNormalizedValue());
		assertTrue(actualStreetNamePostType.isValid());
	}
	
	
	
	
	
	
	
	
	@Test
	public void parseComplex() throws Exception {
		
		ApSplitter splitter;
		ApStreet actualStreet;
		
		// Test single letter stree name
		splitter = new ApSplitter("F Street");
		actualStreet = parse(splitter);
		assertEquals("F STREET", actualStreet.getValue());
		
		
//		splitter = new ApSplitter("South Princeton Circle");
//		actualStreet = parse(splitter);
//		assertEquals(ApDirectionalEnum.SOUTH, actualStreet.getStreetName().getPreDirectional().getDirectional());
//		assertEquals("SOUTH Princeton CIRCLE", actualStreet.getValue());
//		
//		
//		splitter = new ApSplitter("3521 West Highway");
//		actualStreet = parser.parse(splitter);
//		assertEquals("West", actualStreet.getStreetName().getName());
//		assertTrue(actualStreet.isValid());
		
		// caused split non continious error
		splitter = new ApSplitter("14750 Senior 31");
		actualStreet = parse(splitter);
		assertEquals("Senior 31", actualStreet.getStreetName().getName());
		assertTrue(actualStreet.isValid());
		
//		// This was causing null pointer exception
//		splitter = new ApSplitter("N6W2 3001 Bluemound Road");
//		actualStreet = parser.parse(splitter);
//		assertTrue(actualStreet.isValid());
//		
//		
		// This was causing null pointer exception
		splitter = new ApSplitter("1643015 Dent Place");
		actualStreet = parse(splitter);
		assertTrue(actualStreet.isValid());
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
