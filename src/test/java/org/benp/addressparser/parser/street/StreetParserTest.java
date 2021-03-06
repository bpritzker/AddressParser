package org.benp.addressparser.parser.street;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.benp.addressparser.common.ApException;
import org.benp.addressparser.component.street.Street;
import org.benp.addressparser.component.street.StreetNamePostType;
import org.benp.addressparser.data.DirectionalEnum;
import org.benp.addressparser.parser.ApSplitter;
import org.junit.Test;


public class StreetParserTest extends StreetParser {
	
	public StreetParserTest() throws ApException {
		super(null, null);
	}

	
	/**
	 * Super simple parse test
	 * @throws Exception
	 */
	@Test
	public void parse() throws Exception {
		ApSplitter splitter;
		Street actualStreet;

		// basic simple standard
		splitter = new ApSplitter("742 Evergreen Terrace");
		actualStreet = parse(splitter);
		assertEquals(742, actualStreet.getStreet1().getAddressNumber().getAddressNumber());
		assertEquals("Evergreen", actualStreet.getStreet1().getStreetName().getName());
		assertEquals("TERRACE", 
				actualStreet.getStreet1().getStreetPostType().getStreetPostType().getDefualtValue());
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
		assertTrue(actualStreet.getStreet1().getAddressNumber().isValid());
		assertTrue(actualStreet.getStreet1().getStreetPostType().isValid());
		assertTrue(actualStreet.getStreet1().getStreetPostType().getStreetNamePostTypeDirectional().isValid());
		

		splitter = new ApSplitter("888 Lakewoods Village Mhp");
		actualStreet = parse(splitter);
		assertEquals("888 Lakewoods Village Mhp", actualStreet.getValueDefault());
		assertTrue(actualStreet.isValid());
		
		splitter = new ApSplitter("452 South Drive");
		actualStreet = parse(splitter);
		assertTrue(actualStreet.isValid());
		assertEquals("452 South Drive", actualStreet.getValueDefault());
		
		splitter = new ApSplitter("20389 Lynchburg HWY");
		actualStreet = parse(splitter);
		assertTrue(actualStreet.isValid());
		assertEquals("20389 Lynchburg Highway", actualStreet.getValueDefault());
	}

	@Test
	public void streetNameSecondPart() throws Exception {
		ApSplitter splitter;
		Street actualStreet;
		
		splitter = new ApSplitter("742 Evergreen Terrace South Unit #4303");		
		actualStreet = parse(splitter);
		assertEquals("Unit #4303", actualStreet.getStreet2().getStreetPostOther().getValueDefault());
		
	}
	
	
	@Test
	public void streetNamePostType() throws Exception {
		
		ApSplitter splitter;
		StreetNamePostType actualStreetNamePostType;

		splitter = new ApSplitter("742 Evergreen Terrace South");		
		actualStreetNamePostType = getPostType(splitter);
		assertEquals("TERRACE SOUTH", actualStreetNamePostType.getValueNormalized());
		
		splitter = new ApSplitter("South Drive");
		actualStreetNamePostType = getPostType(splitter);
		assertEquals("Drive", actualStreetNamePostType.getValueDefault());
		
		splitter = new ApSplitter("888 West Rarponn Boulevard Northwest");
		actualStreetNamePostType = getPostType(splitter);
		assertEquals("Boulevard Northwest", actualStreetNamePostType.getValueDefault());
		assertTrue(actualStreetNamePostType.isValid());
	}
	
	

	
	
	
	
	
	@Test
	public void parseComplex() throws Exception {
		
		ApSplitter splitter;
		Street actualStreet;
		
		// Test single letter stree name
		splitter = new ApSplitter("F Street");
		actualStreet = parse(splitter);
		assertEquals("F STREET", actualStreet.getValueNormalized());
		
		
		splitter = new ApSplitter("South Princeton Circle");
		actualStreet = parse(splitter);
		assertEquals(DirectionalEnum.SOUTH, actualStreet.getStreet1().getStreetName().getPreDirectional().getDirectional());
		assertEquals("South Princeton Circle", actualStreet.getStreet1().getValueDefault());
		
		
		splitter = new ApSplitter("3521 West Highway");
		actualStreet = parse(splitter);
		assertEquals("West", actualStreet.getStreet1().getStreetName().getName());
		assertTrue(actualStreet.isValid());
		
		// caused split non continuous error
		splitter = new ApSplitter("14750 Senior 31");
		actualStreet = parse(splitter);
		assertEquals("Senior 31", actualStreet.getStreet1().getStreetName().getName());
		assertTrue(actualStreet.isValid());
		
		// This was causing null pointer exception
		splitter = new ApSplitter("N6W2 3001 Bluemound Road");
		actualStreet = parse(splitter);
		assertTrue(actualStreet.isValid());
		
		
		// This was causing null pointer exception
		splitter = new ApSplitter("1643015 Dent Place");
		actualStreet = parse(splitter);
		assertTrue(actualStreet.isValid());
		
		splitter = new ApSplitter("1643015 Dent NOTFOUND");
		actualStreet = parse(splitter);
		assertTrue(actualStreet.isValid());
		
		
		splitter = new ApSplitter("742 Evergreen Terrace S");
		actualStreet = parse(splitter);
		assertTrue(actualStreet.isValid());
		assertEquals("South", actualStreet.getStreet1().getStreetPostType().getStreetNamePostTypeDirectional().getValueDefault());
	}
	
	

	

}
